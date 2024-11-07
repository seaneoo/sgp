package dev.seano.sgp

import net.minecraft.client.render.entity.animation.Animation
import net.minecraft.client.render.entity.animation.AnimationHelper
import net.minecraft.client.render.entity.animation.Keyframe
import net.minecraft.client.render.entity.animation.Transformation
import net.minecraft.client.render.entity.animation.Transformation.Interpolations
import net.minecraft.client.render.entity.animation.Transformation.Targets

object GuineaPigAnimations {

	val WALKING: Animation = Animation.Builder.create(0.5f).looping().addBoneAnimation(
		"root", Transformation(
			Targets.SCALE,
			Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Interpolations.CUBIC),
			Keyframe(0.25f, AnimationHelper.createRotationalVector(1.01f, 1.01f, 1.01f), Interpolations.CUBIC),
			Keyframe(0.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Interpolations.CUBIC)
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
}
