package dev.seano.sgp.registry

import dev.seano.sgp.SGP
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntries
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.item.SpawnEggItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry

object SGPItems {

	@Suppress("MemberVisibilityCanBePrivate")
	val GUINEA_PIG_SPAWN_EGG: SpawnEggItem = Registry.register(
		Registries.ITEM,
		SGP.id("guinea_pig_spawn_egg"),
		SpawnEggItem(SGPEntities.GUINEA_PIG, 0xd7bc98, 0xf7f7f7, Item.Settings())
	)

	fun populateItemGroups() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS)
			.register(ModifyEntries { itemGroup: FabricItemGroupEntries -> itemGroup.add(GUINEA_PIG_SPAWN_EGG) })
	}
}
