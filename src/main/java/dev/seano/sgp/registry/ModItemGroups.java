package dev.seano.sgp.registry;

import dev.seano.sgp.SGP;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModItemGroups {
	public static final DeferredRegister<CreativeModeTab> ITEM_GROUPS = DeferredRegister.create(
			Registries.CREATIVE_MODE_TAB, SGP.MOD_ID);

	public static final RegistryObject<CreativeModeTab> SGP_TAB = ITEM_GROUPS.register(SGP.MOD_ID,
			() -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.EXAMPLE_ITEM.get()))
					.title(Component.translatable("itemGroup." + SGP.MOD_ID)).displayItems(
							(p_270258_, p_259752_) -> ModItems.ITEMS.getEntries()
									.forEach(itemRegistryObject -> p_259752_.accept(itemRegistryObject.get())))
					.build());

	public static void init(IEventBus eventBus) {
		ITEM_GROUPS.register(eventBus);
	}
}
