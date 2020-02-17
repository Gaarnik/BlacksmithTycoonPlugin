package com.gaarnik.blacksmithtycoon

import com.gaarnik.blacksmithtycoon.listener.BlacksmithMasterListener
import com.gaarnik.blacksmithtycoon.listener.OrdersSignListener
import org.bukkit.Bukkit

class ListenersRegister {

    companion object {

        fun register(plugin: BlacksmithTycoonPlugin) {
            Bukkit.getPluginManager().registerEvents(BlacksmithMasterListener(plugin), plugin)
            Bukkit.getPluginManager().registerEvents(OrdersSignListener(), plugin)
        }

    }

}