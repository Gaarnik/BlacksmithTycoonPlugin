package com.gaarnik.blacksmithtycoon.listener

import com.gaarnik.blacksmithtycoon.BlacksmithItems
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class OrdersSignListener(): Listener {

    @EventHandler
    fun onPlaceSign(e: PlayerInteractEvent) {
        if (!BlacksmithItems.isItem(e.item, BlacksmithItems.ItemsName.ORDERS_SIGN)) return
        if (e.action != Action.RIGHT_CLICK_BLOCK) return

        val clickedBlock = e.clickedBlock ?: return
        val face = e.blockFace

        e.isCancelled = true

        val location = Location(
                clickedBlock.location.world,
                clickedBlock.location.x + face.modX,
                clickedBlock.location.y + face.modY,
                clickedBlock.location.z + face.modZ
        )

        //location.block.type = Material.OAK_SIGN
        location.block.type = Material.OAK_WALL_SIGN
    }

}