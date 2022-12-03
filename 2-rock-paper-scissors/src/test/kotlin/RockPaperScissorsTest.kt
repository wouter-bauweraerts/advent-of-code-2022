import be.thebeehive.wouterbauweraerts.common.readFromFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RockPaperScissorsTest {
    @Test
    internal fun `part 1 returns expected score after given rounds`() {
        assertThat(rockPaperScissors(readFromFile("test-input.txt"), true)).isEqualTo(15)
    }

    @Test
    internal fun `part 2 returns expected score after given rounds`() {
        assertThat(rockPaperScissors(readFromFile("test-input.txt"), false)).isEqualTo(12)
    }
}
