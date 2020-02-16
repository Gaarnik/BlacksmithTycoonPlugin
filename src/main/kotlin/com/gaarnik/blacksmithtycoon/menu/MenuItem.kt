package com.gaarnik.blacksmithtycoon.menu

import org.bukkit.inventory.ItemStack

class MenuItem(val slot: Int, private val stack: ItemStack, private val name: String, private val description: Array<String>?) {

    fun getStack(): ItemStack {
        val meta = stack.itemMeta ?: return stack

        meta.setDisplayName(name)
        meta.lore = description?.toList()

        stack.itemMeta = meta

        return stack
    }

}
