package github.gilbertokpl.core.internal.cache

import github.gilbertokpl.core.external.cache.convert.SerializerBase
import github.gilbertokpl.core.external.cache.interfaces.CacheBuilder
import github.gilbertokpl.total.config.files.MainConfig
import org.bukkit.Location
import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.concurrent.locks.ReentrantLock

internal class LocationCacheBuilder(
    private val table: Table,
    private val primaryColumn: Column<String>,
    private val column: Column<String>,
    private val classConvert: SerializerBase<Location?, String>
) : CacheBuilder<Location?> {

    private val hashMap = HashMap<String, Location?>()
    private val toUpdate = mutableSetOf<String>()
    private val lock = ReentrantLock()

    override fun getMap(): Map<String, Location?> {
        lock.lock()
        return try {
            hashMap.toMap()
        } finally {
            lock.unlock()
        }
    }

    override operator fun get(entity: String): Location? {
        lock.lock()
        return try {
            hashMap[entity.lowercase()]
        } finally {
            lock.unlock()
        }
    }

    override operator fun get(entity: Player): Location? {
        return get(entity.name)
    }

    override operator fun set(entity: Player, value: Location?) {
        set(entity.name, value)
    }

    override fun set(entity: String, value: Location?, override: Boolean) {
        set(entity, value)
    }

    override operator fun set(entity: String, value: Location?) {
        lock.lock()
        try {
            hashMap[entity.lowercase()] = value
            toUpdate.add(entity.lowercase())
        } finally {
            lock.unlock()
        }
    }

    override fun remove(entity: Player) {
        remove(entity.name)
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
            table.selectAll().forEach { row ->
                val location = classConvert.convertToCache(row[column]) ?: return@forEach

                val primaryKey = row[primaryColumn]

                if (column.name == "Back" &&
                    MainConfig.backDisabledWorlds.any { disabledWorld ->
                        location.world?.name.equals(disabledWorld, ignoreCase = true)
                    }
                ) {
                    hashMap[primaryKey] = null
                    toUpdate.add(primaryKey)
                } else {
                    hashMap[primaryKey] = location
                }
            }
        } finally {
            lock.unlock()
        }
    }

    override fun unload() {
        save(toUpdate.toList())
    }
}
