package com.gaarnik.blacksmithtycoon.menu

import org.bukkit.Material
import org.bukkit.entity.Villager
import org.bukkit.inventory.ItemStack

private const val TITLE = "Blacksmith Master"

private enum class ItemsSlots(val slot: Int) {
    REMOVE(26),
    SOMETHING(18)
}

private val items = arrayOf(
        MenuItem(ItemsSlots.REMOVE.slot, ItemStack(Material.NETHER_STAR, 1), "Remove"),
        MenuItem(ItemsSlots.SOMETHING.slot, ItemStack(Material.GOLD_NUGGET, 1), "0$")
)

class BlacksmithMasterMainMenu(private val villager: Villager?): AbstractMenu(villager, 27, TITLE, items) {

    override fun onMenuItemClick(slot: Int): Boolean {
        if (slot == ItemsSlots.REMOVE.slot) {
            villager?.remove()
            return true
        }

        return false
    }

    companion object {

        fun isInv(title: String): Boolean {
            return title == TITLE
        }

    }

}
