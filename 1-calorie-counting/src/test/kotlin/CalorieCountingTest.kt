import be.thebeehive.wouterbauweraerts.common.readFromFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CalorieCountingTest {
    @Test
    internal fun `part 1 returns the expected amount of calories`() {
        assertThat(calorieCount(readFromFile("test-input.txt"), 1)).isEqualTo(24000);
    }

    @Test
    internal fun `part 2 returns the expected amount of calories`() {
        assertThat(calorieCount(readFromFile("test-input.txt"), 3)).isEqualTo(45000);
    }
}
