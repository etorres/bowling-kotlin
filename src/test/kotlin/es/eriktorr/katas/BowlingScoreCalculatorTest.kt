package es.eriktorr.katas

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory

class BowlingScoreCalculatorTest {

    private val bowlingScoreCalculator = BowlingScoreCalculator()

    @TestFactory
    fun calculateGameScore() = listOf(
            "X X X X X X X X X XXX" to 300,
            "9- 9- 9- 9- 9- 9- 9- 9- 9- 9-" to 90,
            "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5" to 150)
            .map { (rolls, gameScore) ->
                dynamicTest("[$rolls] => $gameScore") {
                    assertThat(bowlingScoreCalculator.scoreOf(rolls)).isEqualTo(gameScore)
                }
            }

}