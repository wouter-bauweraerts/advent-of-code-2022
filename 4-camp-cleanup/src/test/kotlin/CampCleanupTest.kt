import be.thebeehive.wouterbauweraerts.common.readFromFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CampCleanupTest {
    @Test
    internal fun `part 1 returns expected number`() {
        assertThat(campCleanup(readFromFile("test-input.txt"))).isEqualTo(2)
    }

//    @Test
//    internal fun `part 2 returns expected`() {
//        assertThat(rucksackBadges(readFromFile("test-input.txt"))).isEqualTo(70)
//    }
}