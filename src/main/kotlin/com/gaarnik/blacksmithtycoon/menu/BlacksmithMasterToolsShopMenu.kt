package com.gaarnik.blacksmithtycoon.menu

import com.gaarnik.blacksmithtycoon.BlacksmithItems
import com.gaarnik.blacksmithtycoon.BlacksmithTycoonPlugin
import com.gaarnik.blacksmithtycoon.Color
import com.gaarnik.blacksmithtycoon.color
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.entity.Villager
import org.bukkit.inventory.ItemStack

class BlacksmithMasterToolsShopMenu(private val plugin: BlacksmithTycoonPlugin, private val villager: Villager?):
        AbstractMenu(villager, 27, MenuTitle.TOOLS_SHOP.toString()) {

    private enum class ItemsSlots(val slot: Int) {
        ORDERS_SIGN(11),
        BLACKSMITH_CRAFTING_TABLE(12),
        BLACKSMITH_ANVIL(13),
        BLACKSMITH_ENCHANTING_TABLE(14),
        SKILLS_BOOKSHELF(15),
        BACK(18)
    }

    override fun createMenu(player: Player): Array<MenuItem> {
        return arrayOf(
                MenuItem(
                        ItemsSlots.ORDERS_SIGN.slot,
                        ItemStack(Material.OAK_SIGN, 1),
                        "Orders Sign",
                        arrayOf(
                                "Get a list of orders you can apply to get more money".color(Color.GRAY),
                                "FREE".color(Color.GOLD)
                        )
                ),
                MenuItem(
                        ItemsSlots.BLACKSMITH_CRAFTING_TABLE.slot,
                        ItemStack(Material.CRAFTING_TABLE, 1),
                        "Blacksmith Crafting Table",
                        arrayOf(
                                "Used to craft wooden & stone weapons".color(Color.GRAY),
                                "FREE".color(Color.GOLD)
                        )
                ),
                MenuItem(
                        ItemsSlots.BLACKSMITH_ANVIL.slot,
                        ItemStack(Material.ANVIL, 1),
                        "Blacksmith Anvil",
                        arrayOf(
                                "Used to craft metal based weapons".color(Color.GRAY),
                                "1000 $".color(Color.GOLD)
                        )
                ),
                MenuItem(
                        ItemsSlots.BLACKSMITH_ENCHANTING_TABLE.slot,
                        ItemStack(Material.ENCHANTING_TABLE, 1),
                        "Blacksmith Enchanting Table",
                        arrayOf(
                                "Used to enchant weapons".color(Color.GRAY),
                                "1000 $".color(Color.GOLD)
                        )
                ),
                MenuItem(
                        ItemsSlots.SKILLS_BOOKSHELF.slot,
                        ItemStack(Material.BOOKSHELF, 1),
                        "Skills Bookshelf",
                        arrayOf(
                                "Check & Upgrade your blacksmith skills".color(Color.GRAY),
                                "100 $".color(Color.GOLD)
                        )
                ),
                MenuItem(
                        ItemsSlots.BACK.slot,
                        ItemStack(Material.STICK, 1),
                        "Back",
                        null
                )
        )
    }

    override fun onMenuItemClick(player: Player, slot: Int): Boolean {
        return when (slot) {
            ItemsSlots.ORDERS_SIGN.slot -> {
                buyItem(player, BlacksmithItems.ordersSign(), 0.0)
                true
            }
            ItemsSlots.BLACKSMITH_CRAFTING_TABLE.slot,
            ItemsSlots.BLACKSMITH_ANVIL.slot,
            ItemsSlots.BLACKSMITH_ENCHANTING_TABLE.slot,
            ItemsSlots.SKILLS_BOOKSHELF.slot -> {
                player.sendMessage("WIP")
                false
            }
            ItemsSlots.BACK.slot -> {
                BlacksmithMasterMainMenu(plugin, villager).open(player)
                false
            }
            else -> false
        }
    }

}