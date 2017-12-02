package es.eriktorr.katas

import es.eriktorr.katas.Frame.*
import es.eriktorr.katas.Frame.Companion.TOTAL_NUMBER_OF_PINS

class GameParser {

    companion object {
        val FRAMES_TOTAL = 10
        private val FRAME_SEPARATOR_REGEX = "\\s".toRegex()
        private val SCORE_TWICE_LEFT_SOME_WITHOUT_FALLING = "^\\d{2}$".toRegex()
        private val SCORE_THEN_MISS = "^\\d-$".toRegex()
        private val IS_SPARE = "^\\d/$".toRegex()
        private val IS_STRIKE = "^X$".toRegex()
    }

    fun framesFrom(game: String): List<Frame> {
        return game.split(FRAME_SEPARATOR_REGEX, FRAMES_TOTAL).mapIndexed { index, frame ->
            when (index + 1) {
                FRAMES_TOTAL -> buildLast(frame)
                else -> buildOther(frame)
            }
        }
    }

    private fun buildOther(frame: String): Frame {
        return when {
            SCORE_TWICE_LEFT_SOME_WITHOUT_FALLING.matches(frame) -> Regular(scoreFrom(frame[0]), scoreFrom(frame[1]))
            SCORE_THEN_MISS.matches(frame) -> Regular(scoreFrom(frame[0]), 0)
            IS_SPARE.matches(frame) -> Spare(scoreFrom(frame[0]))
            IS_STRIKE.matches(frame) -> Strike
            else -> throw IllegalArgumentException("Wrong format of frame")
        }
    }

    private fun buildLast(frame: String): LastFrame = LastFrame(scoreFrom(frame, 0), scoreFrom(frame, 1), scoreFrom(frame, 2))

    private fun scoreFrom(frame: String, pos: Int): Int {
        return when {
            pos < frame.length && pos > 0 -> scoreFrom(frame[pos], frame[pos - 1])
            pos < frame.length -> scoreFrom(frame[pos])
            else -> 0
        }
    }

    private fun scoreFrom(symbol: Char, previous: Char = '0'): Int {
        return when (symbol) {
            '-' -> 0
            '/' -> TOTAL_NUMBER_OF_PINS - intValueFrom(previous)
            'X' -> TOTAL_NUMBER_OF_PINS
            else -> intValueFrom(symbol)
        }
    }

    private fun intValueFrom(symbol: Char) = symbol.toInt() - '0'.toInt()

}