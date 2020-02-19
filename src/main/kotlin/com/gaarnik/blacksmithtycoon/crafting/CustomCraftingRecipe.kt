package com.gaarnik.blacksmithtycoon.crafting

import org.bukkit.Bukkit
import org.bukkit.inventory.ItemStack

class CustomCraftingRecipe(private val itemsNeeded: ArrayList<ItemStack?>, var output: ItemStack) {

    /*
    init {
        Bukkit.broadcastMessage(output.data?.itemType?.name.toString())
    }

    fun getOutput() : ItemStack {
        return output
    }

    fun setOutput(item: ItemStack) {
        Bukkit.broadcastMessage(item.data?.itemType?.name.toString())
        output = item
        Bukkit.broadcastMessage(output.data?.itemType?.name.toString())
    }
*/
    fun testRecipe(items: ArrayList<ItemStack?>): Boolean {
        Bukkit.broadcastMessage("==========")
        for (i in 0..8) {
            Bukkit.broadcastMessage(i.toString() + ": " + items[i]?.data?.itemType?.name + " // " + itemsNeeded[i]?.data?.itemType?.name)
            if (items[i] != itemsNeeded[i]) {
                Bukkit.broadcastMessage("Invalid")
                return false
            }
        }
        Bukkit.broadcastMessage("Valid")
        return true
    }

}