package dev.seano.sgp.entity

import net.minecraft.util.StringIdentifiable
import net.minecraft.util.function.ValueLists
import java.util.function.IntFunction

enum class GuineaPigVariant(val id: Int, val texture: String) : StringIdentifiable {

	BLACK(0, "black"),
	CHOCOLATE(1, "chocolate"),
	CLASSIC(2, "classic"),
	CREAM(3, "cream"),
	DUTCH(4, "dutch"),
	GOLDEN(5, "golden"),
	WHITE(6, "white");

	companion object {

		private val byId: IntFunction<GuineaPigVariant> = ValueLists.createIdToValueFunction(
			GuineaPigVariant::id, entries.toTypedArray(), ValueLists.OutOfBoundsHandling.CLAMP
		)

		fun byIndex(index: Int): GuineaPigVariant? {
			return byId.apply(index);
		}
	}

	override fun asString(): String {
		return name
	}
}
