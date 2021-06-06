package net.hypixel.api.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Objects;
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

    // Only these keys are allowed in objects returned from `applyTo(...)`.
    private final Set<PropertyKey> allowedKeys;

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
     * Any duplicate keys, and keys already included in the filter, will not be added. If one key
     * begins with the entirety of another key, and has more tokens / parts after that, the two keys
     * are considered to be colliding. Some notes about key collision:
     * <pre>
     *     • New keys always take precedence when collision happens, such that...
     *     • If an added key has a <u>wider scope</u> (fewer tokens) than any existing keys, all
     *       colliding keys with narrower scopes (more tokens) will be removed, and the new key will
     *       be added.
     *     • If the added key has a <u>narrower scope</u> (more tokens) than an existing key, the
     *       colliding key with a wider scope (fewer tokens) will be replaced with the new one.
     * </pre>
     * An example of collision would be adding {@code "stats.SkyWars.coins"} when {@code
     * "stats.SkyWars"} is already included. In that case, the added key would replace the existing
     * key, so that only {@code "stats.SkyWars.coins"} is included when filtering.
     *
     * @param keys Names of properties that will be allowed to pass through the filter (in
     *             dot-notation).
     */
    public void include(String... keys) {
        if (keys == null) {
            throw new IllegalArgumentException("Cannot include null property keys");
        }

        // Check for key collisions.
        for (String rawKey : keys) {
            if (rawKey == null) {
                throw new IllegalArgumentException("Cannot include null property keys");
            }

            PropertyKey key = new PropertyKey(rawKey);
            boolean shouldAddKey = true;
            Iterator<PropertyKey> existingKeys = allowedKeys.iterator();

            while (existingKeys.hasNext()) {
                PropertyKey existingKey = existingKeys.next();

                // Ignore duplicate keys.
                if (existingKey.equals(key)) {
                    shouldAddKey = false;
                    break;
                }

                // Check if the new key collides with the existing key's scope.
                if (key.isExtendedBy(existingKey)) {
                    // Replace & continue, since there can be multiple keys with narrower scopes.
                    existingKeys.remove();
                } else if (existingKey.isExtendedBy(key)) {
                    // Replace & break, since only 1 key should possibly have a wider scope.
                    existingKeys.remove();
                    break;
                }
            }

            if (shouldAddKey) {
                allowedKeys.add(key);
            }
        }
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
        if (keys == null) {
            throw new IllegalArgumentException("Cannot remove null keys");
        }

        for (String key : keys) {
            if (key == null) {
                throw new IllegalArgumentException("Cannot remove null keys");
            }
            allowedKeys.removeIf(existingKey -> existingKey.toString().equals(key));
        }
    }

    /**
     * @return A new set containing all property keys that can {@link #applyTo(ComplexHypixelObject)
     * pass through} the filter.
     * @see #include(String...)
     */
    public Set<String> getIncludedKeys() {
        Set<String> fullKeys = new HashSet<>(allowedKeys.size());
        for (PropertyKey key : allowedKeys) {
            fullKeys.add(key.toString());
        }
        return fullKeys;
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
        for (PropertyKey key : allowedKeys) {
            JsonElement value = object.getProperty(key.toString());
            if (value == null) {
                // Ignore null properties.
                continue;
            }

            // Create any required parents for the property, similar to File#mkdirs().
            JsonObject parent = temp;
            String[] tokens = key.tokens;
            for (int i = 0; i < tokens.length; i++) {
                String token = tokens[i];
                String escapedToken = token.replace("\\.", ".");

                if (i < tokens.length - 1) {

                    // Use the existing child object (if one exists).
                    JsonElement existingChild = parent.get(token);
                    if (existingChild instanceof JsonObject) {
                        parent = (JsonObject) existingChild;
                        continue;
                    }

                    // Create a new child object if one doesn't exist.
                    JsonObject child = new JsonObject();
                    parent.add(escapedToken, child);
                    parent = child;
                } else {
                    // Set the final value of the property.
                    parent.add(escapedToken, value);
                }
            }
        }

        // Replace the contents of the original object.
        json.entrySet().clear();
        for (Entry<String, JsonElement> property : temp.entrySet()) {
            json.add(property.getKey(), property.getValue());
        }
    }

    /**
     * The key of one of a {@link ComplexHypixelObject}'s properties, potentially one nested inside
     * multiple other objects.
     */
    private static final class PropertyKey {

        // The key's full stringified form. Literal dots (.) should still have escape characters.
        final String full;

        // Each "part" of the key, delimited by un-escaped dots in the `full` key.
        final String[] tokens;

        PropertyKey(String full) {
            if (full == null) {
                throw new IllegalArgumentException("Property key cannot be null");
            }
            this.full = full;

            // Tokenize the key at un-escaped dots.
            // The negative-lookbehind ensures that dots are not preceded by a backslash.
            tokens = full.split("(?<!\\\\)\\.");
        }

        /**
         * @return {@code true} if the {@code other} key starts with all of this key's {@link
         * #tokens} & has additional tokens at the end, otherwise {@code false}.
         */
        boolean isExtendedBy(PropertyKey other) {
            String otherFull = other.full;
            int extensionIndex = full.length();

            // (1) `other` cannot possibly be an extension if it has a shorter or equal length.
            // (2) Check that the key continues immediately after extensionIndex.
            // (3) Check that the dot (.) we found in (2) wasn't escaped.
            // (4) Check that the other key starts with this entire key.
            return otherFull.length() > full.length()
                   && otherFull.charAt(extensionIndex) == '.'
                   && otherFull.charAt(extensionIndex - 1) != '\\'
                   && otherFull.startsWith(full);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null) {
                return false;
            }
            if (o instanceof PropertyKey) {
                return full.equals(((PropertyKey) o).full);
            }
            if (o instanceof String) {
                return full.equals(o);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(full);
        }

        @Override
        public String toString() {
            return full;
        }
    }
}
