package github.gilbertokpl.total.cache.serializer

import github.gilbertokpl.core.external.cache.convert.SerializerBase

internal class CommandsSerializer : SerializerBase<ArrayList<String>, String> {
    override fun convertToDatabase(commands: ArrayList<String>): String {
        return commands.joinToString("|")
    }

    override fun convertToCache(value: String): ArrayList<String> {
        return ArrayList(value.split("|"))
    }
}