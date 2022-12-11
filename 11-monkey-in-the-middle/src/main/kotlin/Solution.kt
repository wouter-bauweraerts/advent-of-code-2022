data class Monkey(
    val items: ArrayDeque<Long>,
    val rawOperation: String,
    val divBy: Long,
    val throwToWhenTrue: Int,
    val throwToWhenFalse: Int,
    var inspectCount: Long = 0L
)

fun monkeyBusiness(input: String, part1: Boolean): Long {
    return monkeyBusiness(input.split("\n"), part1)
}

fun monkeyBusiness(input: List<String>, part1: Boolean): Long {
    val monkeys = buildMonkey(input)
    val modBy = monkeys.map { it.divBy }.reduce { a, b -> a * b }

    repeat(if (part1) 20 else 10000) {
        monkeys.forEach { monkey ->
            monkey.items.forEach { item ->
                val worryLevel = calculateWorryLevel(item, monkey.rawOperation, part1, modBy)

                val throwTo = if (worryLevel % monkey.divBy == 0L) {
                    monkey.throwToWhenTrue
                } else {
                    monkey.throwToWhenFalse
                }
                monkeys[throwTo].items.addLast(worryLevel)
            }
            monkey.inspectCount += monkey.items.size
            monkey.items.clear()
        }
    }

    return monkeys.sortedByDescending { it.inspectCount }.let {
        it[0].inspectCount * it[1].inspectCount
    }
}

fun buildMonkey(input: List<String>): List<Monkey> {
    return buildList {
        input.filter { it.isNotBlank() }
            .chunked(6)
            .map { it.map { s -> s.trim() } }
            .forEach { list ->
                add(
                    Monkey(
                        items = ArrayDeque<Long>().also { dequeue ->
                            list[1].substringAfter(":")
                                .trim()
                                .split(",")
                                .map { it.trim().toLong() }
                                .forEach { dequeue.add(it) }
                        },
                        rawOperation = list[2].substringAfter("old").trim(),
                        divBy = list[3].substringAfter("by").trim().toLong(),
                        throwToWhenTrue = list[4].substringAfter("monkey").trim().toInt(),
                        throwToWhenFalse = list[5].substringAfter("monkey").trim().toInt()
                    )
                )
            }
    }
}

fun calculateWorryLevel(item: Long, rawOperation: String, part1: Boolean, modBy: Long): Long {
    val ops = rawOperation.split(" ").map { it.trim() }
    val secondFactor = if (ops[1].toLongOrNull() == null) item else ops[1].toLong()

    val worryLevel =  if (ops[0] == "*") {
        item *  secondFactor
    } else {
        item + secondFactor
    }

    return if (part1) {
         Math.floorDiv(worryLevel, 3)
    } else {
        worryLevel % modBy
    }
}