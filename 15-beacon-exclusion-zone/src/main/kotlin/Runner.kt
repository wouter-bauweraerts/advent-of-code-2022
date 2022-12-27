import be.thebeehive.wouterbauweraerts.common.readFromFile

fun main() {
    val input = readFromFile("input.txt")

    println("Part 1: ${beaconExclusion1(input, 2000000)}")
    println("Part 1: ${beaconExclusion2(input, 4000000)}")
}
