package github.gilbertokpl.total.listeners

import github.gilbertokpl.total.cache.local.PlayerData
import github.gilbertokpl.total.cache.local.SpawnData
import github.gilbertokpl.total.config.files.MainConfig
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerRespawnEvent

class PlayerRespawn : Listener {
    @EventHandler
    fun event(e: PlayerRespawnEvent) {
        try {
            playerData(e)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    private fun playerData(e: PlayerRespawnEvent) {
        github.gilbertokpl.total.TotalEssentialsJava.basePlugin.getTask().sync {
            waitFor(20)
            PlayerData.applyPlayerSettings(e.player)

            if (MainConfig.spawnSendToSpawnOnDeath) {
                SpawnData.teleportToSpawn(e.player)
            }

        }
    }
}
