package dev.seano.sgp.client.render;

import dev.seano.sgp.SGP;
import dev.seano.sgp.client.model.GuineaPigModel;
import dev.seano.sgp.entity.GuineaPig;
import org.jetbrains.annotations.NotNull;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuineaPigRenderer extends MobRenderer<GuineaPig, GuineaPigModel<GuineaPig>> {

	public GuineaPigRenderer(EntityRendererProvider.Context pContext) {
		super(pContext, new GuineaPigModel<>(pContext.bakeLayer(GuineaPigModel.LAYER_LOCATION)), 0.25f);
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull GuineaPig pEntity) {
		return SGP.resourceLocation("textures/entity/guinea_pig.png");
	}
}
