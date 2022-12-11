import be.thebeehive.wouterbauweraerts.common.readFromFile

fun main() {
    println("Part 1: ${monkeyBusiness(readFromFile("input.txt"), true)}")
    println("Part 2: ${monkeyBusiness(readFromFile("input.txt"), false)}")
}