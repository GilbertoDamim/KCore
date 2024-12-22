package github.gilbertokpl.total.commands

import github.gilbertokpl.core.external.command.CommandTarget
import github.gilbertokpl.core.external.command.annotations.CommandPattern
import github.gilbertokpl.total.cache.local.PlayerData
import github.gilbertokpl.total.config.files.LangConfig
import github.gilbertokpl.total.config.files.MainConfig
import github.gilbertokpl.total.util.MoneyUtil
import github.gilbertokpl.total.util.PlayerUtil
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandMoney : github.gilbertokpl.core.external.command.CommandCreator("money") {

    override fun commandPattern(): CommandPattern {
        return CommandPattern(
            aliases = listOf("dinheiro"),
            active = MainConfig.moneyActivated,
            target = CommandTarget.ALL,
            countdown = 0,
            permission = "totalessentials.commands.money",
            minimumSize = 0,
            maximumSize = 3,
            usage = listOf(
                "P_/money",
                "/money <PlayerName>",
                "P_/money pay <playerName> <value>",
                "/money top",
                "totalessentials.commands.money.admin_/money set <player> <value>",
                "totalessentials.commands.money.admin_/money take <player> <value>",
                "totalessentials.commands.money.admin_/money give <player> <value>"
            )
        )
    }

    override fun funCommand(s: CommandSender, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty() && s is Player) {

            val money = PlayerData.moneyCache[s] ?: 0.0

            s.sendMessage(
                MoneyUtil.coinReplacer(
                    LangConfig.moneyMessage, money
                )
            )
            return false
        }

        if (args.isEmpty()) {
            return true
        }

        if (args[0] == "top") {
            s.sendMessage(LangConfig.moneyTopMessage)
            var position = 1
            for (i in MoneyUtil.tycoonPlayer) {
                s.sendMessage(
                    MoneyUtil.coinReplacer(
                        LangConfig.moneyTop, i.value
                    )
                        .replace("%player%", i.key)
                        .replace("%position%", position.toString())
                )
                position += 1
            }
            return false
        }

        if (args.size == 1) {
            if (!PlayerData.checkIfPlayerExists(args[0])) {
                s.sendMessage(LangConfig.generalPlayerNotExist)
                return false
            }

            s.sendMessage(
                MoneyUtil.coinReplacer(
                    LangConfig.moneyMessageOther, PlayerData.moneyCache[args[0]] ?: 0.0
                ).replace("%player%", args[0].lowercase())
            )
            return false
        }

        if (args[0] == "pay" && args.size == 3 && s is Player) {

            val value = args[2].toDoubleOrNull() ?: return true

            if (value <= 0) return true

            if (args[1].lowercase() == s.name.lowercase()) {
                s.sendMessage(LangConfig.moneyPaySame)
                return false
            }

            if (!PlayerData.checkIfPlayerExists(args[1])) {
                s.sendMessage(LangConfig.generalPlayerNotExist)
                return false
            }

            val money = PlayerData.moneyCache[s] ?: 0.0

            if (money < value) {
                s.sendMessage(
                    MoneyUtil.coinReplacer(
                        LangConfig.moneyMissing, value - money
                    )
                )
                return false
            }


            MoneyUtil.withdrawPlayer(s.name, value)
            MoneyUtil.depositPlayer(args[1], value)

            s.sendMessage(
                MoneyUtil.coinReplacer(
                    LangConfig.moneyPay, value
                ).replace("%player%", args[1].lowercase())
            )

            PlayerUtil.sendMessage(
                args[1].lowercase(), MoneyUtil.coinReplacer(
                    LangConfig.moneyPayOther, value
                ).replace("%player%", s.name)
            )

            return false
        }

        if ((s !is Player || s.hasPermission("totalessentials.commands.money.admin")) && args.size == 3) {


            val value = args[2].toDoubleOrNull() ?: return true

            if (value < 0) return true

            if (!PlayerData.checkIfPlayerExists(args[1])) {
                s.sendMessage(LangConfig.generalPlayerNotExist)
                return false
            }

            if (args[0] == "set") {
                PlayerData.moneyCache[args[1]] = value

                s.sendMessage(
                    MoneyUtil.coinReplacer(
                        LangConfig.moneySet, value
                    ).replace("%player%", args[1].lowercase())
                )

                PlayerUtil.sendMessage(
                    args[1].lowercase(), MoneyUtil.coinReplacer(
                        LangConfig.moneySetOther, value
                    )
                )

                return false
            }
            if (args[0] == "take") {
                val otherPlayer = PlayerData.moneyCache[args[1]] ?: return true
                if (otherPlayer < value) {
                    s.sendMessage(
                        MoneyUtil.coinReplacer(
                            LangConfig.moneyMissing, value - otherPlayer
                        )
                    )
                    return false
                }
                PlayerData.moneyCache[args[1]] = otherPlayer - value

                s.sendMessage(
                    MoneyUtil.coinReplacer(
                        LangConfig.moneyTake, value
                    ).replace("%player%", args[1].lowercase())
                )
                PlayerUtil.sendMessage(
                    args[1].lowercase(), MoneyUtil.coinReplacer(
                        LangConfig.moneyTakeOther, value
                    )
                )

                return false
            }
            if (args[0] == "give") {
                val otherPlayer = PlayerData.moneyCache[args[1]] ?: 0.0
                PlayerData.moneyCache[args[1]] = otherPlayer + value

                s.sendMessage(
                    MoneyUtil.coinReplacer(
                        LangConfig.moneyAdd, value
                    ).replace("%player%", args[1].lowercase())
                )

                PlayerUtil.sendMessage(
                    args[1].lowercase(), MoneyUtil.coinReplacer(
                        LangConfig.moneyAddOther, value
                    )
                )

                return false
            }
        }
        return true
    }
}