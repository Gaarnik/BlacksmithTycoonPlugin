package com.gaarnik.blacksmithtycoon.listener

import com.gaarnik.blacksmithtycoon.BlacksmithTycoonPlugin
import com.gaarnik.blacksmithtycoon.menu.AbstractMenu
import com.gaarnik.blacksmithtycoon.menu.BlacksmithMasterMainMenu
import com.gaarnik.blacksmithtycoon.menu.BlacksmithMasterToolsShopMenu
import com.gaarnik.blacksmithtycoon.menu.MenuTitle
import org.bukkit.entity.Villager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class CraftingMenuListener(private val plugin: BlacksmithTycoonPlugin): Listener {

    @EventHandler
    fun onInventoryClick(e: InventoryClickEvent) {
        if (e.view.title != MenuTitle.CRAFTING.toString()) return

        e.isCancelled = true
    }
}