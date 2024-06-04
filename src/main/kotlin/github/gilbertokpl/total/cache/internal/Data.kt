package github.gilbertokpl.total.cache.internal

import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.jetbrains.exposed.sql.Database

internal object Data {

    lateinit var sql: Database

    //limit
    val limitPlayerEdit: MutableMap<Player, String> = mutableMapOf()

    //vip
    val tokenVip: MutableMap<String, Long> = mutableMapOf()
    val playerVipEdit: MutableMap<Player, String> = mutableMapOf()

    //ShopInv
    lateinit var shopInventoryCache: Map<Int, Inventory>
    lateinit var shopItemCache: Map<Int, String>

    //kitInv
    lateinit var kitInventoryCache: Map<Int, Inventory>
    lateinit var kitItemCache: Map<Int, String>

    //PlaytimeInv
    lateinit var playTimeInventoryCache: Map<Int, Inventory>

    //editKit
    val playerEditKit: MutableMap<Player, String> = HashMap()
    var editKitItemCache: MutableMap<Int, ItemStack> = HashMap()
    val playerEditKitChat: MutableMap<Player, String> = HashMap()

    //reset

    var tokenReset = "0"

}





