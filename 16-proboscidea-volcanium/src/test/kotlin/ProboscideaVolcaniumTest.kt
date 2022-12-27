import be.thebeehive.wouterbauweraerts.common.readFromFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ProboscideaVolcaniumTest {
    private val input = readFromFile("test-input.txt")

    lateinit var day: Day16

    @BeforeEach
    internal fun setUp() {
        day = Day16(input.split("\n"))
    }

    @Test
    internal fun `part 1 returns expected`() {
        assertThat(day.solvePart1()).isEqualTo(1651)
    }

    @Test
    internal fun `part 2 returns expected`() {
        assertThat(day.solvePart2()).isEqualTo(1707)
    }
}
