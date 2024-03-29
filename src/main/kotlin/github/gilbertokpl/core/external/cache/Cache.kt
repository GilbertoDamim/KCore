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

class Cache(core: CorePlugin) {
    private val corePlugin = core

    fun stop() {
        transaction(corePlugin.sql) {
            for (i in toByteUpdate) {
                i.unload()
            }
        }
    }

    fun simpleBoolean(): CacheBuilder<Boolean> {
        return SimpleCacheBuilder()
    }

    fun simpleInteger(): CacheBuilder<Int> {
        return SimpleCacheBuilder()
    }

    fun simpleLong(): CacheBuilder<Long> {
        return SimpleCacheBuilder()
    }

    fun simplePlayer(): CacheBuilder<Player?> {
        return SimpleCacheBuilder()
    }

    fun <T> simpleList(): CacheBuilder<List<T>> {
        return SimpleCacheBuilder()
    }

    fun string(cacheBase: CacheBase, column: Column<String>): CacheBuilder<String> {
        val instance = ByteCacheBuilder(cacheBase.table, cacheBase.primaryColumn, column)
        toByteUpdate.add(instance)
        return instance
    }

    fun boolean(cacheBase: CacheBase, column: Column<Boolean>): CacheBuilder<Boolean> {
        val instance = ByteCacheBuilder(cacheBase.table, cacheBase.primaryColumn, column)
        toByteUpdate.add(instance)
        return instance
    }

    fun integer(cacheBase: CacheBase, column: Column<Int>): CacheBuilder<Int> {
        val instance = ByteCacheBuilder(cacheBase.table, cacheBase.primaryColumn, column)
        toByteUpdate.add(instance)
        return instance
    }

    fun double(cacheBase: CacheBase, column: Column<Double>): CacheBuilder<Double> {
        val instance = ByteCacheBuilder(cacheBase.table, cacheBase.primaryColumn, column)
        toByteUpdate.add(instance)
        return instance
    }

    fun long(cacheBase: CacheBase, column: Column<Long>): CacheBuilder<Long> {
        val instance = ByteCacheBuilder(cacheBase.table, cacheBase.primaryColumn, column)
        toByteUpdate.add(instance)
        return instance
    }

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
            for (i in toByteUpdate) {
                i.load()
            }
        }
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay({
            save()
        }, 5, 5, TimeUnit.MINUTES)
    }

    val toByteUpdate = ArrayList<CacheBuilder<*>>()

    fun save() {
        try {
            transaction(corePlugin.sql) {
                for (i in toByteUpdate) {
                    try {
                        i.update()
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