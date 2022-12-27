import be.thebeehive.wouterbauweraerts.common.readFromFile

fun main() {
    val input = readFromFile("input.txt")

    println("Part 1: ${boilingBoulders1(input)}")
    println("Part 2: ${boilingBoulders2(input)}")
}
