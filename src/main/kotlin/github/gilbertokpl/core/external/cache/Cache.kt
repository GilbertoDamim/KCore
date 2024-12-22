package github.gilbertokpl.core.external.cache

import github.gilbertokpl.core.external.CorePlugin
import github.gilbertokpl.core.external.cache.convert.SerializerBase
import github.gilbertokpl.core.external.cache.interfaces.CacheBase
import github.gilbertokpl.core.external.cache.interfaces.CacheBuilder
import github.gilbertokpl.core.external.cache.interfaces.CacheBuilderV2
import github.gilbertokpl.core.internal.cache.*
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class Cache(private val corePlugin: CorePlugin) {

    val toByteUpdate = ArrayList<CacheBuilder<*>>()

    fun stop() {
        transaction(corePlugin.sql) {
            toByteUpdate.forEach(CacheBuilder<*>::unload)
        }
    }

    fun simpleBoolean(): CacheBuilder<Boolean> = SimpleCacheBuilder()

    fun simpleInteger(): CacheBuilder<Int> = SimpleCacheBuilder()

    fun simpleLong(): CacheBuilder<Long> = SimpleCacheBuilder()

    fun simplePlayer(): CacheBuilder<Player?> = SimpleCacheBuilder()

    fun <T> simpleList(): CacheBuilder<List<T>> = SimpleCacheBuilder()

    private fun <T> createCacheBuilder(
        cacheBase: CacheBase,
        column: Column<T>
    ): ByteCacheBuilder<T> {
        val instance = ByteCacheBuilder(cacheBase.table, cacheBase.primaryColumn, column)
        toByteUpdate.add(instance)
        return instance
    }

    fun string(cacheBase: CacheBase, column: Column<String>): CacheBuilder<String> =
        createCacheBuilder(cacheBase, column)

    fun boolean(cacheBase: CacheBase, column: Column<Boolean>): CacheBuilder<Boolean> =
        createCacheBuilder(cacheBase, column)

    fun integer(cacheBase: CacheBase, column: Column<Int>): CacheBuilder<Int> =
        createCacheBuilder(cacheBase, column)

    fun double(cacheBase: CacheBase, column: Column<Double>): CacheBuilder<Double> =
        createCacheBuilder(cacheBase, column)

    fun long(cacheBase: CacheBase, column: Column<Long>): CacheBuilder<Long> =
        createCacheBuilder(cacheBase, column)

    fun location(
        cacheBase: CacheBase,
        column: Column<String>,
        base: SerializerBase<Location?, String>
    ): CacheBuilder<Location?> {
        val instance = LocationCacheBuilder(cacheBase.table, cacheBase.primaryColumn, column, base)
        toByteUpdate.add(instance)
        return instance
    }

    fun <V> list(
        cacheBase: CacheBase,
        column: Column<String>,
        base: SerializerBase<ArrayList<V>, String>
    ): CacheBuilderV2<ArrayList<V>, V> {
        val instance = ListCacheBuilder(cacheBase.table, cacheBase.primaryColumn, column, base)
        toByteUpdate.add(instance)
        return instance
    }

    fun <V, K> hashMap(
        cacheBase: CacheBase,
        column: Column<String>,
        base: SerializerBase<HashMap<V, K>, String>
    ): CacheBuilderV2<HashMap<V, K>, V> {
        val instance = HashMapCacheBuilder(cacheBase.table, cacheBase.primaryColumn, column, base)
        toByteUpdate.add(instance)
        return instance
    }

    fun <V, K> integerHashMap(
        cacheBase: CacheBase,
        column: Column<Int>,
        base: SerializerBase<HashMap<V, K>, Int>
    ): CacheBuilderV2<HashMap<V, K>, V> {
        val instance = HashMapCacheBuilder(cacheBase.table, cacheBase.primaryColumn, column, base)
        toByteUpdate.add(instance)
        return instance
    }

    fun start(cachePackage: String) {
        corePlugin.getReflection().getClasses(cachePackage)
        transaction(corePlugin.sql) {
            toByteUpdate.forEach(CacheBuilder<*>::load)

        }
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay({
            save()
        }, 5, 5, TimeUnit.MINUTES)
    }

    fun save() {
        try {
            transaction(corePlugin.sql) {
                toByteUpdate.forEach {
                    try {
                        it.update()
                    } catch (e: Exception) {
                        Bukkit.getServer().shutdown()
                        e.printStackTrace()
                    }
                }
            }
        } catch (e: Exception) {
            Bukkit.getServer().shutdown()
            e.printStackTrace()
        }
    }
}
