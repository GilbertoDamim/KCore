package github.gilbertokpl.total.cache.serializer

import github.gilbertokpl.core.external.cache.convert.SerializerBase
import org.bukkit.Location

internal class HomeSerializer : SerializerBase<HashMap<String, Location>, String> {

    private val locationSerializer = LocationSerializer()

    override fun convertToDatabase(hash: HashMap<String, Location>): String {
        return hash.entries.joinToString("|") { (key, value) ->
            "$key,${locationSerializer.convertToDatabase(value)}"
        }
    }

    override fun convertToCache(value: String): HashMap<String, Location> {
        val hash = HashMap<String, Location>()
        for (entryString in value.split("|")) {
            val split = entryString.split(",")
            if (split.size < 2) continue
            hash[split[0]] = locationSerializer.convertToCache(split[1]) ?: continue
        }
        return hash
    }
}