package com.gaarnik.blacksmithtycoon.crafting

import com.gaarnik.blacksmithtycoon.menu.*
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack



class CraftingMenu(private val player: Player?)
{
    private var inv: Inventory = Bukkit.createInventory(player, 54, MenuTitle.CRAFTING.toString())

    init {
        val emptyItem = ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1)
        var meta = emptyItem.itemMeta
        meta?.setDisplayName(" ")
        emptyItem.itemMeta = meta

        val outputItem = ItemStack(Material.BARRIER, 1)
        meta = outputItem.itemMeta
        meta?.setDisplayName("Output")
        outputItem.itemMeta = meta

        inv.setItem(OUTPUT_SLOT, outputItem)
        for (i in 0..53)
        {
            if (CraftingItemsSlot.values().any{it.slot == i}) continue
            if (i == OUTPUT_SLOT) continue
            inv.setItem(i, emptyItem)
        }
    }

    private enum class CraftingItemsSlot(val slot: Int) {
        CRAFTING1(11),
        CRAFTING2(12),
        CRAFTING3(13),
        CRAFTING4(20),
        CRAFTING5(21),
        CRAFTING6(22),
        CRAFTING7(29),
        CRAFTING8(30),
        CRAFTING9(31)
    }

    companion object {
        private const val OUTPUT_SLOT: Int = 25

        fun isCraftingSlot(slot: Int): Boolean {
            return CraftingItemsSlot.values().any{it.slot == slot}
        }

        fun isOutputSlot(slot: Int): Boolean {
            return slot == OUTPUT_SLOT
        }

        fun from(inv: Inventory, player: Player): CraftingMenu {
            val craftingMenu = CraftingMenu(player)
            craftingMenu.inv = inv
            return craftingMenu
        }
    }

    fun open(player: Player) {
        player.openInventory(inv)
    }

    fun getCraftingItems(): List<ItemStack> {
        val craftingItems = ArrayList<ItemStack?>()
        CraftingItemsSlot.values().forEach { craftingItems.add(inv.getItem(it.slot)) }
        return craftingItems.filterNotNull()
    }

    fun getOutputItem(): ItemStack? {
        val outputItem = inv.getItem(OUTPUT_SLOT)
        if (outputItem?.data?.itemType == Material.BARRIER) return null
        return outputItem
    }
}