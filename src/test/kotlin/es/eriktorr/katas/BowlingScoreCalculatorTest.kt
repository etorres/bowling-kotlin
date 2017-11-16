package es.eriktorr.katas

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class BowlingScoreCalculatorTest {

    private val bowlingScoreCalculator = BowlingScoreCalculator()

    @TestFactory
    fun calculateGameScore() = listOf(
            "X X X X X X X X X X X X" to 300,
            "9- 9- 9- 9- 9- 9- 9- 9- 9- 9-" to 90,
            "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5" to 150)
            .map { (rolls, gameScore) ->
                DynamicTest.dynamicTest("[$rolls] => $gameScore") {
                    assertThat(bowlingScoreCalculator.scoreFrom(rolls)).isEqualTo(gameScore)
                }
            }

}