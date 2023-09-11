package dev.seano.sgp.data;

import dev.seano.sgp.SGP;

import dev.seano.sgp.registry.ModItems;

import net.minecraft.data.PackOutput;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class SGPItemModelProvider extends ItemModelProvider {

	public SGPItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
		super(output, modid, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		simpleItem(ModItems.EXAMPLE_ITEM);
	}

	@SuppressWarnings("SameParameterValue")
	private void simpleItem(RegistryObject<Item> itemRegistryObject) {
		withExistingParent(itemRegistryObject.getId().getPath(), new ResourceLocation("item/generated")).texture(
				"layer0", new ResourceLocation(SGP.MOD_ID, "item/" + itemRegistryObject.getId().getPath()));
	}
}
