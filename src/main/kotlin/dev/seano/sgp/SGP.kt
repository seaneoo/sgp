package dev.seano.sgp

import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object SGP : ModInitializer {

	private const val MOD_ID = "sgp"
	val logger: Logger = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		logger.info("onInitialize")
	}
}
