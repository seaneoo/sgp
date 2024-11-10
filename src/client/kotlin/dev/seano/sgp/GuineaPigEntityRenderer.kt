package dev.seano.sgp

import dev.seano.sgp.entity.GuineaPigEntity
import dev.seano.sgp.registry.SGPEntities
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.MobEntityRenderer
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Identifier

@Environment(EnvType.CLIENT)
class GuineaPigEntityRenderer(context: EntityRendererFactory.Context) :
	MobEntityRenderer<GuineaPigEntity, GuineaPigEntityModel>(
		context, GuineaPigEntityModel(context.getPart(guineaPigModelLayer)), 0.2f
	) {

	companion object {

		val guineaPigModelLayer: EntityModelLayer = EntityModelLayer(SGP.id(SGPEntities.GUINEA_PIG_ID), "main")
	}

	override fun getTexture(entity: GuineaPigEntity?): Identifier {
		return SGP.id("textures/entity/guinea_pig.png")
	}

	override fun render(
		livingEntity: GuineaPigEntity,
		f: Float,
		g: Float,
		matrixStack: MatrixStack,
		vertexConsumerProvider: VertexConsumerProvider,
		i: Int
	) {
		if (livingEntity.isBaby) {
			matrixStack.scale(0.75f, 0.75f, 0.75f)
		}
		super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i)
	}
}
