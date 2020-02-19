package com.gaarnik.blacksmithtycoon

import org.bukkit.entity.Player

class BlacksmithPlayer(val player: Player, val data: BlacksmithPlayerData) {

    private var orders = ArrayList<Order>()
    private var lastOrdersUpdate: Long = 0

    fun getOrders(): ArrayList<Order> {
        val currentTime = System.currentTimeMillis()

        if (currentTime - lastOrdersUpdate >= OrdersService.REFRESH_INTERVAL) {
            lastOrdersUpdate = currentTime

            orders = OrdersService.getOrders(data.level)
        }

        return orders
    }

}

data class BlacksmithPlayerData(val uuid: String, val level: Int)