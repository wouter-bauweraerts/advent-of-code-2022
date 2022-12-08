import be.thebeehive.wouterbauweraerts.common.readFromFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NoSpaceLeftTest {
    @Test
    internal fun `part 1 returns expected`() {
        assertThat(fileSystem1(readFromFile("test-input.txt"), 100000)).isEqualTo(95437)
    }

    @Test
    internal fun `part 2 returns expected`() {
        assertThat(fileSystem2(readFromFile("test-input.txt"), 30000000)).isEqualTo(24933642)
    }
}
