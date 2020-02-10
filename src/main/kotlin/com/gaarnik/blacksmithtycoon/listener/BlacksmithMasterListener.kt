package com.gaarnik.blacksmithtycoon.listener

import com.gaarnik.blacksmithtycoon.BlacksmithMasterNPC
import com.gaarnik.blacksmithtycoon.menu.BlacksmithMasterMainMenu
import org.bukkit.entity.Villager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEntityEvent

class BlacksmithMasterListener: Listener {

    @EventHandler
    fun onDamage(e: EntityDamageEvent) {
        if (BlacksmithMasterNPC.isBlacksmithMasterNPC(e.entity)) {
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onInteract(e: PlayerInteractEntityEvent) {
        if (!BlacksmithMasterNPC.isBlacksmithMasterNPC(e.rightClicked)) return

        e.isCancelled = true

        val master = BlacksmithMasterNPC.from(e.rightClicked)
        master.openMenu(e.player)
    }

    @EventHandler
    fun onInventoryClick(e: InventoryClickEvent) {
        if (BlacksmithMasterMainMenu.isInv(e.view.title)) {
            e.isCancelled = true

            val menu = BlacksmithMasterMainMenu(e.inventory.holder as Villager)
            menu.onInventoryClick(e)
        }
    }

}