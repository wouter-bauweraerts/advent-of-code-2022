import be.thebeehive.wouterbauweraerts.common.readFromFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DistressSignalTest {
    @Test
    internal fun `part 1 returns 13 as expected`() {
        assertThat(sumOfCorrectPairIndices(readFromFile("test-input.txt"))).isEqualTo(13)
    }

    @Test
    internal fun `part 2 returns 13 as expected`() {
        assertThat(decoderKey(readFromFile("test-input.txt"))).isEqualTo(140)
    }
}
