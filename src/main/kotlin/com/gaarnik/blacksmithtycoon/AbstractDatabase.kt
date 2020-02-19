package com.gaarnik.blacksmithtycoon

import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.lang.Exception
import java.sql.Connection
import java.sql.DriverManager

abstract class AbstractDatabase(private val plugin: JavaPlugin, private val dbName: String) {

    private var connection: Connection? = null

    fun onEnable(): Boolean {
        val connection = getConnection() ?: return false

        val s = connection.createStatement()
        s.executeUpdate(getDatabaseStructure())
        s.close()

        return true
    }

    protected fun getConnection(): Connection? {
        try {
            connection.let {
                if (it != null && !it.isClosed) {
                    return it
                }
            }

            val dbPath = getDbPath()


            Class.forName("org.sqlite.JDBC")
            connection = DriverManager.getConnection("jdbc:sqlite:$dbPath")

            return connection
        }
        catch (e: Exception) {
            plugin.logger.severe(e.toString())
            return null
        }
    }

    abstract fun getDatabaseStructure(): String

    private fun getDbPath(): File {
        if(!plugin.dataFolder.exists())
            plugin.dataFolder.mkdirs()

        val dbPath = File(plugin.dataFolder, "$dbName.db")

        if(!dbPath.exists())
            dbPath.createNewFile()

        return dbPath
    }

}