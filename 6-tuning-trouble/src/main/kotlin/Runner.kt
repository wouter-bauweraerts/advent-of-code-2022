import be.thebeehive.wouterbauweraerts.common.readFromFile

fun main() {
    println("Part 1: ${startOfPacketMarker(readFromFile("input.txt"))}")
    println("Part 2: ${startOfMessageMarker(readFromFile("input.txt"))}")
}
