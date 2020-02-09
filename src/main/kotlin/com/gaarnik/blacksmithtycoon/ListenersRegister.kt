package com.gaarnik.blacksmithtycoon

import com.gaarnik.blacksmithtycoon.listener.BlacksmithMasterListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class ListenersRegister {

    companion object {

        fun register(plugin: JavaPlugin) {
            Bukkit.getPluginManager().registerEvents(BlacksmithMasterListener(), plugin)
        }

    }

}