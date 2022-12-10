import be.thebeehive.wouterbauweraerts.common.readFromFile

fun main() {
    val cycles = listOf(20, 60, 100, 140, 180, 220)
    println("Part 1: ${summateSignalStrength(
        readFromFile("input.txt"),
        cycles
    )}")

    println()

    println("Part 2:\n-------\n")

    println(draw(readFromFile("input.txt")))
}