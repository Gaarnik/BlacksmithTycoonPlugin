package com.gaarnik.blacksmithtycoon.listener

import com.gaarnik.blacksmithtycoon.BlacksmithPlayerService
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.event.player.PlayerQuitEvent

class BlacksmithPlayerServiceListener: Listener {

    @EventHandler
    fun onConnect(e: PlayerLoginEvent) {
        BlacksmithPlayerService.onConnect(e.player)
    }

    @EventHandler
    fun onDisconnect(e: PlayerQuitEvent) {
        BlacksmithPlayerService.onDisconnect(e.player)
    }

}