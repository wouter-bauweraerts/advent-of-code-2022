import be.thebeehive.wouterbauweraerts.common.readFromFile
import java.time.Duration
import java.time.LocalDateTime

fun main() {
    val start = LocalDateTime.now()

    println(ropeBridge(readFromFile("input.txt")))
    println(ropeBridge(readFromFile("input.txt"), 10))

    println(Duration.between(start, LocalDateTime.now()))
}
