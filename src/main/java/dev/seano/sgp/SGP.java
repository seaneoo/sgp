package dev.seano.sgp;

import dev.seano.sgp.registry.ModItemGroups;
import dev.seano.sgp.registry.ModItems;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.resources.ResourceLocation;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SGP.MOD_ID)
public class SGP {

	public static final String MOD_ID = "sgp";
	private static final Logger LOGGER = LogManager.getLogger(StringUtils.capitalize(MOD_ID));

	public SGP() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		modEventBus.addListener((FMLCommonSetupEvent event) -> LOGGER.info("common setup"));

		ModItems.init(modEventBus);
		ModItemGroups.init(modEventBus);

		MinecraftForge.EVENT_BUS.register(this);
	}

	public static ResourceLocation resourceLocation(String path) {
		return new ResourceLocation(MOD_ID, path);
	}

	@Mod.EventBusSubscriber(modid = SGP.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientModEvents {

		@SubscribeEvent
		public static void onClientSetup(FMLClientSetupEvent event) {
			LOGGER.info("client setup");
		}
	}
}
