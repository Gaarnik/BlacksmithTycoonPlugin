package com.gaarnik.blacksmithtycoon.menu

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

abstract class AbstractMenu(owner: InventoryHolder?, size: Int, title: String, items: Array<MenuItem>) {

    private var inv: Inventory = Bukkit.createInventory(owner, size, title)

    init {
        items.forEach {
            inv.setItem(it.slot, it.getStack())
        }
    }

    fun open(player: Player) {
        player.openInventory(inv)
    }

    fun onInventoryClick(e: InventoryClickEvent) {
        val close = onMenuItemClick(e.rawSlot)

        if (close) e.whoClicked.closeInventory()
    }

    abstract fun onMenuItemClick(slot: Int): Boolean

}