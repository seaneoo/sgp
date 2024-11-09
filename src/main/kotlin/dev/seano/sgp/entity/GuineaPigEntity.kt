package dev.seano.sgp.entity

import dev.seano.sgp.registry.SGPEntities
import dev.seano.sgp.registry.SGPTags.GUINEA_PIG_FOOD
import net.minecraft.block.BlockState
import net.minecraft.component.DataComponentTypes
import net.minecraft.entity.EntityStatuses
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.mob.CreeperEntity
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.passive.*
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.registry.tag.DamageTypeTags
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

class GuineaPigEntity(entityType: EntityType<out TameableEntity>?, world: World?) : TameableEntity(entityType, world) {

	companion object {

		private const val MAX_HEALTH = 6.0
		private const val TAMED_MAX_HEALTH = 12.0

		fun createAttributes(): DefaultAttributeContainer.Builder {
			return MobEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, MAX_HEALTH)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
		}
	}

	override fun initGoals() {
		goalSelector.add(0, SwimGoal(this))
		goalSelector.add(1, TameableEscapeDangerGoal(1.25, DamageTypeTags.PANIC_ENVIRONMENTAL_CAUSES))
		goalSelector.add(2, SitGoal(this))
		goalSelector.add(3, AnimalMateGoal(this, 1.0))
		goalSelector.add(4, TemptGoal(this, 1.2, { stack ->
			isBreedingItem(stack)
		}, false))
		goalSelector.add(5, FollowParentGoal(this, 1.1))
		goalSelector.add(6, FleeEntityGoal(this, CreeperEntity::class.java, 6f, 1.0, 1.25))
		goalSelector.add(6, FleeEntityGoal(this, OcelotEntity::class.java, 6f, 1.0, 1.25))
		goalSelector.add(6, FleeEntityGoal(this, CatEntity::class.java, 6f, 1.0, 1.25))
		goalSelector.add(7, FollowOwnerGoal(this, 1.1, 10.0f, 4.0f))
		goalSelector.add(8, WanderAroundFarGoal(this, 1.0))
		goalSelector.add(9, LookAtEntityGoal(this, PlayerEntity::class.java, 6.0f))
		goalSelector.add(9, LookAtEntityGoal(this, GuineaPigEntity::class.java, 4.0f))
		goalSelector.add(10, LookAroundGoal(this))
	}

	override fun getAmbientSound(): SoundEvent? {
		// TODO 2024-11-5, 21:56 custom sound
		return SoundEvents.ENTITY_PIG_AMBIENT
	}

	override fun getHurtSound(source: DamageSource?): SoundEvent? {
		// TODO 2024-11-5, 21:56 custom sound
		return SoundEvents.ENTITY_PIG_HURT
	}

	override fun getDeathSound(): SoundEvent? {
		// TODO 2024-11-5, 21:56 custom sound
		return SoundEvents.ENTITY_PIG_DEATH
	}

	override fun playStepSound(pos: BlockPos?, state: BlockState?) {
		// TODO 2024-11-5, 21:56 custom sound
		this.playSound(SoundEvents.ENTITY_PIG_STEP, 0.15f, 1.0f)
	}

	override fun getEatSound(stack: ItemStack?): SoundEvent {
		// TODO 2024-11-9, 13:59 custom sound
		return SoundEvents.ENTITY_CAT_EAT
	}

	override fun getLeashOffset(): Vec3d {
		return Vec3d(0.0, 0.1875, 0.0)
	}

	override fun isBreedingItem(stack: ItemStack): Boolean {
		return stack.isIn(GUINEA_PIG_FOOD)
	}

	override fun updateAttributesForTamed() {
		if (isTamed) {
			getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)!!.baseValue = TAMED_MAX_HEALTH
			health = TAMED_MAX_HEALTH.toFloat()
		} else {
			getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)!!.baseValue = MAX_HEALTH
		}
	}

	private fun tryTame(player: PlayerEntity) {
		if (random.nextInt(3) == 0) {
			setOwner(player)
			navigation.stop()
			isSitting = true
			world.sendEntityStatus(this, EntityStatuses.ADD_POSITIVE_PLAYER_REACTION_PARTICLES)
		} else {
			world.sendEntityStatus(this, EntityStatuses.ADD_NEGATIVE_PLAYER_REACTION_PARTICLES)
		}
	}

	override fun damage(source: DamageSource?, amount: Float): Boolean {
		if (this.isInvulnerableTo(source)) {
			return false
		} else {
			if (!world.isClient) isSitting = false
			return super.damage(source, amount)
		}
	}

	override fun interactMob(player: PlayerEntity, hand: Hand): ActionResult {
		val itemStack = player.getStackInHand(hand)
		if (isTamed) {
			if (isOwner(player)) {
				if (isBreedingItem(itemStack) && health < maxHealth) {
					if (!world.isClient) {
						eat(player, hand, itemStack)
						val foodComponent = itemStack.get(DataComponentTypes.FOOD)
						heal(
							foodComponent?.nutrition()?.toFloat()
								?: 1.0f
						)
					}
					return ActionResult.success(world.isClient())
				}

				val actionResult = super.interactMob(player, hand)
				if (!actionResult.isAccepted) {
					isSitting = !isSitting
					return ActionResult.success(world.isClient())
				}
			}
		} else if (isBreedingItem(itemStack)) {
			if (!world.isClient) {
				eat(player, hand, itemStack)
				tryTame(player)
				setPersistent()
				return ActionResult.success(world.isClient())
			}
		}

		val actionResult = super.interactMob(player, hand)
		if (actionResult.isAccepted) this.setPersistent()
		return actionResult
	}

	override fun createChild(world: ServerWorld?, entity: PassiveEntity): GuineaPigEntity? {
		val guineaPigEntity = SGPEntities.GUINEA_PIG?.create(world)
		if (guineaPigEntity != null && entity is GuineaPigEntity) {
			if (isTamed) {
				guineaPigEntity.ownerUuid = this.ownerUuid
				guineaPigEntity.setTamed(true, true)
			}
		}
		return guineaPigEntity
	}

	override fun canBreedWith(other: AnimalEntity): Boolean {
		return if (other == this) {
			false
		} else if (!isTamed) {
			false
		} else if (other !is GuineaPigEntity) {
			false
		} else if (!other.isTamed) {
			false
		} else {
			if (other.isInSittingPose) false else this.isInLove && other.isInLove()
		}
	}
}
