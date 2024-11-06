package dev.seano.sgp

import net.fabricmc.api.ClientModInitializer

object SGPClient : ClientModInitializer {

	override fun onInitializeClient() {
		SGP.logger.info("onInitializeClient")
	}
}
