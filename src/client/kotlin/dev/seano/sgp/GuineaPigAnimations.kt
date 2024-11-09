package dev.seano.sgp

import net.minecraft.client.render.entity.animation.Animation
import net.minecraft.client.render.entity.animation.AnimationHelper
import net.minecraft.client.render.entity.animation.Keyframe
import net.minecraft.client.render.entity.animation.Transformation
import net.minecraft.client.render.entity.animation.Transformation.Interpolations
import net.minecraft.client.render.entity.animation.Transformation.Targets

object GuineaPigAnimations {

	val IDLING: Animation = Animation.Builder.create(1.0f).looping().addBoneAnimation(
		"body", Transformation(
			Targets.SCALE,
			Keyframe(0.0f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Interpolations.LINEAR),
			Keyframe(0.5f, AnimationHelper.createScalingVector(1.01, 1.01, 1.01), Interpolations.CUBIC),
			Keyframe(1.0f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Interpolations.LINEAR)
		)
	).build()

	val WALKING: Animation = Animation.Builder.create(0.5f).looping().addBoneAnimation(
		"body", Transformation(
			Targets.SCALE,
			Keyframe(0f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Interpolations.LINEAR),
			Keyframe(0.25f, AnimationHelper.createScalingVector(1.01, 1.01, 1.01), Interpolations.CUBIC),
			Keyframe(0.5f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Interpolations.LINEAR)
		)
	).addBoneAnimation(
		"legFL", Transformation(
			Targets.ROTATE,
			Keyframe(0f, AnimationHelper.createRotationalVector(-22.5f, 0f, 0f), Interpolations.CUBIC),
			Keyframe(0.25f, AnimationHelper.createRotationalVector(22.5f, 0f, 0f), Interpolations.CUBIC),
			Keyframe(0.5f, AnimationHelper.createRotationalVector(-22.5f, 0f, 0f), Interpolations.CUBIC)
		)
	).addBoneAnimation(
		"legFR", Transformation(
			Targets.ROTATE,
			Keyframe(0f, AnimationHelper.createRotationalVector(22.5f, 0f, 0f), Interpolations.CUBIC),
			Keyframe(0.25f, AnimationHelper.createRotationalVector(-22.5f, 0f, 0f), Interpolations.CUBIC),
			Keyframe(0.5f, AnimationHelper.createRotationalVector(22.5f, 0f, 0f), Interpolations.CUBIC)
		)
	).addBoneAnimation(
		"legBL", Transformation(
			Targets.ROTATE,
			Keyframe(0f, AnimationHelper.createRotationalVector(22.5f, 0f, 0f), Interpolations.CUBIC),
			Keyframe(0.25f, AnimationHelper.createRotationalVector(-22.5f, 0f, 0f), Interpolations.CUBIC),
			Keyframe(0.5f, AnimationHelper.createRotationalVector(22.5f, 0f, 0f), Interpolations.CUBIC)
		)
	).addBoneAnimation(
		"legBR", Transformation(
			Targets.ROTATE,
			Keyframe(0f, AnimationHelper.createRotationalVector(-22.5f, 0f, 0f), Interpolations.CUBIC),
			Keyframe(0.25f, AnimationHelper.createRotationalVector(22.5f, 0f, 0f), Interpolations.CUBIC),
			Keyframe(0.5f, AnimationHelper.createRotationalVector(-22.5f, 0f, 0f), Interpolations.CUBIC)
		)
	).build()

	val SITTING: Animation = Animation.Builder.create(1f).addBoneAnimation(
		"body", Transformation(
			Targets.TRANSLATE,
			Keyframe(0.0f, AnimationHelper.createTranslationalVector(0.0f, -0.5f, 0.0f), Interpolations.LINEAR)
		)
	).addBoneAnimation(
		"legFL", Transformation(
			Targets.TRANSLATE,
			Keyframe(0.0f, AnimationHelper.createTranslationalVector(1.0f, 0.0f, 0.0f), Interpolations.LINEAR)
		)
	).addBoneAnimation(
		"legFR", Transformation(
			Targets.TRANSLATE,
			Keyframe(0.0f, AnimationHelper.createTranslationalVector(-1.0f, 0.0f, 0.0f), Interpolations.LINEAR)
		)
	).addBoneAnimation(
		"legBL", Transformation(
			Targets.TRANSLATE,
			Keyframe(0.0f, AnimationHelper.createTranslationalVector(1.0f, 0.0f, 0.0f), Interpolations.LINEAR)
		)
	).addBoneAnimation(
		"legBR", Transformation(
			Targets.TRANSLATE,
			Keyframe(0.0f, AnimationHelper.createTranslationalVector(-1.0f, 0.0f, 0.0f), Interpolations.LINEAR)
		)
	).build()
}
