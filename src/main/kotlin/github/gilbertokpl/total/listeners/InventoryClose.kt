package github.gilbertokpl.total.listeners

import github.gilbertokpl.total.cache.internal.Data
import github.gilbertokpl.total.cache.internal.inventory.Kit
import github.gilbertokpl.total.cache.local.KitsData
import github.gilbertokpl.total.cache.local.test.LimitData
import github.gilbertokpl.total.cache.local.PlayerData
import github.gilbertokpl.total.cache.local.VipData
import github.gilbertokpl.total.config.files.LangConfig
import github.gilbertokpl.total.config.files.MainConfig
import github.gilbertokpl.total.util.PlayerUtil
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.ItemStack

class InventoryClose : Listener {
    @EventHandler
    fun event(e: InventoryCloseEvent) {
        if (MainConfig.kitsActivated) {
            try {
                if (editKitInventoryCloseEvent(e)) return
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
        if (MainConfig.invseeActivated) {
            try {
                invseeInventoryCloseEvent(e)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
        if (MainConfig.vipActivated) {
            try {
                vipInventoryCloseEvent(e)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }

        if (MainConfig.limitActivated) {
            try {
                limitInventoryCloseEvent(e)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    //limit

    private fun limitInventoryCloseEvent(e: InventoryCloseEvent): Boolean {
        val p = e.player as Player
        Data.playerVipEdit[p].also {
            if (it == null) return false

            val array = ArrayList<ItemStack>()

            for (i in e.inventory.contents.filterNotNull()) {
                array.add(i)
            }

            LimitData.limitItems[it, array] = true

        }
        return true
    }
    //vips
    private fun vipInventoryCloseEvent(e: InventoryCloseEvent): Boolean {
        val p = e.player as Player
        Data.playerVipEdit[p].also {

            if (it == null) return false

            val array = ArrayList<ItemStack>()

            for (i in e.inventory.contents.filterNotNull()) {
                array.add(i)
            }

            VipData.vipItems[it, array] = true

            Data.playerVipEdit.remove(p)

            PlayerUtil.sendMessage(e.player.name, LangConfig.VipsUpdateItems.replace("%vip%", it))

        }

        return true
    }

    //editkit
    private fun editKitInventoryCloseEvent(e: InventoryCloseEvent): Boolean {
        val p = e.player as Player
        Data.playerEditKit[p].also {
            if (it == null) return false
            Data.playerEditKit.remove(p)

            val array = ArrayList<ItemStack>()

            for (i in e.inventory.contents.filterNotNull()) {
                array.add(i)
            }

            KitsData.kitItems[it, array] = true

            val name = KitsData.kitFakeName[it]

            PlayerUtil.sendMessage(
                e.player.name,
                LangConfig.kitsEditKitSuccess.replace(
                    "%kit%",
                    if (name == null || name == "") it else name
                )
            )
        }

        Kit.setup()

        return true
    }

    private fun invseeInventoryCloseEvent(e: InventoryCloseEvent) {
        val p = e.player as Player

        if (PlayerData.inInvSee[p] != null && e.inventory.type == InventoryType.PLAYER) {
            PlayerData.inInvSee[p] = null
        }
    }
}
