package com.gaarnik.blacksmithtycoon.menu

import org.bukkit.inventory.ItemStack

class MenuItem(val slot: Int, private val stack: ItemStack, private val name: String) {

    fun getStack(): ItemStack {
        val meta = stack.itemMeta ?: return stack

        meta.setDisplayName(name)

        stack.itemMeta = meta

        return stack
    }

}
