package github.gilbertokpl.total.cache.local.test

import github.gilbertokpl.core.external.cache.interfaces.CacheBase
import github.gilbertokpl.total.TotalEssentialsJava
import github.gilbertokpl.total.cache.serializer.ItemSerializer
import github.gilbertokpl.total.cache.sql.LimiterSQL
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object LimitData : CacheBase {
    override var table: Table = LimiterSQL
    override var primaryColumn: Column<String> = LimiterSQL.groupTable

    private val cache = TotalEssentialsJava.basePlugin.getCache()

    val limitItems = cache.list(this, LimiterSQL.itemsTable, ItemSerializer())
    val limitPrice = cache.integer(this, LimiterSQL.priceTable)

}