package dev.seano.sgp.registry

import dev.seano.sgp.SGP
import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey

object SGPTags {

    val GUINEA_PIG_FOOD: TagKey<Item> = TagKey.of(RegistryKeys.ITEM, SGP.id("guinea_pig_food"))
}
