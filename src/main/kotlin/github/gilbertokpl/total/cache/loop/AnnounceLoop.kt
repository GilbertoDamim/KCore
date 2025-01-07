package github.gilbertokpl.total.cache.loop

import github.gilbertokpl.total.TotalEssentialsJava
import github.gilbertokpl.total.cache.internal.InternalLoader
import github.gilbertokpl.total.util.TaskUtil
import java.util.concurrent.TimeUnit

internal object AnnounceLoop {
    private var currentAnnouncementIndex = 0
    private var maxAnnouncementIndex = 0

    fun start(maxAnnouncements: Int, intervalInMinutes: Int) {
        if (maxAnnouncements <= 0) {
            throw IllegalArgumentException("maxAnnouncements deve ser maior que 0.")
        }
        if (intervalInMinutes <= 0) {
            throw IllegalArgumentException("intervalInMinutes deve ser maior que 0.")
        }

        maxAnnouncementIndex = maxAnnouncements - 1

        TaskUtil.getInternalExecutor().scheduleWithFixedDelay(
            ::sendAnnouncement,
            intervalInMinutes.toLong(),
            intervalInMinutes.toLong(),
            TimeUnit.MINUTES
        )
    }

    private fun sendAnnouncement() {
        val onlinePlayers = TotalEssentialsJava.basePlugin.getReflection().getPlayers()
        val announcement = InternalLoader.announcementsListAnnounce.getOrDefault(currentAnnouncementIndex, "")

        onlinePlayers.forEach { player ->
            player.sendMessage(announcement.replace("%players_online%", onlinePlayers.size.toString()))
        }

        currentAnnouncementIndex =
            if (currentAnnouncementIndex >= maxAnnouncementIndex) 0 else currentAnnouncementIndex + 1
    }
}

