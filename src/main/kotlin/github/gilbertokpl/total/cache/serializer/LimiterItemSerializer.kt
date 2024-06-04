package github.gilbertokpl.total.cache.serializer

import github.gilbertokpl.core.external.cache.convert.SerializerBase
import org.bukkit.Location

class LimiterItemSerializer : SerializerBase<HashMap<Int, Int>, String> {
    override fun convertToDatabase(hash: HashMap<Int, Int>): String {
        return hash.entries.joinToString("|") { (key, value) ->
            "$key,$value"
        }
    }

    override fun convertToCache(value: String): HashMap<Int, Int> {
        val hash = HashMap<Int, Int>()
        for (entryString in value.split("|")) {
            val split = entryString.split(",")
            if (split.size < 2) continue
            hash[split[0].toInt()] = split[1].toInt()
        }
        return hash
    }
}
