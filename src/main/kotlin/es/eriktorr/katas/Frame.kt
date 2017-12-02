package es.eriktorr.katas

sealed class Frame {

    companion object {
        val PINS_TOTAL = 10
    }

    abstract fun simpleTotal(): Int

    data class Regular(private val roll1: Int, private val roll2: Int): Frame() {
        override fun simpleTotal(): Int = roll1 + roll2
    }

    object Strike: Frame() {
        override fun simpleTotal(): Int = PINS_TOTAL
    }

    data class Spare(val roll1: Int): Frame() {
        override fun simpleTotal(): Int = PINS_TOTAL
    }

    data class LastFrame(private val roll1: Int, private val roll2: Int = 0, private val roll3: Int = 0): Frame() {
        override fun simpleTotal(): Int = roll1 + roll2 + roll3
    }

}