package github.gilbertokpl.total.listeners

import github.gilbertokpl.total.cache.internal.Data
import github.gilbertokpl.total.cache.internal.inventory.EditKit.editKitGui
import github.gilbertokpl.total.cache.internal.inventory.EditKit.editKitGuiItems
import github.gilbertokpl.total.cache.internal.inventory.Kit.openKitInventory
import github.gilbertokpl.total.cache.internal.inventory.Shop
import github.gilbertokpl.total.cache.local.KitsData
import github.gilbertokpl.total.cache.local.LoginData
import github.gilbertokpl.total.cache.local.PlayerData
import github.gilbertokpl.total.cache.local.ShopData
import github.gilbertokpl.total.config.files.LangConfig
import github.gilbertokpl.total.config.files.MainConfig
import github.gilbertokpl.total.util.ItemUtil
import github.gilbertokpl.total.util.PermissionUtil
import github.gilbertokpl.total.util.PlayerUtil
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.ItemStack

class InventoryClick : Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    fun event(e: InventoryClickEvent) {

        if (e.whoClicked is Player && !LoginData.isPlayerLoggedIn(e.whoClicked as Player)) {
            e.isCancelled = true
            return
        }

        if (e.slot == 45) {
            return
        }
        if (MainConfig.kitsActivated) {
            try {
                if (editKitInventoryClickEvent(e)) return
            } catch (e: Throwable) {
                e.printStackTrace()
            }
            try {
                if (kitGuiEvent(e)) return
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
        if (MainConfig.containersBlockShiftEnable) {
            try {
                blockShiftInInventory(e)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
        if (MainConfig.addonsColorInAnvil) {
            try {
                anvilColor(e)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
        if (MainConfig.invseeActivated) {
            try {
                invSeeEvent(e)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
        if (MainConfig.shopActivated) {
            try {
                shopGuiEvent(e)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
        if (MainConfig.playtimeActivated) {
            try {
                playtimeGuiEvent(e)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
        if (MainConfig.vipActivated) {
            try {
                vipGuiEvent(e)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    //vips event
    private fun vipGuiEvent(e: InventoryClickEvent): Boolean {
        e.currentItem ?: return false
        val inventoryName = try {
            e.view.title.split(" ")
        } catch (e: NullPointerException) {
            return false
        }
        if (inventoryName[0] == "§eVipItens") {
            e.isCancelled = true
            val p = e.whoClicked as Player
            if (e.rawSlot != e.slot) {
                return false
            }
            if (e.click.isLeftClick) {

                var inventorySpace = 0

                for (i in 0..35) {
                    if (p.inventory.getItem(i) == null) {
                        inventorySpace += 1
                    }
                }

                if (inventorySpace == 0) {
                    return false
                }

                p.inventory.addItem(e.currentItem)

                e.currentItem = ItemStack(Material.AIR)

                val list = ArrayList<ItemStack>()

                for (i in e.inventory.contents) {
                    if (i == null || i == ItemStack(Material.AIR)) continue
                    list.add(i)
                }

                PlayerData.vipItems[p.name, list] = true

            }
            return true
        }
        return false
    }

    //playtime event
    private fun playtimeGuiEvent(e: InventoryClickEvent): Boolean {
        e.currentItem ?: return false
        val inventoryName = try {
            e.view.title.split(" ")
        } catch (e: NullPointerException) {
            return false
        }
        if (inventoryName[0] == "§ePLAYTIME") {
            e.isCancelled = true
            val p = e.whoClicked as Player
            val number = e.slot
            if (number == 27 && inventoryName[1].toInt() > 1) {
                p.openInventory(Data.playTimeInventoryCache[inventoryName[1].toInt() - 1]!!)
            }
            if (number == 35) {
                val check = Data.playTimeInventoryCache[inventoryName[1].toInt() + 1]
                if (check != null) {
                    p.openInventory(check)
                }
            }
            return true
        }
        return false
    }

    //shop event
    private fun shopGuiEvent(e: InventoryClickEvent): Boolean {
        e.currentItem ?: return false
        val inventoryName = try {
            e.view.title.split(" ")
        } catch (e: NullPointerException) {
            return false
        }
        if (inventoryName[0] == "§eSHOP") {
            e.isCancelled = true
            val p = e.whoClicked as Player
            val number = e.slot
            if (number < 27) {
                val loc = Data.shopItemCache[(number + 1) + ((inventoryName[1].toInt() - 1) * 27)]
                if (loc != null) {
                    if (!ShopData.shopOpen[loc]!!) {
                        p.sendMessage(LangConfig.shopClosedMessage)
                        return false
                    }
                    PlayerUtil.shopTeleport(p, loc)
                    if (loc.lowercase() != p.name.lowercase()) {
                        ShopData.shopVisits[loc] = ShopData.shopVisits[loc]!!.plus(1)
                    }
                }
                return true
            }
            if (number == 27 && inventoryName[1].toInt() > 1) {
                p.openInventory(Data.shopInventoryCache[inventoryName[1].toInt() - 1]!!)
            }
            if (number == 30 && p.hasPermission("totalessentials.commands.shop.set")) {
                ShopData.createNewShop(p.location, p)
                p.sendMessage(LangConfig.shopCreateShopSuccess)
                Shop.setup()
                p.closeInventory()
            }
            if (number == 32 && p.hasPermission("totalessentials.commands.shop.set")) {

                if (!ShopData.checkIfShopExists(p.name.lowercase())) {
                    return true
                }

                val new = ShopData.shopOpen[p]?.not() ?: false
                ShopData.shopOpen[p] = new
                if (new) {
                    p.sendMessage(LangConfig.shopSwitchMessage.replace("%open%", LangConfig.shopOpen))
                } else {
                    p.sendMessage(LangConfig.shopSwitchMessage.replace("%open%", LangConfig.shopClosed))
                }
                Shop.setup()
                p.closeInventory()
            }
            if (number == 35) {
                val check = Data.shopInventoryCache[inventoryName[1].toInt() + 1]
                if (check != null) {
                    p.openInventory(check)
                }
            }
            return true
        }
        return false
    }

    //kit event
    private fun kitGuiEvent(e: InventoryClickEvent): Boolean {
        e.currentItem ?: return false
        val inventoryName = try {
            e.view.title.split(" ")
        } catch (e: NullPointerException) {
            return false
        }
        if (inventoryName[0] == "§eKit") {
            e.isCancelled = true
            val meta = e.currentItem!!.itemMeta ?: return false
            val p = e.whoClicked as Player

            val number = e.slot
            if (number == 36) {
                p.openInventory(Data.kitInventoryCache[inventoryName[2].toInt()]!!)
            }
            if (number == 40 && meta.displayName == LangConfig.kitsInventoryIconEditKitName && p.hasPermission(
                    "totalessentials.commands.editkit"
                )
            ) {
                editKitGui(p, inventoryName[1])
            }
            if (number == 44) {
                if (meta.displayName == LangConfig.kitsGetIcon) {
                    ItemUtil.pickupKit(p, inventoryName[1].lowercase())
                    p.closeInventory()
                    return true
                }
                if (meta.displayName == LangConfig.kitsGetIconNotCatch) {
                    openKitInventory(inventoryName[1], inventoryName[2], p)
                }
            }
            return true
        }
        if (inventoryName[0] == "§eKits") {
            e.isCancelled = true
            val p = e.whoClicked as Player
            val number = e.slot
            if (number < 27) {
                val kit =
                    Data.kitItemCache[(number + 1) + ((inventoryName[1].toInt() - 1) * 27)]
                if (kit != null) {
                    openKitInventory(kit, inventoryName[1], p)
                }
                return true
            }
            if (number == 27 && inventoryName[1].toInt() > 1) {
                p.openInventory(Data.kitInventoryCache[inventoryName[1].toInt() - 1]!!)
            }
            if (number == 35) {
                val check = Data.kitInventoryCache[inventoryName[1].toInt() + 1]
                if (check != null) {
                    p.openInventory(check)
                }
            }
            return true
        }
        return false
    }

    //editkit event
    private fun editKitInventoryClickEvent(e: InventoryClickEvent): Boolean {
        e.currentItem ?: return false
        val inventoryName = try {
            e.view.title.split(" ")
        } catch (e: NullPointerException) {
            return false
        }
        if (inventoryName[0].equals("§eEditKit", true)) {
            e.isCancelled = true
            val number = e.slot
            val p = e.whoClicked as Player

            //items
            if (number == 10) {
                p.closeInventory()
                editKitGuiItems(p, inventoryName[1], KitsData.kitItems[inventoryName[1]]!!)
                Data.playerEditKit[p] = inventoryName[1]
            }

            //time
            if (number == 12) {
                p.closeInventory()
                p.sendMessage(LangConfig.kitsEditKitInventoryTimeMessage)
                Data.playerEditKitChat[p] = "time-${inventoryName[1]}"
            }

            //name
            if (number == 14) {
                p.closeInventory()
                p.sendMessage(LangConfig.kitsEditKitInventoryNameMessage)
                Data.playerEditKitChat[p] = "name-${inventoryName[1]}"
            }

            //weight
            if (number == 16) {
                p.closeInventory()
                p.sendMessage(LangConfig.kitsEditKitInventoryWeightMessage)
                Data.playerEditKitChat[p] = "weight-${inventoryName[1]}"
            }

            return true
        }

        return false
    }

    //block shift
    private fun blockShiftInInventory(e: InventoryClickEvent) {
        if ((e.currentItem ?: return).type == Material.AIR) return
        if (e.click.isShiftClick &&
            MainConfig.containersBlockShift.contains(e.inventory.type.name.lowercase()) &&
            !e.whoClicked.hasPermission("totalessentials.bypass.shiftcontainer")
        ) {
            e.whoClicked.sendMessage(LangConfig.generalNotPermAction)
            e.isCancelled = true
            return
        }
    }

    //anvil color

    private fun anvilColor(e: InventoryClickEvent) {
        if (e.inventory.type == InventoryType.ANVIL && e.slotType == InventoryType.SlotType.RESULT) {
            val item = e.currentItem ?: return
            if (item.type == Material.AIR || !item.itemMeta!!.hasDisplayName()) {
                return
            }
            val meta = item.itemMeta ?: return
            val name = meta.displayName
            val oldItem = e.inventory.getItem(0) ?: return
            val oldMeta = oldItem.itemMeta ?: return
            if (oldMeta.hasDisplayName()) {
                val oldName = oldMeta.displayName.replace("§", "")
                if (name == oldName) {
                    try {
                        ItemUtil.setDisplayName(meta, oldMeta.displayName)
                    } catch (e: NoSuchMethodError) {
                        meta.setDisplayName(oldMeta.displayName)
                    }
                    item.itemMeta = meta
                    e.currentItem = item
                    return
                }
            }
            ItemUtil.setDisplayName(meta, PermissionUtil.colorPermission(e.whoClicked as Player, name))
            item.itemMeta = meta
            e.currentItem = item
        }
    }

    private fun invSeeEvent(e: InventoryClickEvent) {
        val p = e.whoClicked as Player
        val otherPlayer = PlayerData.inInvSee[p]
        if (e.inventory.type == InventoryType.PLAYER && otherPlayer != null) {

            if (!otherPlayer.isOnline) {
                p.closeInventory()
                p.sendMessage(LangConfig.invseePlayerLeave)
            }

            if (p.hasPermission("totalessentials.commands.invsee")
                && !p.hasPermission("totalessentials.commands.invsee.admin")
            ) {
                e.isCancelled = true
            }
        }
    }
}
