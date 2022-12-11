import java.util.concurrent.atomic.AtomicLong
import kotlin.math.floor

fun monkeyBusiness1(input: String): Long {
    val monkeys = input.split("\n\n")
        .map { it.trim() }
        .map { parseMonkey(it) }

    monkeys.forEach {
        it.monkeys = monkeys
    }

    repeat(20) {
        monkeys.forEach { it.inspect() }
    }

    return monkeys
        .sortedByDescending { it.inspectCounter.get() }
        .map { it.inspectCounter.get() }
        .take(2)
        .reduce { a, b -> a * b }
}

fun monkeyBusiness2(input: String): Long {
    val monkeys = input.split("\n\n")
        .map { it.trim() }
        .map { parseMonkey(it) }

    monkeys.forEach {
        it.monkeys = monkeys
    }

    repeat(20) {
        monkeys.forEach { it.inspect() }
    }

    return monkeys
        .sortedByDescending { it.inspectCounter.get() }
        .map { it.inspectCounter.get() }
        .take(2)
        .reduce { a, b -> a * b }
}

fun parseMonkey(rawMonkeyData: String): Monkey {
    val monkeyLines = rawMonkeyData.split("\n")
        .map { it.trim() }
    return Monkey(
        monkeyId(monkeyLines[0]),
        parseItems(monkeyLines[1]),
        parseOperation(monkeyLines[2]),
        getDivider(monkeyLines[3]),
        toMonkeys(rawMonkeyData)
    )
}

private fun monkeyId(idLine: String) =
    idLine.replace(":", "")
        .split(" ")[1]
        .toInt()

private fun parseItems(itemsLine: String): MutableList<Int> =
    itemsLine.substringAfter(": ")
        .split(", ")
        .map { it.toInt() }
        .toMutableList()


private fun getDivider(testLine: String): Int =
    testLine.substringAfter("by ").toInt()

private fun toMonkeys(input: String): Pair<Int, Int> =
    input.substringAfter("If true:")
        .split("\n")
        .map { it.split(" ").last().toInt() }
        .zipWithNext()
        .first()


private fun parseOperation(input: String): FactorMapper {
    val parts = input.substringAfter("new = ")
        .split(" ")
    val firstFactor: FactorMapper = getFactor(parts[0])
    val secondFactor: FactorMapper = getFactor(parts[2])
    val operation: Operation = getOperation(parts[1])

    return { operation.invoke(firstFactor, secondFactor, it) }
}

private fun getFactor(input: String): FactorMapper =
    when (input) {
        "old" -> {
            { it }
        }
        else -> {
            { input.toInt() }
        }
    }

private fun getOperation(input: String): Operation =
    when (input) {
        "+" -> {
            { first: FactorMapper, second: FactorMapper, item: Int -> first.invoke(item) + second.invoke(item) }
        }
        "-" -> {
            { first: FactorMapper, second: FactorMapper, item: Int -> first.invoke(item) - second.invoke(item) }
        }
        "*" -> {
            { first: FactorMapper, second: FactorMapper, item: Int -> first.invoke(item) * second.invoke(item) }
        }
        else -> {
            { first: FactorMapper, second: FactorMapper, item: Int -> first.invoke(item) / second.invoke(item) }
        }
    }

data class Monkey(
    val id: Int,
    var items: MutableList<Int>,
    val operation: FactorMapper,
    val divider: Int,
    val toMonkeys: Pair<Int, Int>,
    var monkeys: List<Monkey> = emptyList()
) {
    val inspectCounter: AtomicLong = AtomicLong(0)

    fun inspect() {
        while (items.isNotEmpty()) {
            inspectCounter.incrementAndGet()
            toNext(
                floor(operation.invoke(items.removeFirst()) / 3.0)
                    .toInt()
            )
        }
    }

    fun inspect2() {
        while (items.isNotEmpty()) {
            inspectCounter.incrementAndGet()
            toNext(operation.invoke(items.removeFirst()))
        }
    }

    private fun toNext(item: Int) {
        val nextMonkey = if (item % divider == 0) {
            monkeys.first { it.id == toMonkeys.first }
        } else {
            monkeys.first { it.id == toMonkeys.second }
        }
        nextMonkey.items.add(item)
    }
}

fun Int.inspect(operation: FactorMapper): Int = operation.invoke(this)

typealias Operation = (FactorMapper, FactorMapper, Int) -> Int
typealias FactorMapper = (Int) -> Int