import be.thebeehive.wouterbauweraerts.common.readFromFile

fun main() {
    println("Part 1: ${fileSystem1(readFromFile("input.txt"), 100000)}")
    println("Part 2: ${fileSystem2(readFromFile("input.txt"), 30000000)}")
}
