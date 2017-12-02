package es.eriktorr.katas.acceptance

import es.eriktorr.katas.BowlingScoreCalculator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory

class BowlingScoreCalculatorTest {

    private val bowlingScoreCalculator = BowlingScoreCalculator()

    @Disabled("feature under development")
    @TestFactory
    fun calculateGameScore() = listOf(
            "X X X X X X X X X XXX" to 300,
            "9- 9- 9- 9- 9- 9- 9- 9- 9- 9-" to 90,
            "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5" to 150,
            "X 9/ 5/ 72 X X X 9- 8/ 9/X" to 187)
            .map { (gameLine, expectedScore) ->
                dynamicTest("scoreOf($gameLine) => $expectedScore") {
                    assertThat(bowlingScoreCalculator.scoreOf(gameLine)).isEqualTo(expectedScore)
                }
            }

}