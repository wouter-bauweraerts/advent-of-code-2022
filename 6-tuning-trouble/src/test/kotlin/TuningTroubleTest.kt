import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class TuningTroubleTest {
    @ParameterizedTest
    @MethodSource("tuningTrouble1")
    internal fun `part 1 returns expected`(message: String, markerPosition: Int) {
        assertThat(startOfPacketMarker(message)).isEqualTo(markerPosition)
    }

    @ParameterizedTest
    @MethodSource("tuningTrouble2")
    internal fun `part 2 returns expected`(message: String, markerPosition: Int) {
        assertThat(startOfMessageMarker(message)).isEqualTo(markerPosition)
    }

    companion object {
        @JvmStatic
        fun tuningTrouble1(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 7),
                Arguments.of("bvwbjplbgvbhsrlpgdmjqwftvncz", 5),
                Arguments.of("nppdvjthqldpwncqszvftbrmjlhg", 6),
                Arguments.of("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 10),
                Arguments.of("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 11)
            )
        }

        @JvmStatic
        fun tuningTrouble2(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 19),
                Arguments.of("bvwbjplbgvbhsrlpgdmjqwftvncz", 23),
                Arguments.of("nppdvjthqldpwncqszvftbrmjlhg", 23),
                Arguments.of("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 29),
                Arguments.of("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 26)
            )
        }
    }
}
