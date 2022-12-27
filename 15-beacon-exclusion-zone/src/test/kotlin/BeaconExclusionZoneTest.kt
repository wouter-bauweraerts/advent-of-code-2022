import be.thebeehive.wouterbauweraerts.common.readFromFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BeaconExclusionZoneTest {
    @Test
    internal fun `part 1 returns expected`() {
        assertThat(beaconExclusion1(readFromFile("test-input.txt"), 10))
    }

    @Test
    internal fun `part 2 returns expected`() {
        assertThat(beaconExclusion2(readFromFile("test-input.txt"), 20)).isEqualTo(56000011)
    }
}
