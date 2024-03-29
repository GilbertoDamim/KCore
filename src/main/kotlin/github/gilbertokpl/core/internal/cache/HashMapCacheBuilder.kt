package github.gilbertokpl.core.internal.cache

import github.gilbertokpl.core.external.cache.convert.SerializerBase
import github.gilbertokpl.core.external.cache.interfaces.CacheBuilderV2
import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

internal class HashMapCacheBuilder<T, V, K>(
    private val table: Table,
    private val primaryColumn: Column<String>,
    private val column: Column<T>,
    private val classConvert: SerializerBase<HashMap<V, K>, T>
) : CacheBuilderV2<HashMap<V, K>, V> {

    private val hashMap = HashMap<String, HashMap<V, K>?>()
    private val toUpdate = mutableListOf<String>()

    override fun getMap(): Map<String, HashMap<V, K>?> {
        return hashMap.toMap()
    }

    override operator fun get(entity: String): HashMap<V, K>? {
        return hashMap[entity.lowercase()]
    }

    override operator fun get(entity: Player): HashMap<V, K>? {
        return hashMap[entity.name.lowercase()]
    }

    override operator fun set(entity: Player, value: HashMap<V, K>) {
        set(entity.name, value)
    }

    override fun set(entity: String, value: HashMap<V, K>, override: Boolean) {
        if (override) {
            hashMap[entity.lowercase()] = value
        } else {
            set(entity, value)
        }
    }

    override operator fun set(entity: String, value: HashMap<V, K>) {
        val ent = hashMap[entity.lowercase()] ?: HashMap()
        ent.putAll(value)
        hashMap[entity.lowercase()] = ent
        toUpdate += entity.lowercase()
    }

    override fun remove(entity: Player, value: V) {
        remove(entity.name, value)
    }

    override fun remove(entity: String, value: V) {
        val ent = hashMap[entity.lowercase()] ?: return
        ent.remove(value)
        hashMap[entity.lowercase()] = ent
        toUpdate += entity.lowercase()
    }

    override fun remove(entity: Player) {
        remove(entity.name.lowercase())
    }

    override fun remove(entity: String) {
        hashMap[entity.lowercase()] = null
        toUpdate += entity.lowercase()
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
                    table.deleteWhere { primaryColumn eq i }
                    continue
                }
                table.update({ primaryColumn eq i }) {
                    it[column] = classConvert.convertToDatabase(value)
                }
            }
        }
    }

    override fun update() {
        save(toUpdate.toList())
    }

    override fun load() {
        for (i in table.selectAll()) {
            hashMap[i[primaryColumn]] = classConvert.convertToCache(i[column]) ?: HashMap()
        }
    }

    override fun unload() {
        save(toUpdate.toList())
    }
}