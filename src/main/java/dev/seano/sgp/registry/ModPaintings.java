package dev.seano.sgp.registry;

import dev.seano.sgp.SGP;

import net.minecraft.world.entity.decoration.PaintingVariant;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintings {

	public static final DeferredRegister<PaintingVariant> PAINTINGS =
			DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, SGP.MOD_ID);

	public static final RegistryObject<PaintingVariant> HENRIETTA_WARD = PAINTINGS.register(
			"henrietta_ward", () -> new PaintingVariant(64, 48));

	public static void init(IEventBus eventBus) {
		PAINTINGS.register(eventBus);
	}
}
