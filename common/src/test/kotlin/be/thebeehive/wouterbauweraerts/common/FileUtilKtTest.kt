package be.thebeehive.wouterbauweraerts.common

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

const val TEST_FILE = "test.txt"
const val EXPECTED_TEXT = "This is a test file"

internal class FileUtilKtTest {
    @Test
    internal fun `readFromFile reads text from file`() {
        assertThat(readFromFile(TEST_FILE)).isEqualTo(EXPECTED_TEXT)
    }
}