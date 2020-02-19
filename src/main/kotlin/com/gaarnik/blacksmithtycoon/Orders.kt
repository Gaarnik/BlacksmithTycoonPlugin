package com.gaarnik.blacksmithtycoon

import org.bukkit.Material

class OrdersService {

    companion object {

        const val REFRESH_INTERVAL = 60000 // ms

        fun getOrders(level: Int): ArrayList<Order> {
            val orders = ArrayList<Order>()

            orders.add(Order(OrderType.WOODEN_SWORD, 5))
            orders.add(Order(OrderType.WOODEN_AXE, 2))
            orders.add(Order(OrderType.STONE_SWORD, 1))
            orders.add(Order(OrderType.STONE_AXE, 1))

            return orders
        }

    }

}

data class Order(val type: OrderType, var quantity: Int)

enum class OrderType(val level: Int, val displayName: String, val mat: Material, val money: Double) {
    WOODEN_SWORD(0, "Wooden Sword", Material.WOODEN_SWORD, 5.0),
    WOODEN_AXE(0, "Wooden Axe", Material.WOODEN_AXE, 5.0),
    STONE_SWORD(1, "Stone Axe", Material.STONE_AXE, 10.0),
    STONE_AXE(0, "Stone Axe", Material.STONE_AXE, 10.0);
}