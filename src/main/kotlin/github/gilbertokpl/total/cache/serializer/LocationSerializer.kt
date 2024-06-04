package github.gilbertokpl.total.cache.serializer

import github.gilbertokpl.core.external.cache.convert.SerializerBase
import org.bukkit.Location

class LocationSerializer : SerializerBase<Location?, String> {
    override fun convertToDatabase(hash: Location?): String {
        return hash?.let {
            "${it.x};${it.y};${it.z};${it.world?.name ?: "world"};${it.pitch};${it.yaw}"
        } ?: ""
    }

    override fun convertToCache(value: String): Location? {
        if (value.isEmpty()) return null

        return try {
            val parts = value.split(";")
            val x = parts.getOrElse(0) { "0.0" }.toDouble()
            val y = parts.getOrElse(1) { "0.0" }.toDouble()
            val z = parts.getOrElse(2) { "0.0" }.toDouble()
            val worldName = parts.getOrElse(3) { "world" }
            val w = try {
                github.gilbertokpl.total.TotalEssentialsJava.instance.server.getWorld(worldName)
            } catch (e: Throwable) {
                e.printStackTrace()
                github.gilbertokpl.total.TotalEssentialsJava.instance.server.getWorld("world")
            }
            val pitch = parts.getOrElse(4) { "0.0" }.toFloat()
            val yaw = parts.getOrElse(5) { "0.0" }.toFloat()
            Location(w, x, y, z, pitch, yaw)
        } catch (e: Throwable) {
            e.printStackTrace()
            null
        }
    }
}