package github.gilbertokpl.total.commands

import github.gilbertokpl.core.external.command.CommandTarget
import github.gilbertokpl.core.external.command.annotations.CommandPattern
import github.gilbertokpl.total.TotalEssentialsJava
import github.gilbertokpl.total.cache.internal.Data
import github.gilbertokpl.total.cache.local.KeyData
import github.gilbertokpl.total.cache.local.PlayerData
import github.gilbertokpl.total.cache.local.VipData
import github.gilbertokpl.total.config.files.LangConfig
import github.gilbertokpl.total.config.files.MainConfig
import github.gilbertokpl.total.discord.Discord
import github.gilbertokpl.total.util.PluginUtil
import github.gilbertokpl.total.util.VipUtil.checkVip
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.jetbrains.exposed.sql.transactions.transaction

class CommandTotal : github.gilbertokpl.core.external.command.CommandCreator("total") {

    override fun commandPattern(): CommandPattern {
        return CommandPattern(
            aliases = listOf("system", "essentials", "s", "ks", "e"),
            active = true,
            target = CommandTarget.ALL,
            countdown = 0,
            permission = "totalessentials.commands.total",
            minimumSize = 1,
            maximumSize = 3,
            usage = listOf(
                "/total reload",
                "/total host",
                "/total plugin <load/unload/reload> <pluginName>",
                "C_/total reset",
                "P_/total id",
                "/total save"
            )
        )
    }

    override fun funCommand(s: CommandSender, label: String, args: Array<out String>): Boolean {

        if (args[0].lowercase() == "plugin") {
            if (args.size == 1 || args.size == 2) return true

            if (args[1].lowercase() == "load") {
                s.sendMessage(PluginUtil.load(args[2]))
                return false
            }

            val pl = PluginUtil.getPluginByName(args[2]) ?: run {
                s.sendMessage(LangConfig.generalPluginNotFound)
                return false
            }

            when (args[1].lowercase()) {
                "unload" -> s.sendMessage(PluginUtil.unload(pl))
                "reload" -> PluginUtil.reload(pl, s)
                else -> return true
            }
            return false
        }

        if (args[0].lowercase() == "reload") {
            if (TotalEssentialsJava.basePlugin.reloadConfig()) {
                s.sendMessage(
                    LangConfig.generalConfigReload
                )
            }
            return false
        }

        if (args[0].lowercase() == "reset" && args.size == 1 && s !is Player) {

            if (MainConfig.generalResetList.size == 1 && MainConfig.generalResetList[0] == "0") {
                s.sendMessage(LangConfig.generalResetMessageNotSet)
                return false
            }

            TotalEssentialsJava.basePlugin.getTask().async {
                val token = KeyData.generateRandomString()
                for (i in MainConfig.generalResetList) {
                    val id = i.toLongOrNull() ?: continue
                    if (!Discord.sendDiscordMessage(
                            id,
                            LangConfig.generalResetDiscordMessage.replace("%value%", token)
                        )
                    ) {
                        s.sendMessage(LangConfig.VipsDiscordUserIdNotExist)
                    }
                }
                Data.tokenReset = token
            }

            s.sendMessage(LangConfig.generalResetMessage)
            return false
        }

        if (args[0].lowercase() == "reset" && args.size == 2 && s !is Player) {

            if (args[1].contains(Data.tokenReset)) {
                for (players in PlayerData.vipCache.getMap()) {

                    PlayerData.commandCache[players.key, ""] = true

                    PlayerData.vipItems[players.key, ArrayList<ItemStack>()] = true

                    if (players.value.isNullOrEmpty()) continue

                    val p = PlayerData.vipCache[players.key] ?: continue

                    if (checkVip(players.key)) {
                        continue
                    }

                    for (vips in p) {
                        val vipItems = VipData.vipItems[vips.key]!!

                        var commands = PlayerData.commandCache[players.key] ?: ""

                        for (c in (VipData.vipCommands[vips.key] ?: ArrayList())) {
                            commands += if (commands == "") c.replace(
                                "%player%",
                                players.key
                            ) else "-" + c.replace("%player%", players.key)
                        }

                        PlayerData.commandCache[players.key, commands] = true

                        PlayerData.vipItems[players.key, vipItems] = true
                    }
                }

                TotalEssentialsJava.basePlugin.getTask().async {
                    try {
                        transaction(basePlugin?.sql) {
                            for (i in basePlugin?.getCache()?.toByteUpdate!!) {
                                try {
                                    i.update()
                                } catch (e: Exception) {
                                    println(e)
                                }
                            }
                        }
                    } catch (e: Exception) {
                        println(e)
                    }
                }

                Data.tokenReset = ""
                return false
            }
            return false
        }

        if (args[0].lowercase() == "host") {
            s.sendMessage(LangConfig.generalHostWait)
            //sendhostinfo
            val host = basePlugin!!.getHost().getHost()
            LangConfig.generalHostConfig.forEach {
                s.sendMessage(
                    it.replace("%ip%", host.ipAddress)
                        .replace("%os%", host.osName)
                        .replace("%os_version%", host.osVersion)
                        .replace("%cpu_name%", host.cpuName)
                        .replace("%cpu_clock_min%", host.cpuClockMin)
                        .replace("%cpu_clock_max%", host.cpuClockMax)
                        .replace("%cores%", host.cpuCores)
                        .replace("%cores_server%", host.cpuAvailable)
                        .replace(
                            "%cpu_usage%", host.cpuUsage
                        )
                        .replace("%used_mem%", host.memoryAllUsage)
                        .replace("%used_server_mem%", host.memoryServerUsage)
                        .replace("%max_mem%", host.memoryMax)
                        .replace("%max_server_mem%", host.memoryServerMax)
                        .replace("%gpu%", host.gpuName)
                        .replace("%name_hd%", host.diskName)
                        .replace("%used_hd%", host.diskUsage)
                        .replace("%max_hd%", host.diskMax)
                )
            }
            return false
        }

        if (args[0].lowercase() == "id" && s is Player) {
            s.sendMessage(s.itemInHand.type.name.lowercase())
            return false
        }

        if (args[0].lowercase() == "save") {
            TotalEssentialsJava.basePlugin.getCache().save()
            s.sendMessage("salvo")
            return false
        }
        return true
    }
}
