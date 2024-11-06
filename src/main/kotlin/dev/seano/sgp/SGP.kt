package dev.seano.sgp

import dev.seano.sgp.registry.SGPEntities
import dev.seano.sgp.registry.SGPItems
import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object SGP : ModInitializer {

	private const val MOD_ID = "sgp"
	val logger: Logger = LoggerFactory.getLogger(MOD_ID)

	fun id(path: String): Identifier = Identifier.of(MOD_ID, path)

	override fun onInitialize() {
		logger.info("onInitialize")

		SGPEntities.registerAttributes()
		SGPItems.populateItemGroups()
	}
}
