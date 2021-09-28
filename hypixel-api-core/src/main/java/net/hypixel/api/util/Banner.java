package net.hypixel.api.util;

import com.google.gson.annotations.SerializedName;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A Minecraft-style banner design.
 * <p><br>
 *
 * <strong id="color_codes">Color Code Reference</strong>
 * <ul>
 *     <li>{@code 0}:  Black</li>
 *     <li>{@code 1}:  Red</li>
 *     <li>{@code 2}:  Green</li>
 *     <li>{@code 3}:  Brown</li>
 *     <li>{@code 4}:  Blue</li>
 *     <li>{@code 5}:  Purple</li>
 *     <li>{@code 6}:  Cyan</li>
 *     <li>{@code 7}:  Silver (Light Grey)</li>
 *     <li>{@code 8}:  Grey (Dark Grey)</li>
 *     <li>{@code 9}:  Pink</li>
 *     <li>{@code 10}: Lime</li>
 *     <li>{@code 11}: Yellow</li>
 *     <li>{@code 12}: Light Blue</li>
 *     <li>{@code 13}: Magenta</li>
 *     <li>{@code 14}: Orange</li>
 *     <li>{@code 15}: White</li>
 * </ul>
 * These numeric color codes are returned by the following methods:
 * <ul>
 *     <li>{@link #getBase()}</li>
 *     <li>{@link Pattern#getColor()}</li>
 * </ul>
 *
 * @see <a href="https://minecraft.fandom.com/wiki/Banner">Banner</a> (Minecraft Wiki)
 */
public class Banner {

    @SerializedName("Base")
    private String baseColor;
    @SerializedName("Patterns")
    private List<Pattern> patterns;

    /**
     * An integer (wrapped in a string) indicating the background/base color of the banner. See the
     * linked cheat-sheet for a list of possible values.
     *
     * @return the banner's background color.
     * @see <a href="#color_codes">Color code cheat-sheet</a>
     */
    public String getBaseColor() {
        return baseColor;
    }

    /**
     * @deprecated Renamed to {@link #getBaseColor()}.
     */
    @Deprecated
    public String getBase() {
        return getBaseColor();
    }

    /**
     * The shapes that compose the banner, minus the {@link #getBaseColor() base/background layer}.
     * Patterns in the list are ordered from background to foreground, meaning that the last pattern
     * in the list will always be displayed on top.
     *
     * @return an immutable list of the banner's layers.
     */
    public List<Pattern> getPatterns() {
        return patterns == null
            ? Collections.emptyList()
            : Collections.unmodifiableList(patterns);
    }

    @Override
    public String toString() {
        return "Banner{" +
               "baseColor='" + baseColor + '\'' +
               ", patterns=" + patterns +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Banner banner = (Banner) o;
        return Objects.equals(baseColor, banner.baseColor) &&
               Objects.equals(patterns, banner.patterns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseColor, patterns);
    }

    /**
     * A colored shape that makes up a layer of a {@link Banner} design.
     */
    public static class Pattern {

        @SerializedName("Pattern")
        private String type;
        @SerializedName("Color")
        private String color;

        /**
         * A short identifier indicating the shape to be used for the layer. See the link below for
         * each type's identifier.
         *
         * @return the pattern's type identifier.
         * @see <a href="https://minecraft.fandom.com/wiki/Banner/Patterns">Pattern identifiers</a>
         */
        public String getType() {
            return type;
        }

        /**
         * @deprecated Renamed to {@link #getType()}.
         */
        @Deprecated
        public String getPattern() {
            return getType();
        }

        /**
         * An integer (wrapped in a string) indicating the color used to draw the pattern's shape.
         * See the linked cheat-sheet for a list of possible values.
         *
         * @return the pattern's color.
         * @see Banner Color code cheat-sheet
         */
        public String getColor() {
            return color;
        }

        @Override
        public String toString() {
            return "Pattern{" +
                   "type='" + type + '\'' +
                   ", color='" + color + '\'' +
                   '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Pattern pattern = (Pattern) o;
            return Objects.equals(type, pattern.type) &&
                   Objects.equals(color, pattern.color);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, color);
        }
    }
}
