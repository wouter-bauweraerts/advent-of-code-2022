import be.thebeehive.wouterbauweraerts.common.readFromFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CathodeRayTubeTest {

    @ParameterizedTest
    @MethodSource("signalStrengthSrc")
    internal fun `signal strength is calculated correctly`(cycle: Int, value: Int) {
        assertThat(
            summateSignalStrength(
                readFromFile("test-input-l.txt"),
                listOf(cycle)
            )
        )
            .isEqualTo(value)
    }

    companion object {
        @JvmStatic
        fun signalStrengthSrc(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(20, 420),
                Arguments.of(60, 1140),
                Arguments.of(100, 1800),
                Arguments.of(140, 2940),
                Arguments.of(180, 2880),
                Arguments.of(220, 3960),
            )
        }
    }

    @Test
    internal fun `part 1 returns expected`() {
        assertThat(
            summateSignalStrength(
                readFromFile("test-input-l.txt"),
                listOf(20, 60, 100, 140, 180, 220)
            )
        ).isEqualTo(13140)
    }

    @Test
    internal fun `part 2 created the expected String`() {
        assertThat(
            draw(
                readFromFile("test-input-l.txt")
            )
        ).isEqualTo(readFromFile("result.txt"))
    }
}