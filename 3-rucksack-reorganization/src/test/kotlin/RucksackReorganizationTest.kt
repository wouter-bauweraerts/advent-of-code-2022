import be.thebeehive.wouterbauweraerts.common.readFromFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RucksackReorganizationTest {
    @Test
    internal fun `part 1 returns expected number`() {
        assertThat(rucksackReorganization(readFromFile("test-input.txt"))).isEqualTo(157)
    }

    @Test
    internal fun `part 2 returns expected`() {
        assertThat(rucksackBadges(readFromFile("test-input.txt"))).isEqualTo(70)
    }
}