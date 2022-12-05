import be.thebeehive.wouterbauweraerts.common.readFromFile
import java.util.concurrent.atomic.AtomicInteger

fun main(args: Array<String>) {
    println("part 1: ${supplyStack(readFromFile("input.txt"))}")
    println("part 2: ${supplyStack9001(readFromFile("input.txt"))}")
}

fun supplyStack(input: String): String {
    val parsedInput = parseInput(input);
    return parsedInput.first.process(parsedInput.second)
        .top()
}

fun supplyStack9001(input: String): String {
    val parsedInput = parseInput(input);
    return parsedInput.first.process9001(parsedInput.second)
        .top()
}

// Parse input
fun parseInput(input: String): Pair<Cargo, List<Movement>> {
    val splitted = input.split("\n\n");
    return Pair(
        parseCargo(splitted[0]),
        parseMovements(splitted[1])
    )
}

private fun parseCargo(input: String): Cargo {
    val lines = input.split("\n").toMutableList()
    val stackCount = lines.removeLast().toCharArray().last().toString().toInt()

    val stacks = lines
        .toList()
        .map {
            it.replace("    ", "@")
                .replace(" ", "")
                .replace("[", "")
                .replace("]", "")
        }
    return Cargo(mapStacks(stacks, stackCount))
}

private fun mapStacks(stacks: List<String>, stackCount: Int): MutableList<Stack> {
    val toTranspose = stacks.map { mapStack(it, stackCount) }
        .toMutableList()
    val transposed = MutableList(stackCount) { Stack(emptyList<Item>().toMutableList()) }
    return transpose(transposed, toTranspose)
}

fun transpose(target: MutableList<Stack>, source: MutableList<Stack>): MutableList<Stack> {
    if (source.isEmpty()) {
        return target
    }

    val processing = source.removeLast()

    val index = AtomicInteger(0)
    processing.items.forEach {
        val targetIndex = index.getAndIncrement()
        if (!it.isEmpty()) {
            target[targetIndex].add(listOf(it))
        }
    }

    return transpose(target, source)
}

private fun mapStack(stack: String, stackCount: Int): Stack { // not correct yet
    val parsed = stack.toCharArray()
        .map { mapItem(it) }
        .toMutableList()
    while (parsed.size < stackCount) {
        parsed.add(Item(null))
    }
    return Stack(parsed)
}

private fun mapItem(char: Char): Item {
    return when(char) {
        '@' -> Item(null)
        else -> Item(char)
    }
}

private fun parseMovements(input: String): List<Movement> {
    val movementFormat = "move (\\d+) from (\\d+) to (\\d+)"
    return input.split("\n")
        .map {
            val match = movementFormat.toRegex().find(it)!!
            val (count, from, to) = match.destructured
            return@map Movement(count.toInt(), from.toInt() - 1, to.toInt() - 1)
        }
}

// Data
data class Cargo(val stacks: MutableList<Stack>){
    fun process(movements: List<Movement>): Cargo {
        if(movements.isEmpty()) {
            return this
        }
        val toProcess = movements.toMutableList()
        val current = toProcess.removeFirst()
        return process(current).process(toProcess)
    }

    fun process(movement: Movement): Cargo {
        val movingItems = stacks[movement.from].take(movement.count).reversed()
        stacks[movement.to].add(movingItems)

        return this
    }

    fun process9001(movements: List<Movement>): Cargo {
        if(movements.isEmpty()) {
            return this
        }
        val toProcess = movements.toMutableList()
        val current = toProcess.removeFirst()
        return process9001(current).process9001(toProcess)
    }

    fun process9001(movement: Movement): Cargo {
        val movingItems = stacks[movement.from].take(movement.count)
        stacks[movement.to].add(movingItems)

        return this
    }

    fun top(): String {
        return stacks.map { it.first() }
            .map { it.char!! }
            .joinToString(separator = "")
    }
}

data class Stack(val items: MutableList<Item>) {
    fun take(count: Int): List<Item> {
        val take = items.filter { !it.isEmpty() }
            .take(count)
        take.forEach { items.remove(it) }
        return take
    }

    fun add(itemsToAdd: List<Item>) {
        this.items.addAll(0, itemsToAdd)
    }

    fun first(): Item {
        return items.first()
    }
}

data class Item(val char: Char?) {
    fun isEmpty(): Boolean {
        return char == null
    }
}

data class Movement(val count: Int, val from: Int, val to: Int) {}
