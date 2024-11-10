package dev.seano.sgp.registry

import dev.seano.sgp.SGP
import dev.seano.sgp.entity.GuineaPigEntity
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.SpawnLocationTypes
import net.minecraft.entity.SpawnRestriction
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.world.Heightmap

object SGPEntities {

	const val GUINEA_PIG_ID = "guinea_pig"

	val GUINEA_PIG: EntityType<GuineaPigEntity>? = Registry.register(
		Registries.ENTITY_TYPE,
		SGP.id(GUINEA_PIG_ID),
		EntityType.Builder.create({ type, world -> GuineaPigEntity(type, world) }, SpawnGroup.CREATURE)
			.dimensions(0.375f, 0.25f)
			.eyeHeight(0.125f)
			.build(GUINEA_PIG_ID)
	)

	fun registerAttributes() {
		FabricDefaultAttributeRegistry.register(GUINEA_PIG, GuineaPigEntity.createAttributes())
	}

	fun registerSpawns() {
		SpawnRestriction.register(
			GUINEA_PIG,
			SpawnLocationTypes.ON_GROUND,
			Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
			AnimalEntity::isValidNaturalSpawn
		)
		BiomeModifications.addSpawn(
			BiomeSelectors.tag(SGPTags.GUINEA_PIG_SPAWN_BIOMES), SpawnGroup.CREATURE, GUINEA_PIG, 100, 3, 5
		)
	}
}
