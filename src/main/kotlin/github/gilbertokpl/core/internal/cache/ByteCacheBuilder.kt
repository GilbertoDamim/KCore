package github.gilbertokpl.core.internal.cache

import github.gilbertokpl.core.external.cache.interfaces.CacheBuilder
import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.concurrent.ConcurrentHashMap

internal class ByteCacheBuilder<T>(
    private val table: Table,
    private val primaryColumn: Column<String>,
    private val column: Column<T>
) : CacheBuilder<T> {

    private val hashMap = ConcurrentHashMap<String, T?>()
    @Volatile private var toUpdate = mutableSetOf<String>()

    override fun getMap(): Map<String, T?> = hashMap.toMap()

    override operator fun get(entity: String): T? = hashMap[entity.lowercase()]

    override operator fun get(entity: Player): T? = get(entity.name)

    override operator fun set(entity: Player, value: T) {
        set(entity.name, value)
    }

    override fun set(entity: String, value: T) {
        hashMap[entity.lowercase()] = value
        toUpdate.add(entity.lowercase())
    }

    override fun set(entity: String, value: T, override: Boolean) {
        set(entity, value)
    }

    override fun remove(entity: Player) {
        remove(entity.name)
    }

    override fun remove(entity: String) {
        hashMap[entity.lowercase()] = null
        toUpdate.add(entity.lowercase())
    }

    override fun update() {
        save(toUpdate)
    }

    private fun save(list: Set<String>) {
        list.forEach { entity ->
            val value = hashMap[entity]
            val existingRecord = table.selectAll().where { primaryColumn eq entity }.singleOrNull()
            when {
                existingRecord == null && value != null -> {
                    table.insert {
                        it[primaryColumn] = entity
                        it[column] = value
                    }
                }
                existingRecord != null && value == null -> {
                    table.deleteWhere { primaryColumn eq entity }
                }
                existingRecord != null && value != null -> {
                    table.update({ primaryColumn eq entity }) {
                        it[column] = value
                    }
                }
            }
        }
        toUpdate.removeAll(list)
    }

    override fun load() {
        table.selectAll().forEach {
            hashMap[it[primaryColumn]] = it[column]
        }
    }

    override fun unload() {
        save(toUpdate)
    }
}
