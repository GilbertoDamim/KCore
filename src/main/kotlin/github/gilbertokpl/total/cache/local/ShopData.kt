package github.gilbertokpl.total.cache.local

import github.gilbertokpl.core.external.cache.interfaces.CacheBase
import github.gilbertokpl.total.cache.serializer.LocationSerializer
import github.gilbertokpl.total.cache.sql.ShopDataSQL
import org.bukkit.Location
import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object ShopData : CacheBase {
    override var table: Table = ShopDataSQL
    override var primaryColumn: Column<String> = ShopDataSQL.playerTable

    private val cache = github.gilbertokpl.total.TotalEssentialsJava.basePlugin.getCache()

    val shopVisits = cache.integer(this, ShopDataSQL.visits)
    val shopLocation = cache.location(this, ShopDataSQL.location, LocationSerializer())
    val shopOpen = cache.boolean(this, ShopDataSQL.open)

    fun createNewShop(location: Location, player: Player) {
        if (!checkIfShopExists(player.name.lowercase())) {
            shopVisits[player] = 0
        }
        shopLocation[player] = location
        shopOpen[player] = false
    }

    fun checkIfShopExists(playerName: String): Boolean {
        return shopVisits[playerName.lowercase()] != null
    }
}