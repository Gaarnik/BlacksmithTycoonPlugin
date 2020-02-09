package com.gaarnik.blacksmithtycoon;

import de.tr7zw.nbtinjector.NBTInjector
import org.bukkit.plugin.java.JavaPlugin

class BlacksmithTycoonPlugin(): JavaPlugin() {

    override fun onLoad() {
        NBTInjector.inject()
    }

    override fun onEnable() {
        CommandsRegister.register(this)
        ListenersRegister.register(this)
    }
    
    override fun onDisable() {}

}
