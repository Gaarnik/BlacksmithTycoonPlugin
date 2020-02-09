package com.gaarnik.blacksmithtycoon.menu

import org.bukkit.Material
import org.bukkit.entity.Villager
import org.bukkit.inventory.ItemStack

const val TITLE = "Blacksmith Master"

enum class ItemsSlots(val slot: Int) {
    REMOVE(26)
}

val items = arrayOf(
        MenuItem(ItemsSlots.REMOVE.slot, ItemStack(Material.NETHER_STAR, 1), "Remove")
)

class BlacksmithMasterMainInventory(private val villager: Villager?): AbstractMenu(villager, 27, TITLE, items) {

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
