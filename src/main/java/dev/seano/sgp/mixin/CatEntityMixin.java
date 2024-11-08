package dev.seano.sgp.mixin;

import dev.seano.sgp.entity.GuineaPigEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.UntamedActiveTargetGoal;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CatEntity.class)
public abstract class CatEntityMixin extends TameableEntity {

	protected CatEntityMixin(EntityType<? extends TameableEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "initGoals", at = @At(value = "TAIL"))
	private void initGoals(CallbackInfo ci) {
		this.targetSelector.add(1, new UntamedActiveTargetGoal<>(this, GuineaPigEntity.class, false, null));
	}
}
