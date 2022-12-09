import be.thebeehive.wouterbauweraerts.common.readFromFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RopeBridgeTest {
    @Test
    internal fun `part 1 rerturns expected`() {
        assertThat(ropeBridge(readFromFile("test-input.txt"))).isEqualTo(13)
    }

    @Test
    internal fun `part 2 rerturns expected`() {
        assertThat(ropeBridge(readFromFile("test-input.txt"), 10)).isEqualTo(1)
    }

    @Test
    internal fun `part 2 xl rerturns expected`() {
        assertThat(ropeBridge(readFromFile("test-input-2.txt"), 10)).isEqualTo(36)
    }
}
