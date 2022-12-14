import be.thebeehive.wouterbauweraerts.common.readFromFile

fun main(args: Array<String>) {
    println(calorieCount(readFromFile("input.txt"), 1))
    println(calorieCount(readFromFile("input.txt"), 3))
}

fun calorieCount(input: String, topX: Int): Int {
    return parseElves(input)
        .sortedByDescending { it.totalAmount() }
        .take(topX)
        .sumOf { it.totalAmount() }
}

fun parseElves(input: String): List<Elf> {
    return input.split("\n\n")
        .map { parseElf(it) }
}

private fun parseElf(input: String): Elf {
    return Elf(
        input.split("\n")
            .map { it.toInt() }
    )
}

data class Elf(val food: List<Int>) {
    fun totalAmount(): Int {
        return food.sum()
    }
}
