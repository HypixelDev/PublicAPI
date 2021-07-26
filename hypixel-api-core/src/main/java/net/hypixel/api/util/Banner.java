package net.hypixel.api.util;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Banner {

    @SerializedName("Base")
    private String base;
    @SerializedName("Patterns")
    private List<Pattern> patterns;

    public String getBase() {
        return base;
    }

    public List<Pattern> getPatterns() {
        return patterns;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "base='" + base + '\'' +
                ", patterns=" + patterns +
                '}';
    }

    public static class Pattern {
        @SerializedName("Pattern")
        private String pattern;
        @SerializedName("Color")
        private String color;

        public String getPattern() {
            return pattern;
        }

        public String getColor() {
            return color;
        }

        @Override
        public String toString() {
            return "Pattern{" +
                    "pattern='" + pattern + '\'' +
                    ", color='" + color + '\'' +
                    '}';
        }
    }
}
