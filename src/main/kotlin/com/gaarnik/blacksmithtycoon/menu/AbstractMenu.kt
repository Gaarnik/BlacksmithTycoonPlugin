package com.gaarnik.blacksmithtycoon.menu

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

abstract class AbstractMenu(owner: InventoryHolder?, size: Int, title: String) {

    private var inv: Inventory = Bukkit.createInventory(owner, size, title)

    fun open(player: Player) {
        val items = createMenu(player)

        items.forEach {
            inv.setItem(it.slot, it.getStack())
        }

        player.openInventory(inv)
    }

    fun onInventoryClick(e: InventoryClickEvent) {
        val player = e.whoClicked
        if (player !is Player) return
        val close = onMenuItemClick(player, e.rawSlot)

        if (close) player.closeInventory()
    }

    fun buyItem(player: Player, item: ItemStack, price: Double) {
        player.inventory.addItem(item)
    }

    abstract fun createMenu(player: Player): Array<MenuItem>

    abstract fun onMenuItemClick(player: Player, slot: Int): Boolean

}