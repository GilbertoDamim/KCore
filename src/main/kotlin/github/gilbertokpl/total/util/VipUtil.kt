package github.gilbertokpl.total.util

import github.gilbertokpl.total.TotalEssentialsJava
import github.gilbertokpl.total.cache.local.PlayerData
import github.gilbertokpl.total.cache.local.VipData
import github.gilbertokpl.total.discord.Discord
import org.bukkit.World

object VipUtil {

    val world: World? = null

    fun checkVip(entity: String): Boolean {
        val vips = PlayerData.vipCache[entity] ?: return false

        val toExclude = ArrayList<String>()

        var delet = false

        for (v in vips) {
            if (v.value < System.currentTimeMillis()) {
                delet = true
                toExclude.add(v.key)
            }
        }

        for (t in toExclude) {
            PlayerData.vipCache.remove(entity, t)
            TotalEssentialsJava.permission.playerRemoveGroup(world, entity, VipData.vipGroup[t])
            VipData.vipQuantity[t] = (VipData.vipQuantity[t] ?: 0) - 1
            Discord.removeUserRole(PlayerData.discordCache[entity] ?: continue, VipData.vipDiscord[t] ?: continue)
        }

        if (toExclude.isNotEmpty()) {
            updateCargo(entity)
        }
        return delet
    }

    fun updateCargo(entity: String, newVip: String? = null, execute: Boolean = true): String? {

        val vips = PlayerData.vipCache[entity] ?: return null

        val size = vips.size

        val sequence = ArrayList<String>()

        var currentGroup: String? = null

        for (v in vips) {
            sequence.add(v.key)
        }

        for (g in sequence) {
            val group = VipData.vipGroup[g] ?: continue
            if (TotalEssentialsJava.permission.playerInGroup(world, entity, group)) {
                currentGroup = g
                break
            }
        }

        if (currentGroup == null) {
            if (newVip != null) {
                TotalEssentialsJava.permission.playerAddGroup(world, entity, VipData.vipGroup[newVip])

                if (execute) {
                    for (c in (VipData.vipCommands[newVip] ?: ArrayList())) {
                        TotalEssentialsJava.instance.server.dispatchCommand(
                            TotalEssentialsJava.instance.server.consoleSender,
                            c.replace("%player%", entity)
                        )
                    }
                }

                VipData.vipQuantity[newVip] = (VipData.vipQuantity[newVip] ?: 0) + 1

                val token = PlayerData.discordCache[entity]
                val roleID = VipData.vipDiscord[newVip]
                if (token != null && roleID != null && token != 0L) {
                    Discord.addUserRole(token, roleID)
                }
                return null
            }
            if (size > 0) {
                val newGroup = vips.keys.first()
                TotalEssentialsJava.permission.playerAddGroup(world, entity, VipData.vipGroup[newGroup])
                return newGroup
            }
            return null
        }

        var value = 0

        for (i in sequence) {
            value += 1
            if (i == currentGroup) {
                break
            }
        }

        if (size < (value + 1)) {
            value = 1
        } else {
            value += 1
        }

        TotalEssentialsJava.permission.playerRemoveGroup(world, entity, VipData.vipGroup[currentGroup])

        if (newVip != null) {
            TotalEssentialsJava.permission.playerAddGroup(world, entity, VipData.vipGroup[newVip])

            if (execute) {
                for (c in (VipData.vipCommands[newVip] ?: ArrayList())) {
                    TotalEssentialsJava.instance.server.dispatchCommand(
                        TotalEssentialsJava.instance.server.consoleSender,
                        c.replace("%player%", entity)
                    )
                }
            }

            VipData.vipQuantity[newVip] = (VipData.vipQuantity[newVip] ?: 0) + 1

            val token = PlayerData.discordCache[entity]
            val roleID = VipData.vipDiscord[newVip]
            if (token != null && roleID != null && token != 0L) {
                Discord.addUserRole(token, roleID)
            }
            return null
        }

        TotalEssentialsJava.permission.playerAddGroup(world, entity, VipData.vipGroup[sequence[value - 1]])

        return sequence[value - 1]

    }

}