package dev.seano.sgp.client;

import dev.seano.sgp.SGP;
import dev.seano.sgp.client.model.GuineaPigModel;
import dev.seano.sgp.client.render.GuineaPigRenderer;
import dev.seano.sgp.registry.ModEntityTypes;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SGP.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SGPClient {

	public static void init() {
		SGP.LOGGER.info("client setup");
	}

	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ModEntityTypes.GUINEA_PIG.get(), GuineaPigRenderer::new);
	}

	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(GuineaPigModel.LAYER_LOCATION, GuineaPigModel::createBodyLayer);
	}
}
