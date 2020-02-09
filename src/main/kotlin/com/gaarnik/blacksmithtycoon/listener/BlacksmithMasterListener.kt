package com.gaarnik.blacksmithtycoon.listener

import com.gaarnik.blacksmithtycoon.BlacksmithMasterNPC
import com.gaarnik.blacksmithtycoon.menu.BlacksmithMasterMainInventory
import org.bukkit.Bukkit
import org.bukkit.entity.Villager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.inventory.InventoryHolder

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
        master.openInv(e.player)
    }

    @EventHandler
    fun onInventoryClick(e: InventoryClickEvent) {
        if (BlacksmithMasterMainInventory.isInv(e.view.title)) {
            e.isCancelled = true

            val inv = BlacksmithMasterMainInventory(e.inventory.holder as Villager)
            inv.onInventoryClick(e)
        }
    }

}