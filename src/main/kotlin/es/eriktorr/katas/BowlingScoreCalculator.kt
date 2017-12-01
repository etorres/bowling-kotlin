package es.eriktorr.katas

class BowlingScoreCalculator {

    companion object {
        val FRAMES_TOTAL = 10
    }

    fun scoreOf(rolls: String): Int {
        val frames = framesFrom(rolls)

        println("$frames");

        return 0
    }

    private fun framesFrom(rolls: String) = rolls.split("\\s".toRegex(), FRAMES_TOTAL)

}