package com.gaarnik.blacksmithtycoon.listener

import com.gaarnik.blacksmithtycoon.BlacksmithTycoonPlugin
import com.gaarnik.blacksmithtycoon.crafting.CraftingMenu
import com.gaarnik.blacksmithtycoon.menu.MenuTitle
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryMoveItemEvent
import org.bukkit.event.inventory.InventoryType

class CraftingMenuListener(private val plugin: BlacksmithTycoonPlugin): Listener {

    @EventHandler
    fun onInventoryClick(e: InventoryClickEvent) {
        if (e.view.title != MenuTitle.CRAFTING.toString()) return

        val inv = e.clickedInventory ?: return
        val playerInventory = inv.type == InventoryType.PLAYER
        val player = e.whoClicked

        if (playerInventory) return
        if (player !is Player) return

        e.isCancelled = true

        if (!CraftingMenu.isCraftingSlot(e.slot) && !CraftingMenu.isOutputSlot(e.slot)) {
            return
        }

        val craftingInv = CraftingMenu.from(inv, player)

        if (CraftingMenu.isCraftingSlot(e.slot)) {
            val currentItem = inv.getItem(e.slot)
            if (e.isLeftClick) {
                inv.setItem(e.slot, player.itemOnCursor)
                player.setItemOnCursor(currentItem)
            }
            if (e.isRightClick)
            {
                if (currentItem != null)
                {
                    if (currentItem.data?.itemType == player.itemOnCursor?.data?.itemType)
                    {
                        if (currentItem.amount < currentItem.maxStackSize) {
                            currentItem.amount += 1
                            player.itemOnCursor.amount -= 1
                            if (player.itemOnCursor.amount == 0) {
                                player.setItemOnCursor(null)
                            }
                        }
                    }
                    else
                    {
                        inv.setItem(e.slot, player.itemOnCursor)
                        player.setItemOnCursor(currentItem)
                    }
                }
                else
                {
                    val droppedItem = player.itemOnCursor.clone()
                    droppedItem.amount = 1
                    inv.setItem(e.slot, droppedItem)
                    player.itemOnCursor.amount -= 1
                    if (player.itemOnCursor.amount == 0) {
                        player.setItemOnCursor(null)
                    }
                }
            }
            player.updateInventory()
            craftingInv.testCraft()
        }

        if (!CraftingMenu.isOutputSlot(e.slot)) return

        val outputItem = craftingInv.getOutputItem()
        Bukkit.broadcastMessage(outputItem.toString())
        if (outputItem == null) {
            return
        }
        player.setItemOnCursor(outputItem)
        craftingInv.doCraft()
        player.updateInventory()
    }

    /*
    @EventHandler
    fun onInventoryItemMove(e: InventoryMoveItemEvent) {
        val inv = e.destination
        val playerInventory = inv.type == InventoryType.PLAYER
        val player = inv.viewers[0] // Only the player can see the inventory

        Bukkit.broadcastMessage("Inventory move item event")
        Bukkit.broadcastMessage("$playerInventory --- $player")

        if (playerInventory) return
        if (player !is Player) return

        val craftingInv = CraftingMenu.from(inv, player)
        craftingInv.testCraft()
    }
    */

    @EventHandler
    fun onInventoryClose(e: InventoryCloseEvent) {
        val player = e.player
        if (e.view.title != MenuTitle.CRAFTING.toString()) return
        if (player !is Player) return

        val craftingMenu = CraftingMenu.from(e.inventory, player)
        craftingMenu.getCraftingItems().filterNotNull().forEach{
            val exceed = player.inventory.addItem(it).values.firstOrNull()
            if (exceed != null) {
                player.world.dropItem(player.location, exceed)
            }
        }
    }
}