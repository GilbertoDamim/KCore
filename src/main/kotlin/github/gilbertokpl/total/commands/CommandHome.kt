package github.gilbertokpl.total.commands

import github.gilbertokpl.core.external.command.CommandTarget
import github.gilbertokpl.core.external.command.annotations.CommandPattern
import github.gilbertokpl.total.cache.local.PlayerData
import github.gilbertokpl.total.config.files.LangConfig
import github.gilbertokpl.total.config.files.MainConfig
import github.gilbertokpl.total.util.PlayerUtil
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandHome : github.gilbertokpl.core.external.command.CommandCreator("home") {

    override fun commandPattern(): CommandPattern {
        return CommandPattern(
            aliases = listOf("h", "homes"),
            active = MainConfig.homesActivated,
            target = CommandTarget.PLAYER,
            countdown = 0,
            permission = "totalessentials.commands.home",
            minimumSize = 0,
            maximumSize = 1,
            usage = listOf(
                "/home <homeName>",
                "totalessentials.commands.home.other_/home <playername>:<homeName>",
                "totalessentials.commands.home.other_/home <playername>:"
            )
        )
    }


    override fun funCommand(s: CommandSender, label: String, args: Array<out String>): Boolean {

        val p = s as Player

        if (!PlayerData.checkIfPlayerExists(p)) return false

        if (args.isEmpty()) {
            p.sendMessage(
                LangConfig.homesList.replace(
                    "%list%",
                    PlayerData.homeCache[p]!!.map { it.key }.toString()
                )
            )
            return false
        }

        //admin
        if (args[0].contains(":") && p.hasPermission("totalessentials.commands.home.other")) {
            val split = args[0].split(":")

            val pName = split[0].lowercase()

            if (!PlayerData.checkIfPlayerExists(pName)) {
                p.sendMessage(LangConfig.generalPlayerNotExist)
                return false
            }

            val homes = PlayerData.homeCache[pName]!!

            if (split.size < 2) {
                p.sendMessage(
                    LangConfig.homesOtherList.replace("%player%", pName)
                        .replace("%list%", homes.map { it.key }.toString())
                )
                return false
            }

            val loc = homes[split[1]] ?: run {
                p.sendMessage(
                    LangConfig.homesOtherList.replace("%player%", pName)
                        .replace("%list%", homes.map { it.key }.toString())
                )
                return false
            }

            p.teleport(loc)


            p.sendMessage(
                LangConfig.homesTeleportedOther.replace(
                    "%home%",
                    split[1].lowercase()
                )
                    .replace("%player%", split[0])
            )

            return false
        }

        val nameHome = args[0].lowercase()

        val homes = PlayerData.homeCache[s]!!

        //check if home don't exist
        val loc = homes[nameHome] ?: run {
            p.sendMessage(LangConfig.homesNameDontExist)
            return false
        }

        PlayerUtil.teleportWithTime(
            p,
            loc,
            MainConfig.homesTimeToTeleport,
            LangConfig.homesTeleported.replace("%home%", nameHome),
            nameHome
        )

        return false
    }
}
