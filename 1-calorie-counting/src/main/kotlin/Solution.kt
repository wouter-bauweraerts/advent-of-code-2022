import be.thebeehive.wouterbauweraerts.common.readFromFile

fun main(args: Array<String>) {
    println(calorieCount(readFromFile("input.txt")))
}

fun calorieCount(input: String): Int {
    return parseElves(input)
        .maxBy { it.totalAmount() }
        .totalAmount()
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
