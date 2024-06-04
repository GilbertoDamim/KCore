package github.gilbertokpl.total.cache.internal

import github.gilbertokpl.total.config.files.LangConfig
import github.gilbertokpl.total.util.TaskUtil
import org.bukkit.entity.Player
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

internal data class DataTeleport(
    val p: Player,
    var otherPlayer: Player?,
    var wait: Boolean
) {
    companion object {
        private val tpaData = HashMap<Player, DataTeleport>()

        operator fun get(p: Player) = tpaData[p]

        fun remove(p: Player) {
            tpaData.remove(p)
        }

        fun checkTpa(p: Player): Boolean {
            return tpaData.contains(p)
        }

        fun checkOtherTpa(p: Player): Boolean {
            return tpaData.values.any { it.otherPlayer == p }
        }

        fun getTpa(p: Player): Player? {
            return tpaData.entries.find { it.value.otherPlayer == p }?.key
        }

        fun createNewTpa(pSender: Player, pReceived: Player, time: Int) {
            val dataTeleport = DataTeleport(pSender, pReceived, true)
            tpaData[pSender] = dataTeleport

            CompletableFuture.runAsync({
                TimeUnit.SECONDS.sleep(time.toLong())
                val sender = tpaData[pSender]
                if (sender?.wait == true) {
                    try {
                        github.gilbertokpl.total.TotalEssentialsJava.instance.server.scheduler.runTask(
                            github.gilbertokpl.total.TotalEssentialsJava.instance
                        ) { BukkitRunnable ->
                            pSender.sendMessage(
                                LangConfig.tpaRequestOtherDenyTime.replace(
                                    "%player%",
                                    pReceived.name
                                )
                            )
                        }
                    } finally {
                        tpaData.remove(pSender)
                    }
                }
            }, TaskUtil.getInternalExecutor())
        }
    }
}
