import be.thebeehive.wouterbauweraerts.common.readFromFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class BoilingBouldersTest {
    @ParameterizedTest
    @MethodSource("part1Source")
    internal fun `part 1 returns expected`(input: String, expected: Int) {
        assertThat(boilingBoulders1(input)).isEqualTo(expected)
    }

    @Test
    internal fun `part 2 returns expected`() {
        assertThat(boilingBoulders2(readFromFile("test-lg.txt"))).isEqualTo(58)
    }

    companion object {
        @JvmStatic
        fun part1Source(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(readFromFile("test-small.txt"), 10),
                Arguments.of(readFromFile("test-lg.txt"), 64),
            )
        }
    }
}
