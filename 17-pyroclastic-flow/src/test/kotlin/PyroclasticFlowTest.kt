import be.thebeehive.wouterbauweraerts.common.readFromFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PyroclasticFlowTest {
    private val jetBlasts = readFromFile("test-input.txt")

    @Test
    internal fun `part 1 returns expected`() {
        assertThat(solve(jetBlasts, 2023)).isEqualTo(3068)
    }

    @Test
    internal fun `part 2 returns expected`() {
        assertThat(solve(jetBlasts, 1000000000001L)).isEqualTo(1514285714288)
    }
}
