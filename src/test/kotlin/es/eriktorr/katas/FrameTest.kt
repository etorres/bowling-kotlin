package es.eriktorr.katas

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import es.eriktorr.katas.Frame.Regular
import es.eriktorr.katas.Frame.Strike
import es.eriktorr.katas.Frame.Spare
import es.eriktorr.katas.Frame.LastFrame

class FrameTest {

    @Test
    fun simpleTotalRegularFrame() {
        assertThat(Regular(2, 7).simpleTotal()).isEqualTo(9);
    }

    @Test
    fun simpleTotalStrikeFrame() {
        assertThat(Strike.simpleTotal()).isEqualTo(10);
    }

    @Test
    fun simpleTotalSpareFrame() {
        assertThat(Spare(3).simpleTotal()).isEqualTo(10);
    }

    @Test
    fun simpleTotalLastFrame() {
        assertThat(LastFrame(10, 10, 10).simpleTotal()).isEqualTo(30);
    }

}