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
    val CommandCache = ins.string(this, PlayerDataSQL.CommandTable)
    val LimiterItemCache = ins.hashMap(this, PlayerDataSQL.LimiterItemTable, LimiterItemSerializer())
    val LimiterLocationCache = ins.hashMap(this, PlayerDataSQL.LimiterLocationTable, LimiterLocationSerializer())
    val inInvSee = ins.simplePlayer()
    val homeLimitCache = ins.simpleInteger()
    val inTeleport = ins.simpleBoolean()
    val afk = ins.simpleInteger()
    val playtimeLocal = ins.simpleLong()
    val playerInfo = ins.simpleList<String>()

    fun checkIfPlayerExist(entity: String): Boolean {
        return nickCache[entity.lowercase()] != null
    }

    fun checkIfPlayerExist(entity: Player): Boolean {
        return nickCache[entity] != null
    }

    fun createNewPlayerData(entity: String) {
        kitsCache[entity] = HashMap()
        homeCache[entity] = HashMap()
        vipCache[entity] = HashMap()
        vipItems[entity] = ArrayList()
        nickCache[entity] = ""
        gameModeCache[entity] = 0
        vanishCache[entity] = false
        lightCache[entity] = false
        flyCache[entity] = false
        backLocation[entity] = SpawnData.spawnLocation["spawn"]
            ?: Location(TotalEssentialsJava.instance.server.getWorld("world"), 1.0, 1.0, 1.0)
        speedCache[entity] = 1
        moneyCache[entity] = MainConfig.moneyDefault?.toDouble() ?: 0.0
        afk[entity] = 1
        playTimeCache[entity] = 0
        discordCache[entity] = 0
        colorCache[entity] = ""
    }

    fun values(p: Player) {

        afk[p] = 1

        val nick = nickCache[p]

        if (nick != "" && nick != p.displayName && p.hasPermission("totalessentials.commands.nick")) {
            PlayerUtil.setDisplayName(p, nick)
        }

        val gameModeName = PlayerUtil.getGameModeNumber(gameModeCache[p].toString())

        if (p.gameMode != gameModeName && gameModeName == GameMode.SURVIVAL) {
            p.gameMode = gameModeName
        }
        if (p.gameMode != gameModeName && p.hasPermission("totalessentials.commands.gamemode")) {
            p.gameMode = gameModeName
        }
        if (vanishCache[p]!!) {
            p.addPotionEffect(PotionEffect(PotionEffectType.INVISIBILITY, Int.MAX_VALUE, 1))
            for (it in TotalEssentialsJava.basePlugin.getReflection().getPlayers()) {
                if (it.player!!.hasPermission("totalessentials.commands.vanish")
                    || it.player!!.hasPermission("totalessentials.bypass.vanish")
                ) {
                    continue
                }
                @Suppress("DEPRECATION")
                it.hidePlayer(p)
            }
        }

        if (lightCache[p]!!) {
            p.addPotionEffect(PotionEffect(PotionEffectType.NIGHT_VISION, Int.MAX_VALUE, 1))
        }

        if (flyCache[p]!!) {
            p.allowFlight = true
            p.isFlying = true
        }

        val speed = speedCache[p]!!

        if (speed != 1) {
            p.walkSpeed = (speed * 0.1).toFloat()
            p.flySpeed = (speed * 0.1).toFloat()
        }

        if (playTimeCache[p]!! > 31557600000) {
            playTimeCache[p] = 0
        }

        if (!CommandCache[p].isNullOrEmpty()) {
            for (i in CommandCache[p]?.split(" -")!!) {
                TotalEssentialsJava.instance.server.dispatchCommand(
                    TotalEssentialsJava.instance.server.consoleSender,
                    i
                )
            }
            CommandCache[p] = ""
            VipUtil.updateCargo(p.name.lowercase())
        }

        if (MainConfig.vanishActivated) {
            if (p.hasPermission("totalessentials.commands.vanish") ||
                p.hasPermission("totalessentials.bypass.vanish")
            ) return
            for (it in TotalEssentialsJava.basePlugin.getReflection().getPlayers()) {
                if (vanishCache[it] ?: continue) {
                    @Suppress("DEPRECATION")
                    p.hidePlayer(it)
                }
            }
        }

    }
}