package github.gilbertokpl.total.cache.serializer

import github.gilbertokpl.core.external.cache.convert.SerializerBase
import github.gilbertokpl.core.external.utils.Inventory
import org.bukkit.Location
import org.bukkit.inventory.ItemStack

class LimiterLocationSerializer : SerializerBase<HashMap<ItemStack, ArrayList<Location>>, String> {

    private val locationSerializer = LocationSerializer()
    private val itemSerializer = Inventory()

    override fun convertToDatabase(hash: HashMap<ItemStack, ArrayList<Location>>): String {
        val serializedLocations = hash.entries.joinToString("|") { entry ->
            val locationList = entry.value.joinToString("-") { location ->
                locationSerializer.convertToDatabase(location)
            }
            "${itemSerializer.serialize(entry.key)},$locationList"
        }
        return serializedLocations
    }

    override fun convertToCache(value: String): HashMap<ItemStack, ArrayList<Location>> {
        val hash = HashMap<ItemStack, ArrayList<Location>>()
        for (entryString in value.split("|")) {
            val split = entryString.split(",")
            if (split.size < 2) continue

            val locationList = ArrayList<Location>()
            for (locationString in split[1].split("-")) {
                locationSerializer.convertToCache(locationString)?.let {
                    locationList.add(it)
                }
            }

            hash[itemSerializer.deserialize(split[0])[0]] = locationList
        }
        return hash
    }
}