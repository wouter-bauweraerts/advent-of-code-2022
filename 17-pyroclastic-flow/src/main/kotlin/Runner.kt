import be.thebeehive.wouterbauweraerts.common.readFromFile

fun main() {
    val input = readFromFile("input.txt")
    println(solve(input, 2023))
    println(solve(input, 1000000000001L))
}
