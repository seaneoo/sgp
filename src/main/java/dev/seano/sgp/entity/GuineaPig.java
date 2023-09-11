package dev.seano.sgp.entity;

import dev.seano.sgp.registry.ModEntityTypes;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GuineaPig extends Animal {

    public static final String ID = "guinea_pig";

    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.WHEAT, Items.APPLE, Items.MELON_SLICE,
            Items.BEETROOT, Items.CARROT, Blocks.WHEAT, Items.GOLDEN_APPLE);

    public GuineaPig(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createGuineaPigAttributes() {
        return createMobAttributes().add(Attributes.MAX_HEALTH, 4d).add(Attributes.MOVEMENT_SPEED, 0.25d);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new PanicGoal(this, 1.05f));
        this.goalSelector.addGoal(3, new BreedGoal(this, 0.95f));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1f, FOOD_ITEMS, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1f, 1.0000001E-5f));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 10f));
    }

    @Override
    public boolean isFood(@NotNull ItemStack itemStack) {
        return FOOD_ITEMS.test(itemStack);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel pLevel, @NotNull AgeableMob pOtherParent) {
        return ModEntityTypes.GUINEA_PIG.get().create(pLevel);
    }
}
