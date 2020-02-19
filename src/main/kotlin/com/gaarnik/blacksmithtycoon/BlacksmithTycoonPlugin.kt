package com.gaarnik.blacksmithtycoon

import de.tr7zw.nbtinjector.NBTInjector
import net.milkbowl.vault.economy.Economy
import org.bukkit.plugin.java.JavaPlugin

class BlacksmithTycoonPlugin(): JavaPlugin() {

    val db = BlacksmithTycoonDatabase(this)

    var economy: Economy? = null
        private set

    override fun onLoad() {
        NBTInjector.inject()

        BlacksmithPlayerService.db = db
    }

    override fun onEnable() {
        if(!setupEconomy()) {
            logger.severe("Disabled due to no Vault dependency found !")
            server.pluginManager.disablePlugin(this)
            return
        }

        if(!db.onEnable()) {
            logger.severe("Unable to init SQLite connection !")
            server.pluginManager.disablePlugin(this)
            return
        }

        CommandsRegister.register(this)
        ListenersRegister.register(this)
    }
    
    override fun onDisable() {
        BlacksmithPlayerService.saveAll()
    }

    private fun setupEconomy(): Boolean {
        if (server.pluginManager.getPlugin("Vault") == null) return false

        val response = server.servicesManager.getRegistration(Economy::class.java) ?: return false

        economy = response.provider

        return true
    }

}
