
fun supplyStack(input: String): String {
    val parsedInput = parseInput(input);
    return parsedInput.first.process(parsedInput.second)
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
            it.replace("    ", "empty ")
                .replace("[", "")
                .replace("]", "")
        }
    return Cargo(mapStacks(stacks, stackCount))
}

private fun mapStacks(stacks: List<String>, stackCount: Int): List<Stack> {
    return stacks.map { mapStack(it, stackCount) }
}

private fun mapStack(stack: String, stackCount: Int): Stack {
    val parsed = stack.split(" ")
        .map { mapItem(it) }
        .toMutableList()
    while (parsed.size < stackCount) {
        parsed.add(Item(null))
    }
    return Stack(parsed.toList())
}

private fun mapItem(string: String): Item {
    return when(string) {
        "empty" -> Item(null)
        else -> Item(string.toCharArray()[0])
    }
}

private fun parseMovements(input: String): List<Movement> {
    val movementFormat = "move (\\d+) from (\\d+) to (\\d+)"
    return input.split("\n")
        .map {
            val match = movementFormat.toRegex().find(it)!!
            val (count, from, to) = match.destructured
            return@map Movement(count.toInt(), from.toInt(), to.toInt())
        }
}

// Data
data class Cargo(val stacks: List<Stack>){
    fun process(movements: List<Movement>): Cargo {
        return Cargo(emptyList())
    }

    fun top(): String {
        return ""
    }
}

data class Stack(val items: List<Item>) {}

data class Item(val char: Char?) {
    fun isEmpty(): Boolean {
        return char == null
    }
}

data class Movement(val count: Int, val from: Int, val to: Int) {}
