package com.gaarnik.blacksmithtycoon.menu

import com.gaarnik.blacksmithtycoon.BlacksmithTycoonPlugin
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.entity.Villager
import org.bukkit.inventory.ItemStack

private const val TITLE = "Blacksmith Master"

class BlacksmithMasterMainMenu(private val plugin: BlacksmithTycoonPlugin, private val villager: Villager?):
        AbstractMenu(villager, 27, TITLE) {

    private enum class ItemsSlots(val slot: Int) {
        TOOLS_SHOP(12),
        PLAYER_MONEY(18),
        REMOVE(26)
    }

    override fun createMenu(player: Player): Array<MenuItem> {
        val money = getMoney(player)

        return arrayOf(
                MenuItem(
                        ItemsSlots.TOOLS_SHOP.slot,
                        ItemStack(Material.ANVIL, 1),
                        "Tools Shop",
                        arrayOf("Buy tools to craft weapons".color(Color.GRAY))
                ),
                MenuItem(
                        ItemsSlots.PLAYER_MONEY.slot,
                        ItemStack(Material.GOLD_NUGGET, 1),
                        money.color(Color.GOLD),
                        null
                ),
                MenuItem(
                        ItemsSlots.REMOVE.slot,
                        ItemStack(Material.NETHER_STAR, 1),
                        "Remove".color(Color.DARK_RED),
                        null
                )
        )
    }

    override fun onMenuItemClick(player: Player, slot: Int): Boolean {
        return when (slot) {
            ItemsSlots.TOOLS_SHOP.slot -> {
                BlacksmithMasterToolsShopMenu(plugin, villager).open(player)
                false
            }
            ItemsSlots.REMOVE.slot -> {
                villager?.remove()
                true
            }
            else -> false
        }
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
