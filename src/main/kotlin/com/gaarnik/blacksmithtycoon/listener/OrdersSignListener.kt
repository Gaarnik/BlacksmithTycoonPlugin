package com.gaarnik.blacksmithtycoon.listener

import com.gaarnik.blacksmithtycoon.BlacksmithItems
import com.gaarnik.blacksmithtycoon.menu.AbstractMenu
import com.gaarnik.blacksmithtycoon.menu.MenuTitle
import com.gaarnik.blacksmithtycoon.menu.OrdersMenu
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.block.Sign
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.inventory.InventoryClickEvent
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

        // TODO fix sign orientation
        if (face == BlockFace.UP) {
            location.block.type = Material.OAK_SIGN

            val sign = location.block.state as? Sign ?: return
            val signData = sign.blockData as? org.bukkit.block.data.type.Sign ?: return
            if (e.player.facing == BlockFace.UP || e.player.facing == BlockFace.DOWN)
                signData.rotation = BlockFace.NORTH
            else
                signData.rotation = e.player.facing.oppositeFace
        }
        else {
            location.block.type = Material.OAK_WALL_SIGN

            val signData = location.block.blockData as? org.bukkit.block.data.type.WallSign ?: return
            if (e.player.facing == BlockFace.DOWN)
                signData.facing = BlockFace.NORTH
            else
                signData.facing = e.player.facing.oppositeFace
        }

    }

    @EventHandler
    fun onInteract(e: PlayerInteractEvent) {
        if (e.action != Action.RIGHT_CLICK_BLOCK) return

        val clickedBlock = e.clickedBlock ?: return
        if (clickedBlock.type != Material.OAK_SIGN && clickedBlock.type != Material.OAK_WALL_SIGN) return

        // TODO check meta data or block data to identity orders sign

        OrdersMenu(e.player).open(e.player)
    }

    @EventHandler
    fun onInventoryClick(e: InventoryClickEvent) {
        val player = e.whoClicked as? Player ?: return

        val menu: AbstractMenu = when(e.view.title) {
            MenuTitle.ORDERS_SIGN.toString() ->
                OrdersMenu(player)
            else -> return
        }

        e.isCancelled = true
        menu.onInventoryClick(e)
    }

}