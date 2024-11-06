package dev.seano.sgp

import dev.seano.sgp.GuineaPigEntityRenderer.Companion.guineaPigModelLayer
import dev.seano.sgp.registry.SGPEntities
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry

@Environment(EnvType.CLIENT)
object SGPClient : ClientModInitializer {

	override fun onInitializeClient() {
		SGP.logger.info("onInitializeClient")

		EntityRendererRegistry.register(SGPEntities.GUINEA_PIG) { context -> GuineaPigEntityRenderer(context) }
		EntityModelLayerRegistry.registerModelLayer(guineaPigModelLayer, GuineaPigEntityModel::texturedModelData)
	}
}
