package dev.seano.sgp.registry;

import dev.seano.sgp.SGP;

import dev.seano.sgp.entity.GuineaPig;

import net.minecraft.world.entity.EntityType;

import net.minecraft.world.entity.MobCategory;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = SGP.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntityTypes {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(
			ForgeRegistries.ENTITY_TYPES, SGP.MOD_ID);

	public static final RegistryObject<EntityType<GuineaPig>> GUINEA_PIG = ENTITY_TYPES.register(GuineaPig.ID,
			() -> EntityType.Builder.of(GuineaPig::new, MobCategory.CREATURE).sized(0.625f, 0.25f)
					.build(SGP.resourceLocation(GuineaPig.ID).toString()));

	public static void init(IEventBus eventBus) {
		ENTITY_TYPES.register(eventBus);
	}

	@SubscribeEvent
	public static void addEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(GUINEA_PIG.get(), GuineaPig.createGuineaPigAttributes().build());
	}
}
