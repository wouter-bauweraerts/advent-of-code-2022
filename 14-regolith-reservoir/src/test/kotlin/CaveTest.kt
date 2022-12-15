import be.thebeehive.wouterbauweraerts.common.readFromFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CaveTest {
    @Test
    internal fun `part 1 returns expected`() {
        assertThat(part1(readFromFile("test-input.txt"))).isEqualTo(24)
    }

    @Test
    internal fun `part 2 returns expected`() {
        assertThat(part2(readFromFile("test-input.txt"))).isEqualTo(93)
    }
}
