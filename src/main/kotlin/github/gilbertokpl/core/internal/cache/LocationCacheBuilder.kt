package github.gilbertokpl.core.internal.cache

import github.gilbertokpl.core.external.cache.convert.SerializerBase
import github.gilbertokpl.core.external.cache.interfaces.CacheBuilder
import org.bukkit.Location
import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.concurrent.ConcurrentHashMap

internal class LocationCacheBuilder(
    private val table: Table,
    private val primaryColumn: Column<String>,
    private val column: Column<String>,
    private val classConvert: SerializerBase<Location?, String>
) : CacheBuilder<Location?> {

    private val hashMap = ConcurrentHashMap<String, Location?>()
    @Volatile private var toUpdate = mutableSetOf<String>()

    override fun getMap(): Map<String, Location?> = hashMap.toMap()

    override operator fun get(entity: String): Location? = hashMap[entity.lowercase()]

    override operator fun get(entity: Player): Location? = get(entity.name)

    override operator fun set(entity: Player, value: Location?) {
        set(entity.name, value)
    }

    override fun set(entity: String, value: Location?, override: Boolean) {
        set(entity, value)
    }

    override operator fun set(entity: String, value: Location?) {
        hashMap[entity.lowercase()] = value
        toUpdate.add(entity.lowercase())
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
                    table.update({ primaryColumn eq entity }) {
                        it[column] = ""
                    }
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
            val location = classConvert.convertToCache(it[column]) ?: return@forEach
            hashMap[it[primaryColumn]] = location
        }
    }

    override fun unload() {
        save(toUpdate)
    }
}
