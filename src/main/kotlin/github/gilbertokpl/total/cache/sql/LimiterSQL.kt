package github.gilbertokpl.total.cache.sql

import github.gilbertokpl.total.config.files.MainConfig
import org.jetbrains.exposed.sql.Table

object LimiterSQL : Table("LimitData" + MainConfig.databaseManager) {
    val groupTable = text("group")
    val itemsTable = text("Items")
    val priceTable = integer("Price")
    override val primaryKey = PrimaryKey(groupTable)
}