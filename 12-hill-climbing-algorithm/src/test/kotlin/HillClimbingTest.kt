import be.thebeehive.wouterbauweraerts.common.readFromFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HillClimbingTest {
    @Test
    internal fun `part 1 finishes in 31 steps`() {
        assertThat(hillClimb(readFromFile("test-input.txt"))).isEqualTo(31)
    }
}
