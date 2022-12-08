import be.thebeehive.wouterbauweraerts.common.readFromFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TreetopTreehouseTest {
    @Test
    internal fun `Part 1 returns expected`() {
        assertThat(treehouse1(readFromFile("test-input.txt"))).isEqualTo(21)
    }

    @Test
    internal fun `Part 2 returns expected`() {
        assertThat(treehouse2(readFromFile("test-input.txt"))).isEqualTo(8)
    }
}
