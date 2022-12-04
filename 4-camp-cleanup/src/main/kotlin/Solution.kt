import be.thebeehive.wouterbauweraerts.common.readFromFile

fun main() {
    println("part 1: ${campCleanup(readFromFile("input.txt"))}")
}

fun campCleanup(input: String): Int {
    return mapPairs(input)
        .count { fullyContains(it.first, it.second) || fullyContains(it.second, it.first) }

}

private fun mapPairs(input: String): List<Pair<Pair<Int, Int>, Pair<Int, Int>>> {
    return input.split("\n")
        .map { mapLine(it) }
}

private fun mapLine(line: String): Pair<Pair<Int, Int>, Pair<Int, Int>> {
    return line.split(",")
        .map { mapPair(it) }
        .zipWithNext()
        .first()
}

private fun mapPair(pair: String): Pair<Int, Int> {
    return pair.split("-")
        .map { it.toInt() }
        .zipWithNext()
        .first()
}

private fun fullyContains(firstAssignment: Pair<Int, Int>, secondAssignment: Pair<Int, Int>): Boolean {
    return firstAssignment.first <= secondAssignment.first
            && firstAssignment.second >= secondAssignment.second
}