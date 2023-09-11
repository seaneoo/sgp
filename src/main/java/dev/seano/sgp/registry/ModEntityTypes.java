package dev.seano.sgp.registry;

import dev.seano.sgp.SGP;

import dev.seano.sgp.entity.ExampleEntity;

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

	public static final RegistryObject<EntityType<ExampleEntity>> EXAMPLE = ENTITY_TYPES.register("example",
			() -> EntityType.Builder.of(ExampleEntity::new, MobCategory.CREATURE).sized(1f, 1f)
					.build(SGP.resourceLocation("example").toString()));

	public static void init(IEventBus eventBus) {
		ENTITY_TYPES.register(eventBus);
	}

	@SubscribeEvent
	public static void addEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(EXAMPLE.get(), ExampleEntity.createMobAttributes().build());
	}
}
