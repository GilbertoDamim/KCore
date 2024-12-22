package github.gilbertokpl.core.internal.cache

import github.gilbertokpl.core.external.cache.convert.SerializerBase
import github.gilbertokpl.core.external.cache.interfaces.CacheBuilderV2
import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.concurrent.locks.ReentrantLock

internal class ListCacheBuilder<K, V>(
    private val table: Table,
    private val primaryColumn: Column<String>,
    private val column: Column<K>,
    private val classConvert: SerializerBase<ArrayList<V>, K>
) : CacheBuilderV2<ArrayList<V>, V> {

    private val hashMap = HashMap<String, ArrayList<V>?>()
    private val toUpdate = mutableListOf<String>()
    private val lock = ReentrantLock()

    override fun getMap(): Map<String, ArrayList<V>?> {
        lock.lock()
        return try {
            hashMap.toMap()
        } finally {
            lock.unlock()
        }
    }

    override operator fun get(entity: String): ArrayList<V>? {
        lock.lock()
        return try {
            hashMap[entity.lowercase()]
        } finally {
            lock.unlock()
        }
    }

    override operator fun get(entity: Player): ArrayList<V>? {
        return get(entity.name)
    }

    override operator fun set(entity: Player, value: ArrayList<V>) {
        set(entity.name, value)
    }

    override operator fun set(entity: String, value: ArrayList<V>, override: Boolean) {
        lock.lock()
        try {
            if (override) {
                hashMap[entity.lowercase()] = value
                toUpdate.add(entity.lowercase())
                return
            }
            set(entity, value)
        } finally {
            lock.unlock()
        }
    }

    override operator fun set(entity: String, value: ArrayList<V>) {
        lock.lock()
        try {
            val ent = hashMap[entity.lowercase()] ?: ArrayList()
            ent.addAll(value)
            hashMap[entity.lowercase()] = ent
            toUpdate.add(entity.lowercase())
        } finally {
            lock.unlock()
        }
    }

    override fun remove(entity: Player, value: V) {
        remove(entity.name, value)
    }

    override fun remove(entity: String, value: V) {
        lock.lock()
        try {
            val ent = hashMap[entity.lowercase()] ?: return
            ent.remove(value)
            hashMap[entity.lowercase()] = ent
            toUpdate.add(entity.lowercase())
        } finally {
            lock.unlock()
        }
    }

    override fun remove(entity: Player) {
        remove(entity.name.lowercase())
    }

    override fun remove(entity: String) {
        lock.lock()
        try {
            hashMap[entity.lowercase()] = null
            toUpdate.add(entity.lowercase())
        } finally {
            lock.unlock()
        }
    }

    override fun update() {
        save(toUpdate.toList())
    }

    private fun save(list: List<String>) {
        lock.lock()
        try {
            if (toUpdate.isEmpty()) return

            val existingRows = table.selectAll()
                .where { primaryColumn inList toUpdate }
                .toList()
                .associateBy { it[primaryColumn] }

            val existingKeys = existingRows.keys.toMutableSet()

            for (i in list) {
                if (i in toUpdate) {
                    toUpdate.remove(i)
                    val value = hashMap[i]

                    if (value == null) {
                        existingRows[i]?.let { row ->
                            table.deleteWhere { primaryColumn eq row[primaryColumn] }
                        }
                    } else {
                        if (i !in existingKeys) {
                            table.insert {
                                it[primaryColumn] = i
                                it[column] = classConvert.convertToDatabase(value)
                            }
                            existingKeys.add(i)
                        } else {
                            table.update({ primaryColumn eq i }) {
                                it[column] = classConvert.convertToDatabase(value)
                            }
                        }
                    }
                }
            }
        } finally {
            lock.unlock()
        }
    }

    override fun load() {
        lock.lock()
        try {
            for (i in table.selectAll()) {
                hashMap[i[primaryColumn]] = classConvert.convertToCache(i[column]) ?: ArrayList()
            }
        } finally {
            lock.unlock()
        }
    }

    override fun unload() {
        save(toUpdate.toList())
    }
}
