package com.gaarnik.blacksmithtycoon.listener

import com.gaarnik.blacksmithtycoon.BlacksmithTycoonPlugin
import com.gaarnik.blacksmithtycoon.crafting.CraftingMenu
import com.gaarnik.blacksmithtycoon.menu.MenuTitle
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryType

class CraftingMenuListener(private val plugin: BlacksmithTycoonPlugin): Listener {

    @EventHandler
    fun onInventoryClick(e: InventoryClickEvent) {
        if (e.view.title != MenuTitle.CRAFTING.toString()) return
        val playerInventory = e.clickedInventory?.type == InventoryType.PLAYER

        if (!playerInventory) {
            if (!CraftingMenu.isCraftingSlot(e.slot)) {
                e.isCancelled = true
            }
        }
    }

    @EventHandler
    fun onInventoryClose(e: InventoryCloseEvent) {
        val player = e.player
        if (e.view.title != MenuTitle.CRAFTING.toString()) return
        if (player !is Player) return
        val craftingMenu = CraftingMenu.from(e.inventory, player)
        craftingMenu.getCraftingItems().forEach{
            val exceed = player.inventory.addItem(it).values.firstOrNull()
            if (exceed != null) {
                player.world.dropItem(player.location, exceed)
            }
        }
    }
}