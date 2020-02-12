package com.gaarnik.blacksmithtycoon

import com.gaarnik.blacksmithtycoon.menu.BlacksmithMasterMainMenu
import de.tr7zw.changeme.nbtapi.NBTEntity
import de.tr7zw.nbtinjector.NBTInjector
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.entity.Villager

class BlacksmithMasterNPC {

    var villager: Villager? = null

    fun openMenu(plugin: BlacksmithTycoonPlugin, player: Player) {
        BlacksmithMasterMainMenu(plugin, villager).open(player)
    }

    companion object {

        private const val NBT_KEY = "blacksmith-master"

        fun create(location: Location): BlacksmithMasterNPC {
            val master = BlacksmithMasterNPC()

            var entity = location.world?.spawnEntity(location, EntityType.VILLAGER) ?: return master
            entity.customName = "Blacksmith Master"
            entity.isCustomNameVisible = true

            val tagAI = NBTEntity(entity)
            tagAI.setInteger("NoAI", 1)

            entity = NBTInjector.patchEntity(entity)
            val tag = NBTInjector.getNbtData(entity)
            tag.setBoolean(NBT_KEY, true)

            master.villager = entity as Villager

            return master
        }

        fun from(e: Entity): BlacksmithMasterNPC {
            val master = BlacksmithMasterNPC()

            master.villager = e as Villager

            return master
        }

        fun isBlacksmithMasterNPC(e: Entity?): Boolean {
            if (e == null) return false
            if (e !is Villager) return false

            val entity = NBTInjector.patchEntity(e)
            val tag = NBTInjector.getNbtData(entity)

            return tag.getBoolean(NBT_KEY)
        }

    }

}