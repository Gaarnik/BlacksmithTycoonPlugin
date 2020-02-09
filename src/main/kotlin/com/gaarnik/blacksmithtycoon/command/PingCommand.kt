package com.gaarnik.blacksmithtycoon.command;

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class PingCommand: CommandExecutor {

    companion object {
        const val CMD = "ping";
    }

    override fun onCommand(sender: CommandSender, commmand: Command, label: String, arsg: Array<out String>): Boolean {
        if (sender !is Player) return false

        Bukkit.broadcastMessage("Hey " + sender.displayName + ": Pong !")

        return true
    }

}