import be.thebeehive.wouterbauweraerts.common.readFromFile

fun main(args: Array<String>) {
    val input = readFromFile("input.txt")
    println("part 1: ${rockPaperScissors(input, true)}")
    println("part 2: ${rockPaperScissors(input, false)}")
}
