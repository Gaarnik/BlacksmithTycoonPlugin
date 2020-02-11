package com.gaarnik.blacksmithtycoon

import com.gaarnik.blacksmithtycoon.command.SpawnMasterCommand
import org.bukkit.plugin.java.JavaPlugin

class CommandsRegister {

    companion object {

        fun register(plugin: JavaPlugin) {
            plugin.getCommand(SpawnMasterCommand.CMD)?.setExecutor(SpawnMasterCommand())
        }

    }

}