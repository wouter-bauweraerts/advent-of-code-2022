import be.thebeehive.wouterbauweraerts.common.readFromFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MonkeyInTheMiddleTest {
    @Test
    internal fun `part 1 returns expected amount`() {
        assertThat(monkeyBusiness1(readFromFile("test-input.txt")))
            .isEqualTo(10605)
    }

    @Test
    internal fun `part 2 returns expected amount`() {
        assertThat(monkeyBusiness2(readFromFile("test-input.txt")))
            .isEqualTo(2713310158)
    }
}