package github.gilbertokpl.total.cache.internal

import github.gilbertokpl.total.cache.internal.inventory.EditKit
import github.gilbertokpl.total.cache.internal.inventory.Kit
import github.gilbertokpl.total.cache.loop.AnnounceLoop
import github.gilbertokpl.total.config.files.MainConfig
import github.gilbertokpl.total.util.TaskUtil

internal object InternalLoader {

    var announcementsListAnnounce = mutableMapOf<Int, String>()

    var deathMessageListReplacer = mutableMapOf<String, String>()

    fun start(announce: List<String>, deathMessage: List<String>, deathMessageEntity: List<String>) {
        try {
            deathMessageListReplacer.clear()
            populateDeathMessageReplacer(deathMessageEntity)
            populateDeathMessageReplacer(deathMessage)

            val newAnnounceMap = announce.withIndex().associate { it.index + 1 to it.value }
            if (newAnnounceMap != announcementsListAnnounce) {
                announcementsListAnnounce = newAnnounceMap.toMutableMap()
                if (MainConfig.announcementsEnabled) {
                    TaskUtil.restartInternalExecutor()
                    AnnounceLoop.start(announce.size, MainConfig.announcementsTime)
                }
            }

            EditKit.setup()
            Kit.setup()

        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    private fun populateDeathMessageReplacer(messages: List<String>) {
        for (d in messages) {
            val to = d.split("-")
            if (to.size == 2) {
                deathMessageListReplacer[to[0].lowercase()] = to[1]
            }
        }
    }
}
