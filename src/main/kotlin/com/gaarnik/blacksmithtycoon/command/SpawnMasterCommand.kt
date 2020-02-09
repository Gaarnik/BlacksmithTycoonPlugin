package com.gaarnik.blacksmithtycoon.command

import com.gaarnik.blacksmithtycoon.BlacksmithMasterNPC
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SpawnMasterCommand: CommandExecutor {

    companion object {
        const val CMD = "blacksmithspawn";
    }

    override fun onCommand(sender: CommandSender, commmand: Command, label: String, arsg: Array<out String>): Boolean {
        if (sender !is Player) return false

        BlacksmithMasterNPC.create(sender.location)

        return true;
    }

}