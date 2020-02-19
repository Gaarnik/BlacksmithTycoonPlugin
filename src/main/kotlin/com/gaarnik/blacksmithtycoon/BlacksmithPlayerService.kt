package com.gaarnik.blacksmithtycoon

import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.HashMap

object BlacksmithPlayerService {

    var db: BlacksmithTycoonDatabase? = null

    private val players = HashMap<UUID, BlacksmithPlayer>()

    fun onConnect(player: Player) {
        val db = this.db ?: return
        val uuid = player.uniqueId
        val data = db.getPlayerData(uuid) ?: return

        players[uuid] = BlacksmithPlayer(player, data)
    }

    fun onDisconnect(player: Player) {
        val db = this.db ?: return
        val uuid = player.uniqueId
        val blacksmithPlayer = players[uuid] ?: return

        db.savePlayerData(blacksmithPlayer.data)
        players.remove(uuid)
    }

    fun saveAll() {
        val db = this.db ?: return

        players.forEach {
            db.savePlayerData(it.value.data)
        }
    }

    fun getBlacksmithPlayer(player: Player): BlacksmithPlayer? {
        return players[player.uniqueId]
    }

}