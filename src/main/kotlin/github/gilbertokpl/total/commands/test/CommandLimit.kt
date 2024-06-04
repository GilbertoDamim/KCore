package github.gilbertokpl.total.commands.test

import github.gilbertokpl.core.external.command.CommandTarget
import github.gilbertokpl.core.external.command.annotations.CommandPattern
import github.gilbertokpl.total.TotalEssentialsJava
import github.gilbertokpl.total.cache.internal.Data.limitPlayerEdit
import github.gilbertokpl.total.cache.local.test.LimitData
import github.gilbertokpl.total.config.files.LangConfig
import github.gilbertokpl.total.config.files.MainConfig
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandLimit : github.gilbertokpl.core.external.command.CommandCreator("limit") {

    override fun commandPattern(): CommandPattern {
        return CommandPattern(
            aliases = listOf("limites", "limite"),
            active = MainConfig.limitActivated,
            target = CommandTarget.ALL,
            countdown = 0,
            permission = "totalessentials.commands.limit",
            minimumSize = 0,
            maximumSize = 1,
            usage = listOf(
                "P_/limit",
                "P_/limit edit <group>"
            )
        )
    }

    override fun funCommand(s: CommandSender, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty() && s is Player) {
            return false
        }
        if (args[0].contains("edit", true) && s is Player && s.hasPermission("totalessentials.commands.limit.edit")) {
            if (!TotalEssentialsJava.permission.groups.contains(args[1])) {
                s.sendMessage(LangConfig.limitGroupDoNotExist)
                return false
            }
            limitPlayerEdit[s] = args[1]

            val limit = LimitData.limitItems[args[1]]
            val inv = TotalEssentialsJava.instance.server.createInventory(null, 54)
            if (limit != null) {
                for (i in limit) {
                    inv.addItem(i)
                }
            }

            s.openInventory(TotalEssentialsJava.instance.server.createInventory(null, 54))
        }
        return true
    }
}