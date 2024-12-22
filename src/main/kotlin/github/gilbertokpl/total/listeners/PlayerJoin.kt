package github.gilbertokpl.total.listeners

import github.gilbertokpl.core.external.task.SynchronizationContext
import github.gilbertokpl.total.TotalEssentialsJava
import github.gilbertokpl.total.cache.local.LoginData
import github.gilbertokpl.total.cache.local.PlayerData
import github.gilbertokpl.total.cache.local.SpawnData
import github.gilbertokpl.total.config.files.LangConfig
import github.gilbertokpl.total.config.files.MainConfig
import github.gilbertokpl.total.discord.Discord
import github.gilbertokpl.total.util.*
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoin : Listener {
    @EventHandler(priority = EventPriority.HIGH)
    fun onPlayerJoin(event: PlayerJoinEvent) {
        event.joinMessage = null

        val player = event.player
        val address = player.address?.address?.toString() ?: return

        handleAuthentication(player, address)
        SpawnData.teleportToSpawn(player)

        TotalEssentialsJava.basePlugin.getTask().async {
            handlePlaytime(player)
            initializePlayerData(player)
            sendMessages(player)
            VipUtil.checkVip(player.name.lowercase())
            handleAntiVpn(player, address)

            switchContext(SynchronizationContext.SYNC)
            PlayerData.applyPlayerSettings(player)
        }
    }

    private fun handleAuthentication(player: Player, address: String) {
        if (!MainConfig.authActivated) {
            LoginData.isLoggedIn[player] = true
            return
        }

        LoginData.loginAttempts[player] = 0
        LoginData.values[player] = 0

        if (LoginData.ipAddress[player] == address) {
            player.sendMessage(LangConfig.authAutoLogin)
            LoginData.isLoggedIn[player] = true
        } else {
            LoginUtil.loginMessage(player)
        }
    }

    private fun handlePlaytime(player: Player) {
        if (MainConfig.playtimeActivated) {
            PlayerData.playtimeLocal[player] = System.currentTimeMillis()
        }
    }

    private fun initializePlayerData(player: Player) {
        if (!PlayerData.checkIfPlayerExists(player)) {
            PlayerData.createNewPlayerData(player.name)
        }

        val homeLimit = PermissionUtil.getNumberPermission(
            player,
            "totalessentials.commands.sethome.",
            MainConfig.homesDefaultLimitHomes
        )
        PlayerData.homeLimitCache[player] = homeLimit
    }

    private fun sendMessages(player: Player) {
        if (player.hasPermission("*")) return

        if (MainConfig.messagesLoginMessage) {
            MainUtil.serverMessage(
                LangConfig.messagesEnterMessage.replace("%player%", player.name)
            )
        }
        if (MainConfig.discordbotSendLoginMessage) {
            Discord.sendDiscordMessage(
                LangConfig.discordchatDiscordSendLoginMessage.replace("%player%", player.name),
                true
            )
        }
    }

    private fun handleAntiVpn(player: Player, address: String) {
        if (MainConfig.generalAntiVpn) {
            PlayerData.playerInfo[player] = PlayerUtil.checkPlayer(address)
        }
    }
}
