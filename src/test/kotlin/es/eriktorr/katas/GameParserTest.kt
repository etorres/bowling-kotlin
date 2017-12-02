package es.eriktorr.katas

import es.eriktorr.katas.GameParser.Companion.FRAMES_TOTAL
import es.eriktorr.katas.Frame.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameParserTest {

    @Test
    fun parseAllStrikesGame() {
        assertThat(GameParser().framesFrom("X X X X X X X X X XXX"))
                .hasSize(FRAMES_TOTAL)
                .containsExactly(
                        Strike, Strike, Strike, Strike, Strike, Strike, Strike, Strike, Strike,
                        LastFrame(10, 10, 10)
                )
    }

    @Test
    fun parseScoreThenMissGame() {
        assertThat(GameParser().framesFrom("9- 8- 7- 6- 5- 4- 3- 2- 1- 9-"))
                .hasSize(FRAMES_TOTAL)
                .containsExactly(
                        Regular(9), Regular(8),
                        Regular(7), Regular(6),
                        Regular(5), Regular(4),
                        Regular(3), Regular(2),
                        Regular(1), LastFrame(9)
                )
    }

    @Test
    fun parseAllSparesGame() {
        assertThat(GameParser().framesFrom("5/ 4/ 3/ 2/ 1/ 9/ 8/ 7/ 6/ 4/7"))
                .hasSize(FRAMES_TOTAL)
                .containsExactly(
                        Spare(5), Spare(4),
                        Spare(3), Spare(2),
                        Spare(1), Spare(9),
                        Spare(8), Spare(7),
                        Spare(6), LastFrame(4, 6, 7)
                )
    }

    @Test
    fun parseRandomGameEndWithStrike() {
        assertThat(GameParser().framesFrom("X 9/ 5/ 72 X X X 9- 8/ 9/X"))
                .hasSize(FRAMES_TOTAL)
                .containsExactly(
                        Strike, Spare(9), Spare(5),
                        Regular(7, 2), Strike, Strike, Strike,
                        Regular(9), Spare(8),
                        LastFrame(9, 1, 10)
                )
    }

    @Test
    fun parseRandomGameEndWithMiss() {
        assertThat(GameParser().framesFrom("X 9/ 5/ 72 X X X 9- 8/ 9/-"))
                .hasSize(FRAMES_TOTAL)
                .containsExactly(
                        Strike, Spare(9), Spare(5),
                        Regular(7, 2), Strike, Strike, Strike,
                        Regular(9), Spare(8),
                        LastFrame(9, 1, 0)
                )
    }

    @Test
    fun parseRandomGameEndWithScore() {
        assertThat(GameParser().framesFrom("X 9/ 5/ 72 X X X 9- 8/ 8/3"))
                .hasSize(FRAMES_TOTAL)
                .containsExactly(
                        Strike, Spare(9), Spare(5),
                        Regular(7, 2), Strike, Strike, Strike,
                        Regular(9), Spare(8),
                        LastFrame(8, 2, 3)
                )
    }

}