package dev.seano.sgp.registry

import dev.seano.sgp.SGP
import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.world.biome.Biome

object SGPTags {

	val GUINEA_PIG_FOOD: TagKey<Item> = TagKey.of(RegistryKeys.ITEM, SGP.id("guinea_pig_food"))
	val GUINEA_PIG_SPAWN_BIOMES: TagKey<Biome> = TagKey.of(RegistryKeys.BIOME, SGP.id("spawns_guinea_pigs"))

	fun registerTags() {}
}
