package dev.seano.sgp.client;

import dev.seano.sgp.SGP;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SGP.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class Client {

	public static void init() {
		SGP.LOGGER.info("client setup");
	}
}
