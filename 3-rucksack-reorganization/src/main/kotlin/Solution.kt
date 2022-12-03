import be.thebeehive.wouterbauweraerts.common.readFromFile
import java.util.concurrent.atomic.AtomicLong

fun main(args: Array<String>) {
    println("part 1: ${rucksackReorganization(readFromFile("input.txt"))}")
}

fun rucksackReorganization(input: String): Long {
    val lowercase = mapToPair(('a'..'z').toList(), 1)
    val uppercase = mapToPair(('A'..'Z').toList(), 27)

    return input.split("\n")
        .map { splitCompartiments(it) }
        .map { findDouble(it) }
        .sumOf { score(it, lowercase, uppercase) }
}

private fun mapToPair(input: List<Char>, startWith: Long): List<Pair<Char, Long>> {
    val atomicVal = AtomicLong(startWith)
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
    try {
        return pair.first.toCharArray()
            .first { pair.second.contains(it, false) }
    } catch (e: Exception) {
        println("No duplicates in ${pair.first} and ${pair.second}")
        return null
    }
}

private fun score(char: Char?, lowercase: List<Pair<Char, Long>>, uppercase: List<Pair<Char, Long>>): Long {
    return when(char) {
        null -> 0
        in 'a'..'z' -> lowercase.first { it.first.equals(char) }.second
        else -> uppercase.first { it.first.equals(char) }.second
    }
}