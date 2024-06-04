package github.gilbertokpl.core.internal.cache

import github.gilbertokpl.core.external.cache.convert.SerializerBase
import github.gilbertokpl.core.external.cache.interfaces.CacheBuilderV2
import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.concurrent.ConcurrentHashMap

internal class HashMapCacheBuilder<T, V, K>(
    private val table: Table,
    private val primaryColumn: Column<String>,
    private val column: Column<T>,
    private val classConvert: SerializerBase<HashMap<V, K>, T>
) : CacheBuilderV2<HashMap<V, K>, V> {

    private val hashMap = ConcurrentHashMap<String, HashMap<V, K>?>()
    @Volatile private var toUpdate = mutableSetOf<String>()

    override fun getMap(): Map<String, HashMap<V, K>?> = hashMap.toMap()

    override operator fun get(entity: String): HashMap<V, K>? = hashMap[entity.lowercase()]

    override operator fun get(entity: Player): HashMap<V, K>? = get(entity.name)

    override operator fun set(entity: Player, value: HashMap<V, K>) {
        set(entity.name, value)
    }

    override fun set(entity: String, value: HashMap<V, K>, override: Boolean) {
        if (override) {
            hashMap[entity.lowercase()] = value
        } else {
            set(entity, value)
        }
        toUpdate.add(entity.lowercase())
    }

    override operator fun set(entity: String, value: HashMap<V, K>) {
        hashMap.merge(entity.lowercase(), value) { old, new -> old.apply { putAll(new) } }
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

    override fun update() {
        save(toUpdate)
    }

    override fun load() {
        table.selectAll().forEach {
            hashMap[it[primaryColumn]] = classConvert.convertToCache(it[column]) ?: HashMap()
        }
    }

    override fun unload() {
        save(toUpdate)
    }
}
