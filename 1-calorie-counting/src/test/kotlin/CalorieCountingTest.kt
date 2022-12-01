import be.thebeehive.wouterbauweraerts.common.readFromFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CalorieCountingTest {
    @Test
    internal fun `returns the expected amount of calories`() {
        assertThat(calorieCount(readFromFile("test-input.txt"))).isEqualTo(24000);
    }
}
