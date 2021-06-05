package net.hypixel.api.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 * A tool for trimming unneeded properties from {@link ComplexHypixelObject}s, especially to
 * minimize their memory and storage consumption. Based on MongoDB projections.
 * <p><br>
 * To use an <strong>inclusion filter</strong>, property names (or "keys") can be added via {@link
 * #include(String...) include(...)} or the {@link #with(String...) with(...) constructor}. When an
 * object is {@link #applyTo(ComplexHypixelObject) passed through} the filter, any properties not
 * explicitly named using the aforementioned methods will be removed from the object. If the object
 * did not have an included property to begin with, it will not be created.
 * <p><br>
 * Property names are referenced using dot-notation. See the documentation for {@link
 * #include(String...) include(...)} for more details.
 */
public class PropertyFilter {

    /**
     * Shorthand for constructing a new filter that only allows the {@code includedKeys} to {@link
     * #applyTo(ComplexHypixelObject) pass through}. See {@link #include(String...)} for the key
     * syntax.
     */
    public static PropertyFilter with(String... includedKeys) {
        PropertyFilter filter = new PropertyFilter();
        filter.include(includedKeys);
        return filter;
    }

    /**
     * Helper function that validates a {@code keys} array and converts it to a list.
     */
    private static List<String> keysToList(String... keys) {
        if (keys == null || keys.length == 0) {
            throw new IllegalArgumentException("At least 1 key name required");
        }

        List<String> keyList = Arrays.asList(keys);
        for (int i = 0; i < keyList.size(); i++) {
            String key = keyList.get(i);
            if (key == null) {
                throw new IllegalArgumentException("Filtered keys cannot be null");
            }

            // Remove escape characters.
            keyList.set(i, key.replace("\\.", "."));
        }

        return keyList;
    }

    // Only these keys are allowed in objects returned from `applyTo(...)`.
    // Keys with literal dots should be stripped of escape characters before being added.
    private final Set<String> allowedKeys;

    public PropertyFilter() {
        allowedKeys = new HashSet<>();
    }

    /**
     * Allows properties with any of the provided {@code keys} to {@link
     * #applyTo(ComplexHypixelObject) pass through} the filter. To include nested properties, use
     * dots ({@code .}) to separate each parent property from its child. If a property's name
     * contains a dot literally, use a double-backslash to escape the dot. (e.g. {@code
     * "key_with_literal_dot\\.in_it"} instead of {@code "key_with_literal_dot.in_it"})
     * <br><pre>
     * Examples:
     *     •{@code uuid}                - Keep the player's UUID when filtering.
     *     •{@code stats}               - Keep all of the player's stats when filtering.
     *     •{@code stats.SkyWars}       - Keep all of the player's SkyWars stats when filtering.
     *     •{@code stats.SkyWars.coins} - Keep just the player's SkyWars coins when filtering.
     * </pre>
     * Any duplicate keys (or keys already included in the filter) will be ignored.
     *
     * @param keys Names of properties that will be allowed to pass through the filter (in
     *             dot-notation).
     */
    public void include(String... keys) {
        allowedKeys.addAll(keysToList(keys));
    }

    /**
     * Removes all of the provided keys from the filter, such that objects {@link
     * #applyTo(ComplexHypixelObject) passed through} the filter will <em>not</em> include
     * properties with those keys.
     * <p><br>
     * Attempting to remove a key that was already removed, or never {@link #include(String...)
     * included} to begin with, will have no effect.
     */
    public void removeKeys(String... keys) {
        allowedKeys.removeAll(keysToList(keys));
    }

    /**
     * @return A new set containing all property keys that can {@link #applyTo(ComplexHypixelObject)
     * pass through} the filter.
     * @see #include(String...)
     */
    public Set<String> getIncludedKeys() {
        return new HashSet<>(allowedKeys);
    }

    /**
     * {@link #applyTo(ComplexHypixelObject) Applies} the filter to all {@code objects} provided as
     * arguments.
     *
     * @throws IllegalArgumentException If no objects are provided, or if any of the objects are
     *                                  {@code null}.
     */
    public void applyTo(ComplexHypixelObject... objects) {
        if (objects == null || objects.length == 0) {
            throw new IllegalArgumentException("Batch filtering requires at least 1 object");
        }

        for (ComplexHypixelObject object : objects) {
            applyTo(object);
        }
    }

    /**
     * {@link #applyTo(ComplexHypixelObject) Applies} the filter to any {@code objects} iterated
     * over.
     *
     * @throws IllegalArgumentException If any object received during iteration is {@code null}.
     */
    public void applyTo(Iterable<ComplexHypixelObject> objects) {
        for (ComplexHypixelObject object : objects) {
            applyTo(object);
        }
    }

    /**
     * Strips the {@code object} of any properties that haven't explicitly been allowed via {@link
     * #include(String...)} or the {@link PropertyFilter#with(String...) with(...) constructor}.
     * <p><br>
     * The resulting object will (at most) only contain the properties returned by {@link
     * #getIncludedKeys()}. Any properties missing from the object will not be added.
     *
     * @throws IllegalArgumentException If the {@code object} is {@code null}.
     */
    public void applyTo(ComplexHypixelObject object) {
        if (object == null) {
            throw new IllegalArgumentException("Cannot filter null objects");
        }

        // Do nothing for empty objects.
        JsonObject json = object.getRaw();
        if (json.entrySet().isEmpty()) {
            return;
        }

        JsonObject temp = new JsonObject();
        for (String key : allowedKeys) {
            JsonElement value = object.getProperty(key);
            if (value == null) {
                // Ignore null properties.
                continue;
            }

            // Tokenize the path at un-escaped dots.
            // The negative-lookbehind ensures that dots are not preceded by a backslash.
            String[] tokens = key.split("(?<!\\\\)\\.");

            JsonObject parent = temp;
            for (int i = 0; i < tokens.length; i++) {
                if (i < tokens.length - 1) {
                    // Use the existing child object (if one exists).
                    JsonElement existingChild = parent.get(tokens[i]);
                    if (existingChild instanceof JsonObject) {
                        parent = (JsonObject) existingChild;
                        continue;
                    }

                    // Create a new child object if one doesn't exist.
                    JsonObject child = new JsonObject();
                    parent.add(tokens[i], child);
                    parent = child;
                } else {
                    // Set the final value of the property.
                    parent.add(tokens[i], value);
                }
            }
        }

        // Replace the contents of the original object.
        json.entrySet().clear();
        for (Entry<String, JsonElement> property : temp.entrySet()) {
            json.add(property.getKey(), property.getValue());
        }
    }
}
