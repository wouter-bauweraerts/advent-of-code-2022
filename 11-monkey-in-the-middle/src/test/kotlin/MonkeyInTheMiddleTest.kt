import be.thebeehive.wouterbauweraerts.common.readFromFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MonkeyInTheMiddleTest {
    @Test
    internal fun `part 1 returns expected amount`() {
        assertThat(monkeyBusiness(readFromFile("test-input.txt"), true))
            .isEqualTo(10605)
    }

    @Test
    internal fun `part 2 returns expected amount`() {
        assertThat(monkeyBusiness(readFromFile("test-input.txt"), false))
            .isEqualTo(2713310158)
    }
}