package github.gilbertokpl.total.cache.serializer

import github.gilbertokpl.core.external.cache.convert.SerializerBase
import org.bukkit.Location

internal class HomeSerializer : SerializerBase<HashMap<String, Location>, String> {

    private val locationSerializer = LocationSerializer()

    override fun convertToDatabase(hash: HashMap<String, Location>): String {
        val stringBuilder = StringBuilder()
        for ((key, value) in hash) {
            val toString = "$key,${locationSerializer.convertToDatabase(value)}"
            if (stringBuilder.isNotEmpty()) {
                stringBuilder.append("|")
            }
            stringBuilder.append(toString)
        }
        return stringBuilder.toString()
    }

    override fun convertToCache(value: String): HashMap<String, Location> {
        val hash = HashMap<String, Location>()
        for (i in value.split("|")) {
            val split = i.split(",", limit = 2)
            if (split.size != 2) continue
            val key = split[0]
            val location = locationSerializer.convertToCache(split[1])
            if (location != null) {
                hash[key] = location
            }
        }
        return hash
    }

}