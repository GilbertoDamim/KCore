package github.gilbertokpl.total.cache.internal.inventory

import github.gilbertokpl.total.TotalEssentialsJava
import github.gilbertokpl.total.cache.internal.Data
import github.gilbertokpl.total.cache.local.PlayerData
import github.gilbertokpl.total.config.files.LangConfig
import github.gilbertokpl.total.util.ItemUtil
import github.gilbertokpl.total.util.MaterialUtil
import org.bukkit.Material
import org.bukkit.SkullType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.util.*

object Playtime {
    private val GLASS_MATERIAL = ItemUtil.item(MaterialUtil["glass"]!!, "§ePLAYTIME", true)
    private const val ITEMS_PER_PAGE = 27
    private const val MAX_ITEMS = 135

    fun setup() {
        val inventoryCache = linkedMapOf<Int, Inventory>()
        var currentPage = 1
        var currentSlot = 0
        var inventory = createPlaytimeInventory(currentPage)

        val sortedPlaytime = PlayerData.playTimeCache.getMap()
            .toList()
            .sortedByDescending { (_, value) -> value }

        sortedPlaytime.take(MAX_ITEMS).forEach { (player, time) ->
            val item = createHeadItem(player, time ?: 0L)
            inventory.setItem(currentSlot, item)
            currentSlot++

            if (currentSlot == ITEMS_PER_PAGE) {
                finalizePage(inventory, currentPage)
                inventoryCache[currentPage] = inventory
                currentPage++
                currentSlot = 0
                inventory = createPlaytimeInventory(currentPage)
            }
        }

        if (currentSlot > 0) {
            finalizePage(inventory, currentPage, isLastPage = true)
            inventoryCache[currentPage] = inventory
        }

        Data.playTimeInventoryCache = Collections.unmodifiableMap(inventoryCache)
    }

    private fun createHeadItem(name: String, time: Long): ItemStack {
        val playerName = LangConfig.playtimeInventoryItemsName.replace("%player%", name)
        val item = ItemStack(MaterialUtil["head"]!!, 1, SkullType.PLAYER.ordinal.toShort())
        val meta = item.itemMeta

        ItemUtil.setDisplayName(meta, playerName)
        meta?.lore = createLore(name, time)
        item.itemMeta = meta

        return item
    }

    private fun createLore(name: String, time: Long): List<String> {
        val t1 = PlayerData.playtimeLocal[name] ?: 0L
        val totalTime = time + if (t1 != 0L) System.currentTimeMillis() - t1 else 0L

        return LangConfig.playtimeInventoryItemsLore.map {
            it.replace("%time%", TotalEssentialsJava.basePlugin.getTime().convertMillisToString(totalTime, true))
        }
    }

    private fun finalizePage(inventory: Inventory, currentPage: Int, isLastPage: Boolean = false) {
        inventory.setItem(27, if (currentPage > 1) {
            ItemUtil.item(Material.HOPPER, LangConfig.playtimeInventoryIconBackName, true)
        } else {
            GLASS_MATERIAL
        })

        for (i in 28..34) {
            inventory.setItem(i, GLASS_MATERIAL)
        }

        inventory.setItem(35, if (isLastPage) {
            GLASS_MATERIAL
        } else {
            ItemUtil.item(Material.ARROW, LangConfig.playtimeInventoryIconNextName, true)
        })
    }

    private fun createPlaytimeInventory(page: Int): Inventory {
        return TotalEssentialsJava.instance.server.createInventory(null, 36, "§ePLAYTIME $page")
    }
}
