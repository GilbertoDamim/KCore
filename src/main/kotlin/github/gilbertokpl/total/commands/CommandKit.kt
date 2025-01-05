package github.gilbertokpl.total.commands

import github.gilbertokpl.core.external.command.CommandTarget
import github.gilbertokpl.core.external.command.annotations.CommandPattern
import github.gilbertokpl.total.cache.internal.Data
import github.gilbertokpl.total.cache.local.KitsData
import github.gilbertokpl.total.config.files.LangConfig
import github.gilbertokpl.total.config.files.MainConfig
import github.gilbertokpl.total.util.ItemUtil
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandKit : github.gilbertokpl.core.external.command.CommandCreator("kit") {

    override fun commandPattern(): CommandPattern {
        return CommandPattern(
            aliases = listOf("kits"),
            active = MainConfig.kitsActivated,
            target = CommandTarget.ALL,
            countdown = 0,
            permission = "totalessentials.commands.kit",
            minimumSize = 0,
            maximumSize = 1,
            usage = listOf(
                "/kit",
                "/kit <kitName>"
            )
        )
    }

    override fun funCommand(s: CommandSender, label: String, args: Array<out String>): Boolean {

        if (s !is Player || (args.isEmpty() && !MainConfig.kitsMenuKit)) {
            s.sendMessage(
                LangConfig.kitsList.replace(
                    "%kits%",
                    KitsData.kitTime.getMap().map { it.key }.toString()
                )
            )
            return false
        }

        //send gui
        if (args.isEmpty()) {

            Data.kitInventoryCache[1].also {
                it ?: run {
                    s.sendMessage(LangConfig.kitsNotExistKits)
                    return false
                }
                s.openInventory(it)
            }
            return false
        }

        //check if not exist
        if (!KitsData.checkIfExist(args[0].lowercase())) {
            s.sendMessage(
                LangConfig.kitsList.replace(
                    "%kits%",
                    KitsData.kitTime.getMap().map { it.key }.toString()
                )
            )
            return false
        }

        //give kit
        ItemUtil.pickupKit(s, args[0].lowercase())
        return false
    }
}
