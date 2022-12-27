import be.thebeehive.wouterbauweraerts.common.readFromFile

fun main() {
    val input = readFromFile("input.txt")
    val day = Day16(input.split("\n"))

    println("Part 1: ${day.solvePart1()}")
    println("Part 2: ${day.solvePart2()}")
}
