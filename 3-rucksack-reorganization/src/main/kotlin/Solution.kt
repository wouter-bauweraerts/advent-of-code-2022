import be.thebeehive.wouterbauweraerts.common.readFromFile
import java.util.concurrent.atomic.AtomicInteger

fun main() {
    println("part 1: ${rucksackReorganization(readFromFile("input.txt"))}")
    println("part 2: ${rucksackBadges(readFromFile("input.txt"))}")
}

fun rucksackReorganization(input: String): Int {
    val lowercase = mapToPair(('a'..'z').toList(), 1)
    val uppercase = mapToPair(('A'..'Z').toList(), 27)

    return input.split("\n")
        .map { splitCompartiments(it) }
        .map { findDouble(it) }
        .sumOf { score(it, lowercase, uppercase) }
}

fun rucksackBadges(input: String): Int {
    val lowercase = mapToPair(('a'..'z').toList(), 1)
    val uppercase = mapToPair(('A'..'Z').toList(), 27)

    return input.split("\n")
        .chunked(3
    ) { Triple(it[0], it[1], it[2]) }
        .map { commonElement(it) }
        .sumOf { score(it, lowercase, uppercase) }
}

private fun mapToPair(input: List<Char>, startWith: Int): List<Pair<Char, Int>> {
    val atomicVal = AtomicInteger(startWith)
    return input.map { Pair(it, atomicVal.getAndIncrement()) }
}

private fun splitCompartiments(backpack: String): Pair<String, String> {
    val compartimentLength = backpack.length / 2
    return Pair(
        backpack.substring(0, compartimentLength),
        backpack.substring(compartimentLength)
    )
}

private fun findDouble(pair: Pair<String, String>): Char? {
        return pair.first.toCharArray()
            .firstOrNull { pair.second.contains(it, false) }
}

private fun commonElement(triple: Triple<String, String, String>): Char? {
    return triple.first.toCharArray()
        .firstOrNull { triple.second.contains(it) && triple.third.contains(it) }
}

private fun score(char: Char?, lowercase: List<Pair<Char, Int>>, uppercase: List<Pair<Char, Int>>): Int {
    return when(char) {
        null -> 0
        in 'a'..'z' -> lowercase.first { it.first == char }.second
        else -> uppercase.first { it.first == char }.second
    }
}