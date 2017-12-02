package es.eriktorr.katas

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class FrameTest {

    @TestFactory
    fun calculateGameScore() = listOf(
            "" to "")
            .map { (text, frame) ->
                DynamicTest.dynamicTest("frameFrom($text) => $frame") {
                    Assertions.assertThat(frameBuilder.frameFrom(text)).isEqualTo(frame)
                }
            }

}