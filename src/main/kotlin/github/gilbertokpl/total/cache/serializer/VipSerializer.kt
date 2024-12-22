package github.gilbertokpl.total.cache.serializer

import github.gilbertokpl.core.external.cache.convert.SerializerBase

class VipSerializer : SerializerBase<HashMap<String, Long>, String> {
    override fun convertToDatabase(hash: HashMap<String, Long>): String {
        var string = ""
        for (i in hash) {
            val toString = "${i.key},${i.value}"
            string += if (string == "") {
                toString
            } else {
                "|$toString"
            }
        }
        return string
    }

    override fun convertToCache(value: String): HashMap<String, Long> {
        val hash = HashMap<String, Long>()
        for (i in value.split("|")) {
            val split = i.split(",")
            if (split.size < 2) continue
            hash[split[0]] = split[1].toLong()
        }
        return hash
    }
}