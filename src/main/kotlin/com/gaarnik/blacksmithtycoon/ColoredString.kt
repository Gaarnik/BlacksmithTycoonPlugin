package com.gaarnik.blacksmithtycoon

enum class Color(private val value: String) {

    BLACK("§0"),
    DARK_BLUE("1"),
    DARK_GREEN("§2"),
    DARK_AQUA("§3"),
    DARK_RED("§4"),
    DARK_PURPLE("§5"),
    GOLD("§6"),
    GRAY("§7"),
    DARK_GRAY("§8"),
    BLUE("§9"),
    GREEN("§a"),
    AQUA("§b"),
    RED("§c"),
    LIGHT_PURPLE("§d"),
    YELLOW("§e"),
    WHITE("§f");

    override fun toString(): String {
        return value
    }
}

fun String.color(color: Color): String {
    return String.format("%s%s", color.toString(), this)
}