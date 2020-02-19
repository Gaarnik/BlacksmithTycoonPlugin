package com.gaarnik.blacksmithtycoon.menu

import com.gaarnik.blacksmithtycoon.BlacksmithPlayer
import com.gaarnik.blacksmithtycoon.Color
import com.gaarnik.blacksmithtycoon.color
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class OrdersMenu(player: Player):
        AbstractMenu(player, 27, MenuTitle.ORDERS_SIGN.toString()) {

    override fun createMenu(player: Player): Array<MenuItem> {
        val blacksmithPlayer = BlacksmithPlayer.from(player) // use service to manage instances
        val orders = blacksmithPlayer.getOrders()
        val ordersList = ArrayList<MenuItem>()
        var slot = 10

        orders.forEach {
            ordersList.add(
                    MenuItem(
                            slot,
                            ItemStack(it.type.mat, it.quantity),
                            it.type.displayName,
                            arrayOf(
                                    "${it.type.money} $".color(Color.GOLD)
                            )
                    )
            )

            slot++
        }

        return ordersList.toTypedArray()
    }

    override fun onMenuItemClick(player: Player, slot: Int): Boolean {
        return true
    }

}