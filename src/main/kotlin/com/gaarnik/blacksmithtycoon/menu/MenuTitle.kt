package com.gaarnik.blacksmithtycoon.menu

enum class MenuTitle(private val value: String) {
    CRAFTING("Custom Crafting"),
    BLACKSMITH_MASTER("Blacksmith Master"),
    TOOLS_SHOP("Tools Shop"),
    ORDERS_SIGN("Available Orders");

    override fun toString(): String {
        return value
    }
}