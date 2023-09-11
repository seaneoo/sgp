package dev.seano.sgp.entity;

import dev.seano.sgp.registry.ModEntityTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;

public class ExampleEntity extends Animal {

	public ExampleEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}

	@Nullable
	@Override
	public AgeableMob getBreedOffspring(@NotNull ServerLevel pLevel, @NotNull AgeableMob pOtherParent) {
		return ModEntityTypes.EXAMPLE.get().create(pLevel);
	}
}
