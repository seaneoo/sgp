package dev.seano.sgp.data;

import dev.seano.sgp.registry.ModItemGroups;
import dev.seano.sgp.registry.ModItems;

import net.minecraft.data.PackOutput;

import net.minecraftforge.common.data.LanguageProvider;

public class EnglishLanguageProvider extends LanguageProvider {

	public EnglishLanguageProvider(PackOutput output, String modid, String locale) {
		super(output, modid, locale);
	}

	@Override
	protected void addTranslations() {
		this.add(ModItems.EXAMPLE_ITEM.get(), "Example Item");
		this.add(ModItemGroups.SGP_TAB.get().getDisplayName().getString(), "Sean's Guinea Pigs");
	}
}
