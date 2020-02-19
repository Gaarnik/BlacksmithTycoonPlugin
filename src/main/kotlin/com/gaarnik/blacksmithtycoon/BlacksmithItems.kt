package com.gaarnik.blacksmithtycoon

import de.tr7zw.changeme.nbtapi.NBTItem
import org.bukkit.Material
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

class BlacksmithItems {

    enum class ItemsName(private val value: String) {
        ORDERS_SIGN("orders-sign");

        override fun toString(): String {
            return value
        }
    }

    companion object {

        private const val ITEM_KEY = "blacksmith-item"

        fun ordersSign(): ItemStack {
            val item = ItemStack(Material.OAK_SIGN, 1)

            val meta = item.itemMeta
            meta?.setDisplayName("Orders Sign")
            meta?.isUnbreakable = true
            meta?.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE)
            item.itemMeta = meta

            val nbt = NBTItem(item)
            nbt.setString(ITEM_KEY, ItemsName.ORDERS_SIGN.toString())

            return nbt.item
        }

        fun isItem(item: ItemStack?, name: ItemsName): Boolean {
            if (item == null) return false

            val nbt = NBTItem(item)

            return nbt.getString(ITEM_KEY) == name.toString()
        }

    }

}