package com.gaarnik.blacksmithtycoon

import de.tr7zw.nbtinjector.NBTInjector
import net.milkbowl.vault.economy.Economy
import org.bukkit.plugin.java.JavaPlugin

class BlacksmithTycoonPlugin(): JavaPlugin() {

    var economy: Economy? = null
        private set

    override fun onLoad() {
        NBTInjector.inject()
    }

    override fun onEnable() {
        if(!setupEconomy()) {
            logger.severe("Disabled due to no Vault dependency found !")
            server.pluginManager.disablePlugin(this)
            return
        }

        CommandsRegister.register(this)
        ListenersRegister.register(this)
    }
    
    override fun onDisable() {}

    private fun setupEconomy(): Boolean {
        if (server.pluginManager.getPlugin("Vault") == null) return false

        val response = server.servicesManager.getRegistration(Economy::class.java) ?: return false

        economy = response.provider

        return true
    }

}
