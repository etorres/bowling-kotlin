package es.eriktorr.katas

import es.eriktorr.katas.Frame.*
import es.eriktorr.katas.Roll.FIRST_ROLL
import es.eriktorr.katas.Roll.SECOND_ROLL

class BowlingScoreCalculator(val gameParser: GameParser) {

    fun scoreOf(game: String): Int {
        val frames = gameParser.framesFrom(game)
        return scoreOf(frames, 0, 0)
    }

    private tailrec fun scoreOf(frames: List<Frame>, turn: Int, accumulated: Int): Int {
        val frame = frames[turn]
        val score = accumulated + when (frame) {
            is Regular -> frame.pinFallSum()
            is Spare -> frame.pinFallSum() + pinFallByNextRoll(frames, turn)
            is Strike -> frame.pinFallSum() + pinFallByNextTwoRolls(frames, turn)
            is LastFrame -> frame.pinFallSum()
        }
        return if (turn == frames.lastIndex) {
            score
        } else {
            scoreOf(frames, turn + 1, score)
        }
    }

    private fun pinFallByNextRoll(frames: List<Frame>, turn: Int): Int = frames[turn + 1].pinFallBy(FIRST_ROLL)!!

    private fun pinFallByNextTwoRolls(frames: List<Frame>, turn: Int): Int {
        var pinFallByNextNextRoll: Int? = frames[turn + 1].pinFallBy(SECOND_ROLL)
        if (pinFallByNextNextRoll == null) {
            pinFallByNextNextRoll = frames[turn + 2].pinFallBy(FIRST_ROLL)
        }
        return pinFallByNextRoll(frames, turn) + pinFallByNextNextRoll!!
    }

}