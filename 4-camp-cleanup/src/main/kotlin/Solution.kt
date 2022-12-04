import be.thebeehive.wouterbauweraerts.common.readFromFile

fun main() {
    println("part 1: ${campCleanup(readFromFile("input.txt"))}")
    println("part 2: ${campCleanup2(readFromFile("input.txt"))}")
}

fun campCleanup(input: String): Int {
    return mapPairs(input)
        .count { fullyContains(it.first, it.second) || fullyContains(it.second, it.first) }
}

fun campCleanup2(input: String): Int {
    return mapPairs(input)
        .count { hasOverlap(it.first, it.second) }
}

// Parse input
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

// part 1
private fun fullyContains(firstAssignment: Pair<Int, Int>, secondAssignment: Pair<Int, Int>): Boolean {
    return firstAssignment.first <= secondAssignment.first
            && firstAssignment.second >= secondAssignment.second
}

// part 2
private fun hasOverlap(firstAssignment: Pair<Int, Int>, secondAssignment: Pair<Int, Int>): Boolean {
    val first = (firstAssignment.first .. firstAssignment.second).toList()
    val second = (secondAssignment.first .. secondAssignment.second).toList()

    return first.any { second.contains(it) }
}