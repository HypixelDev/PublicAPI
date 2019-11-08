package net.hypixel.api.util;

import java.awt.*;

public enum MinecraftColor {
    BLACK(new Color(0x0)),
    DARK_BLUE(new Color(0xAA)),
    DARK_GREEN(new Color(0xAA00)),
    DARK_AQUA(new Color(0xAAAA)),
    DARK_RED(new Color(0xAA0000)),
    DARK_PURPLE(new Color(0xAA00AA)),
    GOLD(new Color(0xFFAA00)),
    GRAY(new Color(0xAAAAAA)),
    DARK_GRAY(new Color(0x555555)),
    BLUE(new Color(0x5555FF)),
    GREEN(new Color(0x55FF55)),
    AQUA(new Color(0x55FFFF)),
    RED(new Color(0xFF5555)),
    LIGHT_PURPLE(new Color(0xFF55FF)),
    YELLOW(new Color(0xFFFF55)),
    WHITE(new Color(0xFFFFFF));

    private final Color color;

    public Color getColor() {
        return color;
    }

    MinecraftColor(Color color) {
        this.color = color;
    }
}
