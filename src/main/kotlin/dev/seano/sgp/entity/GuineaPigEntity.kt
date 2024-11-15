package dev.seano.sgp.entity

import dev.seano.sgp.registry.SGPEntities
import dev.seano.sgp.registry.SGPTags.GUINEA_PIG_FOOD
import net.minecraft.block.BlockState
import net.minecraft.component.DataComponentTypes
import net.minecraft.entity.*
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.CreeperEntity
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.passive.*
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.tag.DamageTypeTags
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.Util
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World

class GuineaPigEntity(entityType: EntityType<out TameableEntity>?, world: World?) : TameableEntity(entityType, world) {

	companion object {

		private const val MAX_HEALTH = 6.0
		private const val TAMED_MAX_HEALTH = 12.0
		private val VARIANT: TrackedData<Int> =
			DataTracker.registerData(GuineaPigEntity::class.java, TrackedDataHandlerRegistry.INTEGER)


		fun createAttributes(): DefaultAttributeContainer.Builder {
			return MobEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, MAX_HEALTH)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
		}
	}

	val idlingAnimationState = AnimationState()
	val sittingAnimationState = AnimationState()
	private var idleAnimationCooldown = 0

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

	fun getVariant(): GuineaPigVariant? {
		return GuineaPigVariant.byIndex(this.dataTracker.get(VARIANT))
	}

	private fun setVariant(guineaPigVariant: GuineaPigVariant) {
		this.dataTracker.set(VARIANT, guineaPigVariant.id)
	}

	override fun initDataTracker(builder: DataTracker.Builder) {
		super.initDataTracker(builder)
		builder.add(VARIANT, 0)
	}

	override fun readCustomDataFromNbt(nbt: NbtCompound) {
		super.readCustomDataFromNbt(nbt)
		GuineaPigVariant.byIndex(nbt.getInt("Variant"))?.let { setVariant(it) }
	}

	override fun writeCustomDataToNbt(nbt: NbtCompound) {
		super.writeCustomDataToNbt(nbt)
		getVariant()?.let { nbt.putInt("Variant", it.id) }
	}

	override fun initialize(
		world: ServerWorldAccess?, difficulty: LocalDifficulty?, spawnReason: SpawnReason?, entityData: EntityData?
	): EntityData? {
		if (world != null) setVariant(Util.getRandom(GuineaPigVariant.entries.toTypedArray(), world.random))
		return super.initialize(world, difficulty, spawnReason, entityData)
	}

	override fun tick() {
		super.tick()
		if (world.isClient()) updateAnimations()
	}

	private fun updateAnimations() {
		if (this.idleAnimationCooldown <= 0) {
			this.idleAnimationCooldown = random.nextInt(40) + 80
			idlingAnimationState.start(age)
		} else {
			idleAnimationCooldown--
		}

		if (isInSittingPose && isTamed) {
			sittingAnimationState.startIfNotRunning(age)
		} else {
			sittingAnimationState.stop()
		}
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

	override fun breed(world: ServerWorld, other: AnimalEntity) {
		val children = createMultipleChildren(world, other)
		children.forEach { child ->
			child.isBaby = true
			child.refreshPositionAndAngles(x, y, z, 0f, 0f)
			this.breed(world, other, child)
			world.spawnEntityAndPassengers(child)
		}
	}

	private fun createMultipleChildren(world: ServerWorld, entity: PassiveEntity): ArrayList<GuineaPigEntity> {
		val children = ArrayList<GuineaPigEntity>()
		val numOfChildren = random.nextInt(3)
		for (i in 0 until numOfChildren) createChild(world, entity)?.let { children.add(it) }
		return children
	}

	override fun createChild(world: ServerWorld, entity: PassiveEntity): GuineaPigEntity? {
		val guineaPigEntity = SGPEntities.GUINEA_PIG?.create(world)
		if (guineaPigEntity != null && entity is GuineaPigEntity) {
			if (isTamed) {
				guineaPigEntity.ownerUuid = this.ownerUuid
				guineaPigEntity.setTamed(true, true)
			}
			entity.getVariant()?.let { guineaPigEntity.setVariant(it) }
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
