package dev.seano.sgp.client;

import dev.seano.sgp.SGP;
import dev.seano.sgp.client.model.ExampleModel;
import dev.seano.sgp.client.render.ExampleRenderer;
import dev.seano.sgp.registry.ModEntityTypes;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SGP.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class Client {

	public static void init() {
		SGP.LOGGER.info("client setup");
	}

	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ModEntityTypes.EXAMPLE.get(), ExampleRenderer::new);
	}

	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ExampleModel.LAYER_LOCATION, ExampleModel::createBodyLayer);
	}
}
