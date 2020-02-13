package com.gaarnik.blacksmithtycoon.menu

import com.gaarnik.blacksmithtycoon.BlacksmithTycoonPlugin
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.entity.Villager
import org.bukkit.inventory.ItemStack

private const val TITLE = "Blacksmith Master"

private enum class ItemsSlots(val slot: Int) {
    PLAYER_MONEY(18),
    REMOVE(26)
}

class BlacksmithMasterMainMenu(private val plugin: BlacksmithTycoonPlugin, private val villager: Villager?): AbstractMenu(villager, 27, TITLE) {

    override fun createMenu(player: Player): Array<MenuItem> {
        val money = getMoney(player)

        return arrayOf(
                MenuItem(
                        ItemsSlots.PLAYER_MONEY.slot,
                        ItemStack(Material.GOLD_NUGGET, 1),
                        money.color(Color.GOLD)
                ),
                MenuItem(
                        ItemsSlots.REMOVE.slot,
                        ItemStack(Material.NETHER_STAR, 1),
                        "Remove".color(Color.DARK_RED)
                )
        )
    }

    override fun onMenuItemClick(player: Player, slot: Int): Boolean {
        if (slot == ItemsSlots.REMOVE.slot) {
            villager?.remove()
            return true
        }

        return false
    }

    private fun getMoney(player: Player): String {
        val balance = plugin.economy?.getBalance(player) ?: 0.0
        val currency = plugin.economy?.currencyNamePlural() ?: "$"

        return String.format("%d %s", balance.toInt(), currency)
    }

    companion object {

        fun isInv(title: String): Boolean {
            return title == TITLE
        }

    }

}
