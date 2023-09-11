package dev.seano.sgp.data;

import dev.seano.sgp.SGP;

import net.minecraft.data.DataGenerator;

import net.minecraft.data.PackOutput;

import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SGP.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SGPDataGenerator {

	@SubscribeEvent
	public static void dataGenerator(GatherDataEvent event) {
		DataGenerator dataGenerator = event.getGenerator();
		PackOutput packOutput = dataGenerator.getPackOutput();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		dataGenerator.addProvider(true, new SGPItemModelProvider(packOutput, SGP.MOD_ID, existingFileHelper));
		dataGenerator.addProvider(true, new EnglishLanguageProvider(packOutput, SGP.MOD_ID, "en_us"));
	}
}
