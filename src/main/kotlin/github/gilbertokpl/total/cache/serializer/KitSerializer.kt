package github.gilbertokpl.total.cache.serializer

import github.gilbertokpl.core.external.cache.convert.SerializerBase
import github.gilbertokpl.total.cache.local.KitsData


internal class KitSerializer : SerializerBase<HashMap<String, Long>, String> {
    override fun convertToDatabase(hash: HashMap<String, Long>): String {
        val validEntries = hash.entries.filter { (key, value) ->
            KitsData.checkIfExist(key) && (value != 0L) && ((KitsData.kitTime[key]
                ?: (0L + value)) > System.currentTimeMillis())
        }.map { (key, value) ->
            "$key,$value"
        }
        return validEntries.joinToString("|")
    }

    override fun convertToCache(value: String): HashMap<String, Long> {
        val hash = HashMap<String, Long>()
        for (entryString in value.split("|")) {
            val split = entryString.split(",")
            if (split.size < 2) continue
            val nameKit = split[0].lowercase()
            val timeKit = split[1].toLong()
            hash[nameKit] = timeKit
        }
        return hash
    }
}