package dev.seano.sgp.entity

import dev.seano.sgp.registry.SGPEntities
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.server.world.ServerWorld
import net.minecraft.world.World

class GuineaPigEntity(entityType: EntityType<out AnimalEntity>?, world: World?) : AnimalEntity(entityType, world) {

	private val food = arrayOf(Items.WHEAT, Items.APPLE, Items.CARROT, Items.SWEET_BERRIES)

	companion object {

		fun createAttributes(): DefaultAttributeContainer.Builder {
			return MobEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
		}
	}

	override fun initGoals() {
		goalSelector.add(0, SwimGoal(this))
		goalSelector.add(1, EscapeDangerGoal(this, 1.25))
		goalSelector.add(2, AnimalMateGoal(this, 1.0))
		goalSelector.add(3, TemptGoal(this, 1.2, { stack ->
			isBreedingItem(stack)
		}, false))
		goalSelector.add(4, FollowParentGoal(this, 1.1))
		goalSelector.add(5, WanderAroundFarGoal(this, 1.0))
		goalSelector.add(6, LookAtEntityGoal(this, PlayerEntity::class.java, 6.0f))
		goalSelector.add(7, LookAtEntityGoal(this, GuineaPigEntity::class.java, 4.0f))
		goalSelector.add(8, LookAroundGoal(this))
	}

	override fun createChild(world: ServerWorld?, entity: PassiveEntity?): PassiveEntity? {
		return SGPEntities.GUINEA_PIG?.create(world)
	}

	override fun isBreedingItem(stack: ItemStack?): Boolean {
		return food.any { stack?.isOf(it) ?: false }
	}
}