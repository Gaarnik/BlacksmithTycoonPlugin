package com.gaarnik.blacksmithtycoon

import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class BlacksmithTycoonDatabase(plugin: JavaPlugin): AbstractDatabase(plugin, "data") {

    fun getPlayerData(uuid: UUID): BlacksmithPlayerData? {
        val connection = getConnection() ?: return null

        val ps = connection.prepareStatement("SELECT * FROM player WHERE uuid=?;")
        ps.setString(1, uuid.toString())
        val rs = ps.executeQuery()

        if (rs.fetchSize != 0) {
            rs.next()

            return BlacksmithPlayerData(
                    rs.getString("uuid"),
                    rs.getInt("level")
            )
        }

        ps.close()
        rs.close()

        val data = BlacksmithPlayerData(uuid.toString(), 0)
        createPlayerData(data)

        return data
    }

    fun savePlayerData(data: BlacksmithPlayerData) {
        val connection = getConnection() ?: return

        val ps = connection.prepareStatement("UPDATE player SET level=? WHERE uuid=?;")
        ps.setInt(1, data.level)
        ps.setString(2, data.uuid)

        ps.executeUpdate()
        ps.close()
    }

    private fun createPlayerData(data: BlacksmithPlayerData) {
        val connection = getConnection() ?: return

        val ps = connection.prepareStatement("INSERT INTO player VALUES(?, ?);")
        ps.setString(1, data.uuid)
        ps.setInt(2, data.level)

        ps.executeUpdate()
        ps.close()
    }

    override fun getDatabaseStructure(): String {
        return "CREATE TABLE IF NOT EXISTS player (" +
                "`uuid` varchar(36) NOT NULL," +
                "`level` int(11) NOT NULL," +
                "PRIMARY KEY (`uuid`)" +
                ");"
    }

}