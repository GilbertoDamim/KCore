package github.gilbertokpl.core.internal.cache

import github.gilbertokpl.core.external.cache.convert.SerializerBase
import github.gilbertokpl.core.external.cache.interfaces.CacheBuilderV2
import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.concurrent.ConcurrentHashMap

internal class ListCacheBuilder<K, V>(
    private val table: Table,
    private val primaryColumn: Column<String>,
    private val column: Column<K>,
    private val classConvert: SerializerBase<ArrayList<V>, K>
) : CacheBuilderV2<ArrayList<V>, V> {

    private val hashMap = ConcurrentHashMap<String, ArrayList<V>?>()
    @Volatile private var toUpdate = mutableSetOf<String>()

    override fun getMap(): Map<String, ArrayList<V>?> = hashMap.toMap()

    override operator fun get(entity: String): ArrayList<V>? = hashMap[entity.lowercase()]

    override operator fun get(entity: Player): ArrayList<V>? = get(entity.name)

    override operator fun set(entity: Player, value: ArrayList<V>) {
        set(entity.name, value)
    }

    override operator fun set(entity: String, value: ArrayList<V>, override: Boolean) {
        if (override) {
            hashMap[entity.lowercase()] = value
        } else {
            set(entity, value)
        }
        toUpdate.add(entity.lowercase())
    }

    override operator fun set(entity: String, value: ArrayList<V>) {
        hashMap.merge(entity.lowercase(), value) { old, new -> old.apply { addAll(new) } }
        toUpdate.add(entity.lowercase())
    }

    override fun remove(entity: Player, value: V) {
        remove(entity.name, value)
    }

    override fun remove(entity: String, value: V) {
        hashMap[entity.lowercase()]?.apply {
            remove(value)
            toUpdate.add(entity.lowercase())
        }
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
                        it[column] = classConvert.convertToDatabase(value)
                    }
                }
                existingRecord != null && value == null -> {
                    table.deleteWhere { primaryColumn eq entity }
                }
                existingRecord != null && value != null -> {
                    table.update({ primaryColumn eq entity }) {
                        it[column] = classConvert.convertToDatabase(value)
                    }
                }
            }
        }
        toUpdate.removeAll(list)
    }

    override fun load() {
        table.selectAll().forEach {
            hashMap[it[primaryColumn]] = classConvert.convertToCache(it[column]) ?: ArrayList()
        }
    }

    override fun unload() {
        save(toUpdate)
    }
}
