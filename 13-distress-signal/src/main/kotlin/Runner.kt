import be.thebeehive.wouterbauweraerts.common.readFromFile

fun main() {
    println("Part 1: ${sumOfCorrectPairIndices(readFromFile("input.txt"))}")
    println("Part 2: ${decoderKey(readFromFile("input.txt"))}")
}
