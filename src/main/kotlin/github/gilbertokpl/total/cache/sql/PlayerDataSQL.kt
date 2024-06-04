package github.gilbertokpl.total.cache.sql

import github.gilbertokpl.total.config.files.MainConfig
import org.jetbrains.exposed.sql.Table

object PlayerDataSQL : Table("PlayerData" + MainConfig.databaseManager) {
    val playerTable = varchar("name", 64)
    val kitsTable = text("kitsTime").default("")
    val homeTable = text("savedHomes").default("")
    val vipTable = text("Vip").default("")
    val vipItems = text("VipItems").default("")
    val nickTable = varchar("fakeNick", 32).default("")
    val gameModeTable = integer("Gamemode").default(0)
    val vanishTable = bool("Vanish").default(false)
    val lightTable = bool("Light").default(false)
    val flyTable = bool("Fly").default(false)
    val backTable = text("Back").default("")
    val speedTable = integer("Speed").default(1)
    val moneyTable = double("Money").default(MainConfig.moneyDefault.toDouble())
    val DiscordTable = long("DiscordID").default(0)
    val PlaytimeTable = long("Playtime").default(0)
    val colorTable = varchar("color", 10).default("")
    val CommandTable = text("command").default("")
    val LimiterItemTable = text("LimiterItem").default("")
    val LimiterLocationTable = text("LimiterLocation").default("")
    override val primaryKey = PrimaryKey(playerTable)
}
