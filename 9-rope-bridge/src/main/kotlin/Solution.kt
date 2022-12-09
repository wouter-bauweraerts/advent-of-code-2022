import kotlin.math.abs
import kotlin.math.sign

enum class Direction(val movement: Point) {
    U(Point(1,0)),
    R(Point(0,1)),
    D(Point(-1,0)),
    L(Point(0,-1)),
}

fun ropeBridge(input: String, numberOfKnots: Int = 2): Int {
    val motions = input.split("\n")
        .map { it.substringBefore(" ") to it.substringAfter(" ").toInt() }

    val knots = MutableList(numberOfKnots) { Point(0, 0) }
    val visited = mutableSetOf<Point>()

    motions.forEach { (dir, steps) ->
        repeat(steps) {
            knots[0] = knots[0] + Direction.valueOf(dir).movement

            knots.indices.windowed(2) { (head, tail) ->
                if (!knots[tail].isAdjacent(knots[head])) {
                    knots[tail] = knots[tail].moveTo(knots[head])
                }
            }

            visited.add(knots.last())
        }
    }

    return visited.size
}

data class Point(
    val row: Int,
    val col: Int
) {
    operator fun plus(other: Point): Point {
        return Point(row + other.row, col + other.col)
    }

    fun isAdjacent(other: Point): Boolean {
        return abs(other.row - row) < 2 && abs(other.col - col) < 2
    }

    fun moveTo(other: Point): Point {
        return this + Point((other.row - row).sign, (other.col - col).sign)
    }
}
