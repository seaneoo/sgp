package dev.seano.sgp

import dev.seano.sgp.entity.GuineaPigEntity
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.model.*
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.entity.model.SinglePartEntityModel
import net.minecraft.client.util.math.MatrixStack

@Environment(EnvType.CLIENT)
class GuineaPigEntityModel(root: ModelPart) : SinglePartEntityModel<GuineaPigEntity>() {

	private var root: ModelPart = root.getChild("root")

	@Suppress("UNUSED_VARIABLE")
	companion object {

		val texturedModelData: TexturedModelData
			get() {
				val modelData = ModelData()
				val modelPartData = modelData.root
				val root =
					modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0f, 23.5f, 0.0f))
				val body = root.addChild(
					"body",
					ModelPartBuilder.create()
						.uv(0, 0)
						.cuboid(-1.5f, -1.5f, -2.6f, 3.0f, 3.0f, 7.0f, Dilation(0.0f))
						.uv(0, 10)
						.cuboid(-1.5f, -0.5f, -3.6f, 3.0f, 2.0f, 1.0f, Dilation(0.0f))
						.uv(0, 0)
						.cuboid(-0.5f, 0.0f, 3.9f, 1.0f, 1.0f, 1.0f, Dilation(0.0f))
						.uv(2, 2)
						.cuboid(1.0f, -1.0f, -1.1f, 1.0f, 1.0f, 0.0f, Dilation(0.0f))
						.uv(0, 2)
						.cuboid(-2.0f, -1.0f, -1.1f, 1.0f, 1.0f, 0.0f, Dilation(0.0f)),
					ModelTransform.pivot(0.0f, -1.5f, -0.4f)
				)
				val legFL = root.addChild(
					"legFL", ModelPartBuilder.create().uv(0, 3).cuboid(
						-0.5f, 0.0f, -0.5f, 1.0f, 1.0f, 1.0f, Dilation(0.0f)
					), ModelTransform.pivot(0.5f, -0.5f, -2.0f)
				)
				val legFR = root.addChild(
					"legFR", ModelPartBuilder.create().uv(0, 3).cuboid(
						-0.5f, 0.0f, -0.5f, 1.0f, 1.0f, 1.0f, Dilation(0.0f)
					), ModelTransform.pivot(-0.5f, -0.5f, -2.0f)
				)
				val legBL = root.addChild(
					"legBL", ModelPartBuilder.create().uv(0, 3).cuboid(
						-0.5f, 0.0f, -0.5f, 1.0f, 1.0f, 1.0f, Dilation(0.0f)
					), ModelTransform.pivot(0.5f, -0.5f, 3.0f)
				)
				val legBR = root.addChild(
					"legBR", ModelPartBuilder.create().uv(0, 3).cuboid(
						-0.5f, 0.0f, -0.5f, 1.0f, 1.0f, 1.0f, Dilation(0.0f)
					), ModelTransform.pivot(-0.5f, -0.5f, 3.0f)
				)
				return TexturedModelData.of(modelData, 32, 32)
			}
	}

	override fun getPart(): ModelPart {
		return root
	}

	override fun render(matrices: MatrixStack?, vertices: VertexConsumer?, light: Int, overlay: Int, color: Int) {
		root.render(matrices, vertices, light, overlay, color)
	}

	override fun setAngles(
		entity: GuineaPigEntity,
		limbAngle: Float,
		limbDistance: Float,
		animationProgress: Float,
		headYaw: Float,
		headPitch: Float
	) {
		part.traverse().forEach { obj: ModelPart -> obj.resetTransform() }
		this.animateMovement(GuineaPigAnimations.WALKING, limbAngle, limbDistance, 2.0f, 2.5f)
		this.updateAnimation(entity.idlingAnimationState, GuineaPigAnimations.IDLING, animationProgress, 1f)
		this.updateAnimation(entity.sittingAnimationState, GuineaPigAnimations.SITTING, animationProgress, 1f)
	}
}
