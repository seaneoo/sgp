package dev.seano.sgp.mixin;

import dev.seano.sgp.entity.GuineaPigEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OcelotEntity.class)
public abstract class OcelotEntityMixin extends AnimalEntity {

	protected OcelotEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "initGoals", at = @At(value = "TAIL"))
	private void initGoals(CallbackInfo ci) {
		this.targetSelector.add(1, new ActiveTargetGoal<>(this, GuineaPigEntity.class, false));
	}
}
