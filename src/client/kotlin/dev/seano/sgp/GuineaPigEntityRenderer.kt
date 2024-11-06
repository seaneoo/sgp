package dev.seano.sgp

import dev.seano.sgp.entity.GuineaPigEntity
import dev.seano.sgp.registry.SGPEntities
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.MobEntityRenderer
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.util.Identifier

@Environment(EnvType.CLIENT)
class GuineaPigEntityRenderer(context: EntityRendererFactory.Context) :
	MobEntityRenderer<GuineaPigEntity, GuineaPigEntityModel>(
		context, GuineaPigEntityModel(context.getPart(guineaPigModelLayer)), 0.3f
	) {

	companion object {

		val guineaPigModelLayer: EntityModelLayer = EntityModelLayer(SGP.id(SGPEntities.GUINEA_PIG_ID), "main")
	}

	override fun getTexture(entity: GuineaPigEntity?): Identifier {
		return SGP.id("textures/entity/guinea_pig.png")
	}
}
