package github.gilbertokpl.core.internal.cache

import github.gilbertokpl.core.external.cache.convert.SerializerBase
import github.gilbertokpl.core.external.cache.interfaces.CacheBuilder
import org.bukkit.Location
import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.*

internal class LocationCacheBuilder(
    private val table: Table,
    private val primaryColumn: Column<String>,
    private val column: Column<String>,
    private val classConvert: SerializerBase<Location?, String>
) : CacheBuilder<Location?> {

    private val hashMap = HashMap<String, Location?>()
    private val toUpdate = mutableListOf<String>()

    override fun getMap(): Map<String, Location?> {
        return hashMap.toMap()
    }

    override operator fun get(entity: String): Location? {
        return hashMap[entity.lowercase()]
    }

    override operator fun get(entity: Player): Location? {
        return hashMap[entity.name.lowercase()]
    }

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
        save(toUpdate.toList())
    }

    private fun save(list: List<String>) {
        val currentHash = hashMap

        for (i in list) {
            toUpdate.remove(i)
            val tab = table.select { primaryColumn eq i }
            val value = currentHash[i]

            if (tab.empty()) {
                if (value == null) continue
                table.insert {
                    it[primaryColumn] = i
                    it[column] = classConvert.convertToDatabase(value)
                }
            } else {
                if (value == null) {
                    table.update({ primaryColumn eq i }) {
                        it[column] = ""
                    }
                    continue
                }
                table.update({ primaryColumn eq i }) {
                    it[column] = classConvert.convertToDatabase(value)
                }
            }
        }
    }

    override fun load() {
        for (i in table.selectAll()) {
            val location = classConvert.convertToCache(i[column]) ?: continue
            hashMap[i[primaryColumn]] = location
        }
    }

    override fun unload() {
        save(toUpdate.toList())
    }
}