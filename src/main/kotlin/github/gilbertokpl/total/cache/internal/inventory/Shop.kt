package github.gilbertokpl.total.cache.internal.inventory

import github.gilbertokpl.total.cache.internal.Data
import github.gilbertokpl.total.cache.local.ShopData
import github.gilbertokpl.total.config.files.LangConfig
import github.gilbertokpl.total.util.ItemUtil
import github.gilbertokpl.total.util.MaterialUtil
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.SkullType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.util.*

object Shop {
    private val GLASS_MATERIAL = ItemUtil.item(MaterialUtil["glass"]!!, "§eSHOP", true)
    private const val ITEMS_PER_PAGE = 27

    fun setup() {
        val inventoryCache = linkedMapOf<Int, Inventory>()
        val itemCache = linkedMapOf<Int, String>()
        var currentPage = 1
        var currentSlot = 0
        var inventory = createShopInventory(currentPage)

        val sortedShops = ShopData.shopVisits.getMap().asSequence()
            .sortedByDescending { (_, value) -> value }

        sortedShops.forEach { (shopKey, visits) ->
            val item = createShopItem(shopKey, visits ?: 0)
            val cacheValue = (currentSlot + 1) + ((currentPage - 1) * ITEMS_PER_PAGE)
            itemCache[cacheValue] = shopKey

            inventory.setItem(currentSlot, item)
            currentSlot++

            if (currentSlot == ITEMS_PER_PAGE) {
                finalizePage(inventory, currentPage)
                inventoryCache[currentPage] = inventory
                currentPage++
                currentSlot = 0
                inventory = createShopInventory(currentPage)
            }
        }

        if (currentSlot > 0) {
            finalizePage(inventory, currentPage, isLastPage = true)
            inventoryCache[currentPage] = inventory
        }

        Data.shopInventoryCache = Collections.unmodifiableMap(inventoryCache)
        Data.shopItemCache = Collections.unmodifiableMap(itemCache)
    }

    private fun createShopItem(shopKey: String, visits: Int): ItemStack {
        val name = LangConfig.shopInventoryItemsName.replace("%player%", shopKey)
        val item = ItemStack(MaterialUtil["head"]!!, 1, SkullType.PLAYER.ordinal.toShort())
        val meta = item.itemMeta

        ItemUtil.setDisplayName(meta, name)

        val isOpen = ShopData.shopOpen[shopKey] ?: false
        meta?.lore = LangConfig.shopInventoryItemsLore.map {
            it.replace("%visits%", visits.toString())
                .replace("%open%", if (isOpen) LangConfig.shopOpen else LangConfig.shopClosed)
        }
        item.itemMeta = meta

        return item
    }

    private fun finalizePage(inventory: Inventory, currentPage: Int, isLastPage: Boolean = false) {
        inventory.setItem(27, if (currentPage > 1) {
            ItemUtil.item(Material.HOPPER, LangConfig.shopInventoryIconBackName, true)
        } else {
            GLASS_MATERIAL
        })

        for (i in 28..34) {
            inventory.setItem(i, GLASS_MATERIAL)
        }

        inventory.setItem(35, if (isLastPage) {
            GLASS_MATERIAL
        } else {
            ItemUtil.item(Material.ARROW, LangConfig.shopInventoryIconNextName, true)
        })
    }

    private fun createShopInventory(page: Int): Inventory {
        return Bukkit.createInventory(null, 36, "§eSHOP $page")
    }
}
