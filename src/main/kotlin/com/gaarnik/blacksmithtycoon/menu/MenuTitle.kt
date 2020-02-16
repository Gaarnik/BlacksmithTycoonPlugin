package com.gaarnik.blacksmithtycoon.menu

enum class MenuTitle(private val value: String) {
    BLACKSMITH_MASTER("Blacksmith Master"),
    TOOLS_SHOP("Tools Shop");

    override fun toString(): String {
        return value
    }
}