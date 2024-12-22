package github.gilbertokpl.total.cache.serializer

import github.gilbertokpl.core.external.cache.convert.SerializerBase

internal class CommandsSerializer : SerializerBase<ArrayList<String>, String> {
    override fun convertToDatabase(hash: ArrayList<String>): String {
        return hash.joinToString("|")
    }

    override fun convertToCache(value: String): ArrayList<String> {
        if (value.isBlank()) {
            return ArrayList()
        }
        return ArrayList(value.split("|"))
    }
}