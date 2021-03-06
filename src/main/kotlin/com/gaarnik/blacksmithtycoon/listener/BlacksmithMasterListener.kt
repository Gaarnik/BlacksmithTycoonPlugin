package com.gaarnik.blacksmithtycoon.listener

import com.gaarnik.blacksmithtycoon.BlacksmithMasterNPC
import com.gaarnik.blacksmithtycoon.BlacksmithTycoonPlugin
import com.gaarnik.blacksmithtycoon.menu.*
import org.bukkit.entity.Villager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityTargetEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEntityEvent

class BlacksmithMasterListener(private val plugin: BlacksmithTycoonPlugin): Listener {

    @EventHandler
    fun onDamage(e: EntityDamageEvent) {
        if (BlacksmithMasterNPC.isBlacksmithMasterNPC(e.entity)) {
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onTarget(e: EntityTargetEvent) {
        if (BlacksmithMasterNPC.isBlacksmithMasterNPC(e.target)) {
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onInteract(e: PlayerInteractEntityEvent) {
        if (!BlacksmithMasterNPC.isBlacksmithMasterNPC(e.rightClicked)) return

        e.isCancelled = true

        val master = BlacksmithMasterNPC.from(e.rightClicked)
        master.openMenu(plugin, e.player)
    }

    @EventHandler
    fun onInventoryClick(e: InventoryClickEvent) {
        val menu: AbstractMenu = when(e.view.title) {
            MenuTitle.BLACKSMITH_MASTER.toString() ->
                BlacksmithMasterMainMenu(plugin, e.inventory.holder as Villager)
            MenuTitle.TOOLS_SHOP.toString() ->
                BlacksmithMasterToolsShopMenu(plugin, e.inventory.holder as Villager)
            else -> return
        }

        e.isCancelled = true
        menu.onInventoryClick(e)
    }

}