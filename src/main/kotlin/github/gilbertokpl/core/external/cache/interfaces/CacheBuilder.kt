package github.gilbertokpl.core.external.cache.interfaces

import github.gilbertokpl.core.external.CorePlugin
import org.bukkit.entity.Player

interface CacheBuilder<T> {

    fun update()

    fun load()

    fun unload()

    fun getMap(): Map<String, T?>

    operator fun get(entity: String): T?

    operator fun get(entity: Player): T?

    operator fun set(entity: String, value: T)
    operator fun set(entity: String, value: T, override: Boolean)
    operator fun set(entity: Player, value: T)

    fun remove(entity: String)

    fun remove(entity: Player)

}