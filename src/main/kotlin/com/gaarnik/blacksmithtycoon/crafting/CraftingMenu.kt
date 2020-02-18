package com.gaarnik.blacksmithtycoon.crafting

import com.gaarnik.blacksmithtycoon.BlacksmithTycoonPlugin
import com.gaarnik.blacksmithtycoon.menu.*
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.entity.Villager
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class CraftingMenu(private val plugin: BlacksmithTycoonPlugin, private val player: Player?)
{

    private var inv: Inventory = Bukkit.createInventory(player, 54, MenuTitle.CRAFTING.toString())

    private enum class ItemsSlots(val slot: Int) {
        CRAFTING1(11),
        CRAFTING2(12),
        CRAFTING3(13),
        CRAFTING4(20),
        CRAFTING5(21),
        CRAFTING6(22),
        CRAFTING7(29),
        CRAFTING8(30),
        CRAFTING9(31),
        OUTPUT(25)
    }

    fun open(player: Player) {
        val emptyItem = ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1)
        val meta = emptyItem.itemMeta
        meta?.setDisplayName(" ")
        emptyItem.itemMeta = meta

        for (i in 0..53)
        {
            if (ItemsSlots.values().any{it.slot == i}) continue
            inv.setItem(i, emptyItem)
        }

        player.openInventory(inv)
    }
}