package es.eriktorr.katas

import es.eriktorr.katas.Frame.*
import es.eriktorr.katas.Frame.Companion.TOTAL_NUMBER_OF_PINS
import es.eriktorr.katas.Roll.FIRST_ROLL
import es.eriktorr.katas.Roll.SECOND_ROLL
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class FrameTest {

    companion object {
        private val REGULAR_FRAME = Regular(2, 7)
        private val STRIKE_FRAME = Strike
        private val SPARE_FRAME = Spare(4)
        private val LAST_FRAME = LastFrame(10, 10, 10)
    }

    @TestFactory
    fun pinFallSumInFrame() = listOf(
            REGULAR_FRAME to 9,
            STRIKE_FRAME to TOTAL_NUMBER_OF_PINS,
            SPARE_FRAME to TOTAL_NUMBER_OF_PINS,
            LAST_FRAME to 30)
            .map { (frame, pinFallSum) ->
                DynamicTest.dynamicTest("pinFallSum($frame) => $pinFallSum") {
                    assertThat(frame.pinFallSum()).isEqualTo(pinFallSum)
                }
            }

    @TestFactory
    fun pinFallByFirstRollInFrame() = listOf(
            REGULAR_FRAME to 2,
            STRIKE_FRAME to TOTAL_NUMBER_OF_PINS,
            SPARE_FRAME to 4,
            LAST_FRAME to 10)
            .map { (frame, pinFallByFirstRoll) ->
                DynamicTest.dynamicTest("pinFallByFirstRoll($frame) => $pinFallByFirstRoll") {
                    assertThat(frame.pinFallBy(FIRST_ROLL)).isEqualTo(pinFallByFirstRoll)
                }
            }

    @TestFactory
    fun pinFallBySecondRollInFrame() = listOf(
            REGULAR_FRAME to 7,
            STRIKE_FRAME to null,
            SPARE_FRAME to 6,
            LAST_FRAME to 10)
            .map { (frame, pinFallBySecondtRoll) ->
                DynamicTest.dynamicTest("pinFallBySecondtRoll($frame) => $pinFallBySecondtRoll") {
                    assertThat(frame.pinFallBy(SECOND_ROLL)).isEqualTo(pinFallBySecondtRoll)
                }
            }

}