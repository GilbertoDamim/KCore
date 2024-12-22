package github.gilbertokpl.total.cache.serializer

import github.gilbertokpl.core.external.cache.convert.SerializerBase
import github.gilbertokpl.total.cache.local.KitsData

internal class KitSerializer : SerializerBase<HashMap<String, Long>, String> {
    override fun convertToDatabase(hash: HashMap<String, Long>): String {
        return hash.entries
            .filter { (key, value) ->
                KitsData.checkIfExist(key) &&
                        KitsData.kitTime[key]?.let { timeAll -> value != 0L && (timeAll + value) > System.currentTimeMillis() } == true
            }
            .joinToString("|") { "${it.key},${it.value}" }
    }

    override fun convertToCache(value: String): HashMap<String, Long> {
        val hash = HashMap<String, Long>()
        if (value.isBlank()) return hash

        for (entry in value.split("|")) {
            val split = entry.split(",", limit = 2)
            if (split.size != 2) continue

            val nameKit = split[0].lowercase()
            val timeKit = split[1].toLongOrNull() ?: continue

            hash[nameKit] = timeKit
        }
        return hash
    }
}