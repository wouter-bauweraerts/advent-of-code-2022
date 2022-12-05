import be.thebeehive.wouterbauweraerts.common.readFromFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SupplyStackTest {
    @Test
    internal fun `part 1 returns expected string`() {
        assertThat(supplyStack(readFromFile("test-input.txt"))).isEqualTo("CMZ")
    }
}
