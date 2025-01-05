package github.gilbertokpl.total.cache.local

import github.gilbertokpl.core.external.cache.interfaces.CacheBase
import github.gilbertokpl.total.TotalEssentialsJava
import github.gilbertokpl.total.cache.serializer.*
import github.gilbertokpl.total.cache.sql.PlayerDataSQL
import github.gilbertokpl.total.config.files.MainConfig
import github.gilbertokpl.total.util.PlayerUtil
import github.gilbertokpl.total.util.VipUtil
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object PlayerData : CacheBase {
    override var table: Table = PlayerDataSQL
    override var primaryColumn: Column<String> = PlayerDataSQL.playerTable

    private val ins = TotalEssentialsJava.basePlugin.getCache()

    val kitsCache = ins.hashMap(this, PlayerDataSQL.kitsTable, KitSerializer())
    val homeCache = ins.hashMap(this, PlayerDataSQL.homeTable, HomeSerializer())
    val vipCache = ins.hashMap(this, PlayerDataSQL.vipTable, VipSerializer())
    val vipItems = ins.list(this, PlayerDataSQL.vipItems, ItemSerializer())
    val nickCache = ins.string(this, PlayerDataSQL.nickTable)
    val gameModeCache = ins.integer(this, PlayerDataSQL.gameModeTable)
    val vanishCache = ins.boolean(this, PlayerDataSQL.vanishTable)
    val lightCache = ins.boolean(this, PlayerDataSQL.lightTable)
    val flyCache = ins.boolean(this, PlayerDataSQL.flyTable)
    val backLocation = ins.location(this, PlayerDataSQL.backTable, LocationSerializer())
    val speedCache = ins.integer(this, PlayerDataSQL.speedTable)
    val moneyCache = ins.double(this, PlayerDataSQL.moneyTable)
    val discordCache = ins.long(this, PlayerDataSQL.DiscordTable)
    val playTimeCache = ins.long(this, PlayerDataSQL.PlaytimeTable)
    val colorCache = ins.string(this, PlayerDataSQL.colorTable)
    val commandCache = ins.string(this, PlayerDataSQL.CommandTable)
    val limiterItemCache = ins.hashMap(this, PlayerDataSQL.LimiterItemTable, LimiterItemSerializer())
    val limiterLocationCache = ins.hashMap(this, PlayerDataSQL.LimiterLocationTable, LimiterLocationSerializer())
    val inInvSee = ins.simplePlayer()
    val homeLimitCache = ins.simpleInteger()
    val inTeleport = ins.simpleBoolean()
    val afk = ins.simpleInteger()
    val playtimeLocal = ins.simpleLong()
    val playerInfo = ins.simpleList<String>()

    fun checkIfPlayerExists(entity: String): Boolean {
        return nickCache[entity.lowercase()] != null
    }

    fun checkIfPlayerExists(entity: Player): Boolean {
        return nickCache[entity] != null
    }

    fun createNewPlayerEco(entity: String) {
        moneyCache[entity] = MainConfig.moneyDefault?.toDouble() ?: 0.0
    }

    fun createNewPlayerData(entity: String) {
        val defaultLocation = SpawnData.spawnLocation["spawn"]
            ?: Location(TotalEssentialsJava.instance.server.getWorld("world"), 1.0, 1.0, 1.0)

        kitsCache[entity] = hashMapOf()
        homeCache[entity] = hashMapOf()
        vipCache[entity] = hashMapOf()
        vipItems[entity] = arrayListOf()
        nickCache[entity] = ""
        gameModeCache[entity] = 0
        vanishCache[entity] = false
        lightCache[entity] = false
        flyCache[entity] = false
        backLocation[entity] = defaultLocation
        speedCache[entity] = 1
        moneyCache[entity] = MainConfig.moneyDefault?.toDouble() ?: 0.0
        afk[entity] = 1
        playTimeCache[entity] = 0
        discordCache[entity] = 0
        colorCache[entity] = ""
        commandCache[entity] = ""
    }

    fun applyPlayerSettings(p: Player) {
        afk[p] = 1

        nickCache[p]?.let { nick ->
            if (nick.isNotEmpty() && nick != p.displayName && p.hasPermission("totalessentials.commands.nick")) {
                PlayerUtil.setDisplayName(p, nick)
            }
        }

        gameModeCache[p]?.let { gameModeNumber ->
            val gameModeName = PlayerUtil.getGameModeNumber(gameModeNumber.toString())
            if (p.gameMode != gameModeName && (gameModeName == GameMode.SURVIVAL || p.hasPermission("totalessentials.commands.gamemode"))) {
                p.gameMode = gameModeName
            }
        }

        vanishCache[p]?.takeIf { it }?.let {
            p.addPotionEffect(PotionEffect(PotionEffectType.INVISIBILITY, Int.MAX_VALUE, 1))
            TotalEssentialsJava.basePlugin.getReflection().getPlayers().forEach { otherPlayer ->
                otherPlayer.player?.takeIf { !it.hasPermission("totalessentials.commands.vanish") && !it.hasPermission("totalessentials.bypass.vanish") }
                    ?.hidePlayer(p)
            }
        }

        lightCache[p]?.takeIf { it }?.let {
            p.addPotionEffect(PotionEffect(PotionEffectType.NIGHT_VISION, Int.MAX_VALUE, 1))
        }

        flyCache[p]?.takeIf { it }?.let {
            p.allowFlight = true
            p.isFlying = true
        }

        speedCache[p]?.takeIf { it != 1 }?.let { speed ->
            val speedValue = (speed * 0.1).toFloat()
            p.walkSpeed = speedValue
            p.flySpeed = speedValue
        }

        playTimeCache[p]?.let { playTime ->
            if (playTime > 31_557_600_000) {
                playTimeCache[p] = 0
            }
        }

        commandCache[p]?.takeIf { it.isNotEmpty() }?.let { commands ->
            commands.split(" -").forEach { command ->
                TotalEssentialsJava.instance.server.dispatchCommand(
                    TotalEssentialsJava.instance.server.consoleSender,
                    command
                )
            }
            commandCache[p] = ""
            VipUtil.updateCargo(p.name.lowercase())
        }

        if (MainConfig.vanishActivated) {
            if (!p.hasPermission("totalessentials.commands.vanish") && !p.hasPermission("totalessentials.bypass.vanish")) {
                TotalEssentialsJava.basePlugin.getReflection().getPlayers().forEach { otherPlayer ->
                    vanishCache[otherPlayer]?.takeIf { it }?.let {
                        p.hidePlayer(otherPlayer)
                    }
                }
            }
        }
    }
}
