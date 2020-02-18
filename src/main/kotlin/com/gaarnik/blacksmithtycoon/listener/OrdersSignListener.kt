package com.gaarnik.blacksmithtycoon.listener

import com.gaarnik.blacksmithtycoon.BlacksmithItems
import com.gaarnik.blacksmithtycoon.BlacksmithPlayer
import com.gaarnik.blacksmithtycoon.menu.OrdersMenu
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class OrdersSignListener(): Listener {

    @EventHandler
    fun onPlace(e: PlayerInteractEvent) {
        if (!BlacksmithItems.isItem(e.item, BlacksmithItems.ItemsName.ORDERS_SIGN)) return
        if (e.action != Action.RIGHT_CLICK_BLOCK) return

        val clickedBlock = e.clickedBlock ?: return
        val face = e.blockFace

        if (face == BlockFace.DOWN) return

        e.isCancelled = true

        val location = Location(
                clickedBlock.location.world,
                clickedBlock.location.x + face.modX,
                clickedBlock.location.y + face.modY,
                clickedBlock.location.z + face.modZ
        )

        if (face == BlockFace.UP)
            location.block.type = Material.OAK_WALL_SIGN
        else
            location.block.type = Material.OAK_SIGN

        // add meta data ou block data to identify orders sign
    }

    @EventHandler
    fun onInteract(e: PlayerInteractEvent) {
        if (e.action != Action.RIGHT_CLICK_BLOCK) return

        val clickedBlock = e.clickedBlock ?: return
        if (clickedBlock.type != Material.OAK_SIGN && clickedBlock.type != Material.OAK_WALL_SIGN) return

        // check meta data or block data to identity orders sign

        OrdersMenu(e.player).open(e.player)
    }

}