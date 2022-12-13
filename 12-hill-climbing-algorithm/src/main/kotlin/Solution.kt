typealias Coordinate = Pair<Int, Int>

val Coordinate.x get() = first
val Coordinate.y get() = second

operator fun Coordinate.plus(other: Coordinate): Coordinate {
    return Coordinate(
        this.x + other.x,
        this.y + other.y
    )
}

fun hillClimb(input: String): Int {
    return parseInput(input).bfs().count() - 1
}

fun hillClimb2(input: String): Int = parseInput(input).let { grid ->
    grid.nodes
        .flatMap { it.filter { node -> node.elev == 'a' } }
        .map { grid.copy(start = it).bfs().count() - 1 }
        .filter { it != 0 }.minOf { it }
}

sealed class Node(open val coordinate: Coordinate, open val elev: Char) {
    data class Start(
        override val coordinate: Coordinate,
        override val elev: Char = 'a'
    ) :
        Node(coordinate, elev)

    data class End(
        override val coordinate: Coordinate,
        override val elev: Char = 'z'
    ) :
        Node(coordinate, elev)

    data class Other(
        override val coordinate: Coordinate,
        override val elev: Char
    ) :
        Node(coordinate, elev)

    fun isValid(other: Node) = this.elev + 1 >= other.elev
}

data class Grid(val start: Node, val end: Node, val nodes: List<List<Node>>)

fun parseInput(input: String): Grid = input.split("\n")
    .mapIndexed { y, row ->
        row.mapIndexed { x, char ->
            when (char) {
                'S' -> Node.Start(x to y)
                'E' -> Node.End(x to y)
                else -> Node.Other(x to y, elev = char)
            }
        }
    }.fold(Grid(Node.Start(-1 to -1), Node.End(-1 to -1), emptyList())) { acc: Grid, nodes: List<Node> ->
        val start: Node.Start = (nodes.firstOrNull { it is Node.Start } ?: acc.start) as Node.Start
        val end: Node.End = (nodes.firstOrNull { it is Node.End } ?: acc.end) as Node.End
        acc.copy(start = start, end = end, nodes = acc.nodes + listOf(nodes))
    }

enum class Direction(val mvmnt: Coordinate) {
    UP(-1 to 0),
    DOWN(1 to 0),
    LEFT(0 to -1),
    RIGHT(0 to 1)
}

fun Grid.bfs(): List<Node> {
    val rows = nodes.count()
    val cols = nodes.first().count()

    val path = mutableMapOf<Node, Node>()
    val queue = mutableListOf<Node>()
    val visited = mutableMapOf<Node, Boolean>()
    queue.add(start)
    visited[start] = true

    while (queue.size > 0) {
        val curr = queue.removeLast()
        if (curr == end) break
        Direction.values().mapNotNull { direction ->
            val newPos = curr.coordinate + direction.mvmnt
            when {
                newPos.x < 0 || newPos.y < 0 -> null
                newPos.y >= rows || newPos.x >= cols -> null
                else -> nodes[newPos.y][newPos.x]
            }
        }.filter { current ->
            !visited.getOrDefault(current, false)
        }.filter { node ->
            curr.isValid(node)
        }.forEach { next ->
            queue.add(0, next)
            path[next] = curr
            visited[next] = true
        }
    }

    return path.regeneratePath(end)
}

fun Map<Node, Node>.regeneratePath(tail: Node): List<Node> {
    return when (val next = this[tail]) {
        null -> return listOf<Node>() + tail
        else -> regeneratePath(next) + tail
    }
}
