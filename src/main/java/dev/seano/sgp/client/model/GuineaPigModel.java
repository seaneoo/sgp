package dev.seano.sgp.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import dev.seano.sgp.SGP;
import dev.seano.sgp.entity.GuineaPig;

import org.jetbrains.annotations.NotNull;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.Entity;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuineaPigModel<T extends Entity> extends EntityModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(SGP.resourceLocation(GuineaPig.ID),
			"main");

	private final ModelPart body;
	private final ModelPart rightHindLeg;
	private final ModelPart leftHindLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart leftFrontLeg;
	private final ModelPart head;

	public GuineaPigModel(ModelPart root) {
		this.body = root.getChild("body");
		this.rightHindLeg = root.getChild("rightHindLeg");
		this.leftHindLeg = root.getChild("leftHindLeg");
		this.rightFrontLeg = root.getChild("rightFrontLeg");
		this.leftFrontLeg = root.getChild("leftFrontLeg");
		this.head = root.getChild("head");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0)
						.addBox(-1.5F, -2.75F, -2.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(12, 2)
						.addBox(-0.5F, -1.25F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 23.5F, 0.0F));

		PartDefinition rightHindLeg = partdefinition.addOrReplaceChild("rightHindLeg",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-0.75F, 23.5F, 3.25F));

		PartDefinition leftHindLeg = partdefinition.addOrReplaceChild("leftHindLeg",
				CubeListBuilder.create().texOffs(0, 2)
						.addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.75F, 23.5F, 3.25F));

		PartDefinition rightFrontLeg = partdefinition.addOrReplaceChild("rightFrontLeg",
				CubeListBuilder.create().texOffs(0, 4)
						.addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-0.75F, 23.5F, -3.25F));

		PartDefinition leftFrontLeg = partdefinition.addOrReplaceChild("leftFrontLeg",
				CubeListBuilder.create().texOffs(12, 0)
						.addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.75F, 23.5F, -3.25F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 9)
						.addBox(-1.5F, -1.375F, -3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(9, 9)
						.addBox(-1.5F, -0.375F, -4.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(3, 2)
						.addBox(-2.0F, -0.875F, -1.5F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(3, 0)
						.addBox(1.0F, -0.875F, -1.5F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 22.125F, -1.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
						  float headPitch) {
//		this.head.xRot = headPitch * ((float) Math.PI / 180F);
//		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
//		this.rightHindLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
//		this.leftHindLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
//		this.rightFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
//		this.leftFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight,
							   int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightHindLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftHindLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightFrontLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftFrontLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
