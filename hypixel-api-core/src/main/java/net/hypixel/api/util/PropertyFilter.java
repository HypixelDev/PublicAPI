package net.hypixel.api.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A tool for trimming unneeded properties from data, especially to minimize their memory and
 * storage consumption. Based on MongoDB projections.
 * <p><br>
 * To use an <strong>inclusion filter</strong>, property names (or "keys") can be added via {@link
 * #include(String...) include(...)} or the {@link #including(String...) including(...)
 * constructor}. When an object is passed through the filter, any properties not explicitly named
 * using the aforementioned methods will be removed from the object. If the object did not have an
 * included property to begin with, it will not be created.
 * <p><br>
 * Property names are referenced using dot-notation. See the documentation for {@link
 * #include(String...) include(...)} for more details.
 */
public class PropertyFilter {

    /**
     * Shorthand for constructing a new filter that only allows the {@code includedKeys} to pass
     * through. See {@link #include(String...)} for the key syntax.
     */
    public static PropertyFilter including(String... includedKeys) {
        PropertyFilter filter = new PropertyFilter();
        filter.include(includedKeys);
        return filter;
    }

    // Only these keys are allowed in objects passed through.
    protected final Set<PropertyKey> allowedKeys;

    public PropertyFilter() {
        allowedKeys = new HashSet<>();
    }

    /**
     * Allows properties with any of the provided {@code keys} to pass through the filter. To
     * include nested properties, use dots ({@code .}) to separate each parent property from its
     * child. If a property's name contains a dot literally, use a double-backslash to escape the
     * dot. (e.g. {@code "key_with_literal_dot\\.in_it"} instead of {@code
     * "key_with_literal_dot.in_it"})
     * <br><pre>
     * Examples:
     *     •{@code uuid}                - Keep the player's UUID when filtering.
     *     •{@code stats}               - Keep all of the player's stats when filtering.
     *     •{@code stats.SkyWars}       - Keep all of the player's SkyWars stats when filtering.
     *     •{@code stats.SkyWars.coins} - Keep just the player's SkyWars coins when filtering.
     * </pre>
     * If an added key conflicts with an existing one, the newer key takes precedence.
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
     * Removes all of the provided keys from the filter, such that objects passed through the filter
     * will <em>not</em> include properties with those keys.
     * <p><br>
     * Attempting to remove a key that was already removed, or never {@link #include(String...)
     * included} to begin with, will have no effect.
     */
    public void remove(String... keys) {
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
     * @return A new set containing all property keys that can pass through the filter.
     * @see #include(String...)
     */
    public Set<String> getIncluded() {
        return allowedKeys.stream()
                .map(PropertyKey::toString)
                .collect(Collectors.toSet());
    }

    /**
     * The key a property in an object, potentially one nested inside multiple other objects.
     */
    protected static final class PropertyKey {

        // The key's full stringified form. Literal dots (.) should still have escape characters.
        final String full;

        // Each "part" of the key, delimited by un-escaped dots in the `full` key.
        final String[] tokens;

        PropertyKey(String full) {
            if (full == null) {
                throw new IllegalArgumentException("Property key cannot be null");
            }
            this.full = full;
            tokens = Utilities.tokenizeKey(full);
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
