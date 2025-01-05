package github.gilbertokpl.core.internal.cache

import github.gilbertokpl.core.external.cache.convert.SerializerBase
import github.gilbertokpl.core.external.cache.interfaces.CacheBuilderV2
import github.gilbertokpl.total.TotalEssentialsJava
import github.gilbertokpl.total.cache.local.PlayerData
import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.concurrent.locks.ReentrantLock

internal class HashMapCacheBuilder<T, V, K>(
    private val table: Table,
    private val primaryColumn: Column<String>,
    private val column: Column<T>,
    private val classConvert: SerializerBase<HashMap<V, K>, T>
) : CacheBuilderV2<HashMap<V, K>, V> {

    private val hashMap = HashMap<String, HashMap<V, K>?>()
    private val toUpdate = mutableSetOf<String>()
    private val lock = ReentrantLock()

    override fun getMap(): Map<String, HashMap<V, K>?> {
        lock.lock()
        return try {
            hashMap.toMap()
        } finally {
            lock.unlock()
        }
    }

    override operator fun get(entity: String): HashMap<V, K>? {
        return hashMap[entity.lowercase()]
    }

    override operator fun get(entity: Player): HashMap<V, K>? {
        return get(entity.name.lowercase())
    }

    override operator fun set(entity: Player, value: HashMap<V, K>) {
        set(entity.name.lowercase(), value)
    }

    override fun set(entity: String, value: HashMap<V, K>, override: Boolean) {
        lock.lock()
        try {
            val lowerKey = entity.lowercase()
            if (override) {
                hashMap[lowerKey] = value
            } else {
                val existing = hashMap[lowerKey] ?: HashMap()
                existing.putAll(value)
                hashMap[lowerKey] = existing
            }
            toUpdate.add(lowerKey)
        } finally {
            lock.unlock()
        }
    }

    override operator fun set(entity: String, value: HashMap<V, K>) {
        set(entity, value, override = false)
    }

    override fun remove(entity: Player, value: V) {
        remove(entity.name.lowercase(), value)
    }

    override fun remove(entity: String, value: V) {
        lock.lock()
        try {
            val lowerKey = entity.lowercase()
            val existing = hashMap[lowerKey] ?: return
            existing.remove(value)
            hashMap[lowerKey] = existing
            toUpdate.add(lowerKey)
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

    private fun save(list: List<String>) {
        if (toUpdate.isEmpty()) return

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
                            TotalEssentialsJava.basePlugin.logger.log("Removendo Entidade chamada: $i, coluna: $column")
                            table.deleteWhere { primaryColumn eq row[primaryColumn] }
                        }
                    } else {
                        if (i !in existingKeys) {
                            table.insert {
                                val newValue = classConvert.convertToDatabase(value)
                                TotalEssentialsJava.basePlugin.logger.log("Setando valor da entidade: $i, coluna: $column, valor: $newValue")
                                it[primaryColumn] = i
                                it[column] = newValue
                            }
                            existingKeys.add(i)
                        } else {
                            val newValue = classConvert.convertToDatabase(value)
                            TotalEssentialsJava.basePlugin.logger.log("Atualizando valor da entidade: $i, coluna: $column, valor: $newValue")
                            table.update({ primaryColumn eq i }) {
                                it[column] = newValue
                            }
                        }
                    }
                }
            }
        } finally {
            lock.unlock()
        }
    }

    override fun update() {
        save(toUpdate.toList())
    }

    override fun load() {
        lock.lock()
        try {
            for (i in table.selectAll()) {
                hashMap[i[primaryColumn].lowercase()] = classConvert.convertToCache(i[column]) ?: HashMap()
            }
        } finally {
            lock.unlock()
        }

    }

    override fun unload() {
        update()
        //Verificação PlayerData
        if (primaryColumn != PlayerData.primaryColumn) return
        for (i in table.selectAll()) {
            val value = hashMap[i[primaryColumn].lowercase()] ?: continue
            if (classConvert.convertToCache(i[column]) != value) {
                val name = i[primaryColumn]
                TotalEssentialsJava.basePlugin.logger.log("Novo Erro encontrado da entidade: $name, coluna: $column, setando novo valor: $value")
                set(name, value)
            }
        }
        update()
    }
}
