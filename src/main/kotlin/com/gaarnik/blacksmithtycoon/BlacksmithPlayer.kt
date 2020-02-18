package com.gaarnik.blacksmithtycoon

import org.bukkit.entity.Player

class BlacksmithPlayer(val player: Player) {

    private val data = BlacksmithPlayerData(player.uniqueId.toString())

    private var orders = ArrayList<Order>()
    private var lastOrdersUpdate: Long = 0

    companion object {

        fun from(player: Player): BlacksmithPlayer {
            return BlacksmithPlayer(player)
        }

    }

    fun getOrders(): ArrayList<Order> {
        val currentTime = System.currentTimeMillis()

        if (currentTime - lastOrdersUpdate >= OrdersService.REFRESH_INTERVAL) {
            lastOrdersUpdate = currentTime

            orders = OrdersService.getOrders(data.level)
        }

        return orders
    }

}

data class BlacksmithPlayerData(private val id: String) {
    val level = 0
}