package es.eriktorr.katas

import es.eriktorr.katas.Roll.FIRST_ROLL
import es.eriktorr.katas.Roll.SECOND_ROLL

sealed class Frame {

    companion object {
        val TOTAL_NUMBER_OF_PINS = 10
    }

    abstract fun pinFallSum(): Int

    abstract fun pinFallBy(roll: Roll): Int?

    data class Regular(private val pinFallFirstRoll: Int, private val pinFallSecondRoll: Int = 0): Frame() {
        override fun pinFallSum(): Int = pinFallFirstRoll + pinFallSecondRoll
        override fun pinFallBy(roll: Roll): Int {
            return when (roll) {
                FIRST_ROLL -> pinFallFirstRoll
                SECOND_ROLL -> pinFallSecondRoll
            }
        }
    }

    object Strike: Frame() {
        override fun pinFallSum(): Int = TOTAL_NUMBER_OF_PINS
        override fun pinFallBy(roll: Roll): Int? {
            return when (roll) {
                FIRST_ROLL -> TOTAL_NUMBER_OF_PINS
                SECOND_ROLL -> null
            }
        }
    }

    data class Spare(private val pinFallFirstRoll: Int): Frame() {
        override fun pinFallSum(): Int = TOTAL_NUMBER_OF_PINS
        override fun pinFallBy(roll: Roll): Int {
            return when (roll) {
                FIRST_ROLL -> pinFallFirstRoll
                SECOND_ROLL -> TOTAL_NUMBER_OF_PINS - pinFallFirstRoll
            }
        }
    }

    data class LastFrame(private val pinFallFirstRoll: Int, private val pinFallSecondRoll: Int = 0,
                         private val pinFallThirdRoll: Int = 0): Frame() {
        override fun pinFallSum(): Int = pinFallFirstRoll + pinFallSecondRoll + pinFallThirdRoll
        override fun pinFallBy(roll: Roll): Int? {
            return when (roll) {
                FIRST_ROLL -> pinFallFirstRoll
                SECOND_ROLL -> pinFallSecondRoll
            }
        }
    }

}