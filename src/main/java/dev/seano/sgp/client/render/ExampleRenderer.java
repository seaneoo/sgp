package dev.seano.sgp.client.render;

import dev.seano.sgp.SGP;
import dev.seano.sgp.client.model.ExampleModel;

import dev.seano.sgp.entity.ExampleEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.minecraft.resources.ResourceLocation;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ExampleRenderer extends MobRenderer<ExampleEntity, ExampleModel<ExampleEntity>> {

	public ExampleRenderer(EntityRendererProvider.Context pContext) {
		super(pContext, new ExampleModel<>(pContext.bakeLayer(ExampleModel.LAYER_LOCATION)), 1f);
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull ExampleEntity pEntity) {
		return SGP.resourceLocation("textures/entity/example.png");
	}
}
