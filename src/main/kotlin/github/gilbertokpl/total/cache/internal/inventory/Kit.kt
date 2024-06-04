package github.gilbertokpl.total.cache.internal.inventory

import github.gilbertokpl.total.cache.internal.Data.kitInventoryCache
import github.gilbertokpl.total.cache.internal.Data.kitItemCache
import github.gilbertokpl.total.cache.local.KitsData
import github.gilbertokpl.total.cache.local.KitsData.kitFakeName
import github.gilbertokpl.total.cache.local.KitsData.kitWeight
import github.gilbertokpl.total.cache.local.PlayerData
import github.gilbertokpl.total.config.files.LangConfig
import github.gilbertokpl.total.config.files.MainConfig
import github.gilbertokpl.total.util.ItemUtil
import github.gilbertokpl.total.util.MaterialUtil
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.util.*

internal object Kit {

    private val GLASS_MATERIAL = ItemUtil.item(MaterialUtil["glass"]!!, "§eKIT", true)
    private const val ITEMS_PER_PAGE = 27

    fun setup() {
        val inventoryCache = mutableMapOf<Int, Inventory>()
        val itemCache = mutableMapOf<Int, String>()

        var currentPage = 1
        var currentSlot = 0
        var inventory = createKitsInventory(currentPage)

        val sortedKits = kitWeight.getMap()
            .toList()
            .sortedByDescending { (_, value) -> value }
            .toMap()

        sortedKits.forEach { (kitKey, _) ->
            val kitName = kitFakeName[kitKey]?.takeIf { it.isNotEmpty() } ?: kitKey
            val item = createKitItem(kitKey, kitName)

            val cacheValue = currentSlot + 1 + (ITEMS_PER_PAGE * (currentPage - 1))
            itemCache[cacheValue] = kitKey

            inventory.setItem(currentSlot, item)
            currentSlot++

            if (currentSlot == ITEMS_PER_PAGE) {
                finalizePage(inventory, currentPage, sortedKits.size)
                inventoryCache[currentPage] = inventory
                currentPage++
                currentSlot = 0
                inventory = createKitsInventory(currentPage)
            }
        }

        if (currentSlot > 0) {
            finalizePage(inventory, currentPage, sortedKits.size, true)
            inventoryCache[currentPage] = inventory
        }

        kitItemCache = Collections.unmodifiableMap(itemCache)
        kitInventoryCache = Collections.unmodifiableMap(inventoryCache)
    }

    private fun createKitItem(kitKey: String, kitName: String): ItemStack {
        val item = ItemStack(KitsData.kitItems[kitKey]?.getOrNull(0) ?: ItemStack(Material.CHEST))
        val meta = item.itemMeta

        val itemName = LangConfig.kitsInventoryItemsName.replace("%kitrealname%", kitName)
        ItemUtil.setDisplayName(meta, itemName)

        meta?.lore = LangConfig.kitsInventoryItemsLore.map { it.replace("%realname%", kitKey) }
        item.itemMeta = meta
        return item
    }

    private fun finalizePage(inventory: Inventory, currentPage: Int, totalKits: Int, isLastPage: Boolean = false) {
        inventory.setItem(27, createBackItem(currentPage))
        for (i in 28..34) {
            inventory.setItem(i, GLASS_MATERIAL)
        }
        inventory.setItem(35, if (isLastPage) GLASS_MATERIAL else createNextItem(currentPage, totalKits))
    }

    fun openKitInventory(kit: String, guiNumber: String, player: Player) {
        val inventory = github.gilbertokpl.total.TotalEssentialsJava.instance.server.createInventory(
            null, 45, "§eKit $kit $guiNumber"
        )
        val kitItems = KitsData.kitItems[kit] ?: emptyList()
        kitItems.forEach { inventory.addItem(it) }

        val timeAll = (PlayerData.kitsCache[player]?.get(kit) ?: 0L) + (KitsData.kitTime[kit] ?: 0L)

        for (i in 36..44) {
            inventory.setItem(i, createSpecialItem(i, kit, player, timeAll))
        }

        player.openInventory(inventory)
    }

    private fun createSpecialItem(slot: Int, kit: String, player: Player, timeAll: Long): ItemStack {
        return when (slot) {
            36 -> ItemUtil.item(Material.HOPPER, LangConfig.kitsInventoryIconBackName, true)
            40 -> if (player.hasPermission("totalessentials.commands.editkit")) {
                ItemUtil.item(Material.CHEST, LangConfig.kitsInventoryIconEditKitName, true)
            } else {
                GLASS_MATERIAL
            }
            44 -> createGetItem(slot, kit, player, timeAll)
            else -> GLASS_MATERIAL
        }
    }

    private fun createGetItem(slot: Int, kit: String, player: Player, timeAll: Long): ItemStack {
        return if (player.hasPermission("totalessentials.commands.kit.$kit")) {
            if (timeAll <= System.currentTimeMillis() || timeAll == 0L || player.hasPermission("totalessentials.bypass.kitcatch")) {
                ItemUtil.item(Material.ARROW, LangConfig.kitsGetIcon, true)
            } else {
                val remainingTime = timeAll - System.currentTimeMillis()
                val lore = LangConfig.kitsGetIconLoreTime.map {
                    it.replace("%time%", github.gilbertokpl.total.TotalEssentialsJava.basePlugin.getTime().convertMillisToString(remainingTime, MainConfig.kitsUseShortTime))
                }
                ItemUtil.item(Material.ARROW, LangConfig.kitsGetIconNotCatch, lore, true)
            }
        } else {
            ItemUtil.item(Material.ARROW, LangConfig.kitsGetIconNotCatch, LangConfig.kitsGetIconLoreNotPerm, true)
        }
    }

    private fun createKitsInventory(page: Int): Inventory {
        return github.gilbertokpl.total.TotalEssentialsJava.instance.server.createInventory(null, 36, "§eKits $page")
    }

    private fun createBackItem(currentPage: Int): ItemStack {
        return if (currentPage > 1) {
            ItemUtil.item(Material.HOPPER, LangConfig.kitsInventoryIconBackName, true)
        } else {
            GLASS_MATERIAL
        }
    }

    private fun createNextItem(currentPage: Int, totalKits: Int): ItemStack {
        return if (currentPage * ITEMS_PER_PAGE < totalKits) {
            ItemUtil.item(Material.ARROW, LangConfig.kitsInventoryIconNextName, true)
        } else {
            GLASS_MATERIAL
        }
    }
}
