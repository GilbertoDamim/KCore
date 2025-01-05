package github.gilbertokpl.core.internal.cache

import github.gilbertokpl.core.external.cache.interfaces.CacheBuilder
import github.gilbertokpl.total.TotalEssentialsJava
import github.gilbertokpl.total.cache.local.PlayerData
import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.concurrent.locks.ReentrantLock

internal class ByteCacheBuilder<T>(
    private val table: Table,
    private val primaryColumn: Column<String>,
    private val column: Column<T>
) : CacheBuilder<T> {

    private val hashMap = mutableMapOf<String, T?>()
    private val toUpdate = mutableSetOf<String>()
    private val lock = ReentrantLock()

    override fun getMap(): Map<String, T?> {
        return hashMap.toMap()
    }

    override operator fun get(entity: String): T? {
        return hashMap[entity.lowercase()]
    }

    override operator fun get(entity: Player): T? {
        return hashMap[entity.name.lowercase()]
    }

    override fun set(entity: String, value: T) {
        lock.lock()
        try {
            hashMap[entity.lowercase()] = value
            toUpdate.add(entity.lowercase())
        } finally {
            lock.unlock()
        }
    }

    override fun set(entity: String, value: T, override: Boolean) {
        set(entity, value)
    }

    override operator fun set(entity: Player, value: T) {
        set(entity.name, value)
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

    private fun save(list: List<String>) {
        lock.lock()
        try {
            if (toUpdate.isEmpty()) return

            val existingRows = table.selectAll().where { primaryColumn inList list }
                .associateBy { it[primaryColumn] }

            for (i in list) {
                val value = hashMap[i]

                if (value == null) {
                    existingRows[i]?.let {
                        TotalEssentialsJava.basePlugin.logger.log("Removendo Entidade chamada: $i, coluna: $column")
                        table.deleteWhere { primaryColumn eq i }
                    }
                } else {
                    if (i !in existingRows) {
                        table.insert {
                            TotalEssentialsJava.basePlugin.logger.log("Setando valor da entidade: $i, coluna: $column, valor: $value")
                            it[primaryColumn] = i
                            it[column] = value
                        }
                    } else {
                        table.update({ primaryColumn eq i }) {
                            TotalEssentialsJava.basePlugin.logger.log("Setando valor da entidade: $i, coluna: $column, valor: $value")
                            it[column] = value
                        }
                    }
                }
            }

            toUpdate.removeAll(list.toSet())
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
            table.selectAll().forEach {
                hashMap[it[primaryColumn].lowercase()] = it[column]
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
            if (i[column] != value) {
                val name = i[primaryColumn]
                TotalEssentialsJava.basePlugin.logger.log("Novo Erro encontrado da entidade: $name, coluna: $column, setando novo valor: $value")
                set(name, value)
            }
        }

        update()
    }
}
