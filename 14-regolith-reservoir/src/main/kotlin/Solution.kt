import kotlin.math.max
import kotlin.math.min

typealias Coordinate = Pair<Int, Int>

val Coordinate.x get() = first
val Coordinate.y get() = second


fun String.toCoordinate(): Coordinate = split(",").let { Coordinate(it[0].toInt(), it[1].toInt()) }

operator fun Coordinate.plus(other: Coordinate): Coordinate = Coordinate(this.x + other.x, this.y + other.y)
fun Coordinate.isLower(other: Coordinate): Boolean = this.y > other.y
fun Coordinate.lowest(other: Coordinate): Coordinate = when(this.isLower(other)) {
    true -> this
    else -> other
}

enum class Direction(val to: Coordinate) {
    DOWN(0 to 1),
    LEFT(-1 to 1),
    RIGHT(1 to 1)
}

val origin = 500 to 0

fun part1(input: String): Int {
    val cave = parse(input)
    val rocks = cave.toSet()

    val xRange =  cave.minOf { it.x } .. cave.maxOf { it.x }
    val yRange = 0 .. cave.maxOf { it.y }

    var dropsInRange = true
    do{
        val dropLocation = drop(cave)
        if (!inRange(dropLocation, xRange, yRange)) {
            dropsInRange = false
        } else {
            cave.add(dropLocation)
        }
    } while (dropsInRange)
    return cave.subtract(rocks).count()
}

fun part2(input: String): Int {
    val cave = parse(input)

    val xRange =  min(0, cave.minOf { it.x }) .. max(cave.maxOf { it.x }, 1000)
    val yRange = 0 .. (cave.maxOf { it.y } + 2)

    cave.addAll(xRange.map { it to yRange.last })

    val rocks = cave.toSet()
    do{
        val dropLocation = drop(cave)
        if (inRange(dropLocation, xRange, yRange)) {
            cave.add(dropLocation)
        }
    } while (!cave.contains(origin))
    return cave.subtract(rocks).count()
}

fun drop(cave: Set<Coordinate>): Coordinate {
    val xRange =  cave.minOf { it.x } .. cave.maxOf { it.x }
    val yRange = 0 .. cave.maxOf { it.y }

    var sand = origin.copy()

    do {
        when {
            isFree(cave, sand + Direction.DOWN.to) -> sand += Direction.DOWN.to
            isFree(cave, sand + Direction.LEFT.to) -> sand += Direction.LEFT.to
            isFree(cave, sand + Direction.RIGHT.to) -> sand += Direction.RIGHT.to
            else -> break
        }
    } while (inRange(sand, xRange, yRange))

    return sand
}

fun isFree(cave: Set<Coordinate>, coordinate: Coordinate): Boolean = !cave.contains(coordinate)

fun inRange(coordinate: Coordinate, xRange: IntRange, yRange: IntRange) = xRange.contains(coordinate.x) && yRange.contains(coordinate.y)

fun parse(input: String): MutableSet<Coordinate> {
    return input.split("\n")
        .map { line ->
            line.split(" -> ")
                .map { it.toCoordinate() }
        }.fold(mutableSetOf()) { acc: MutableSet<Coordinate>, coordinates: List<Coordinate> ->
            coordinates.windowed(2, 1) { (start, end) ->
                val rocks = mutableSetOf<Coordinate>()
                rocks.addAll(listOf(start, end))

                rocks.addAll(
                    (min(start.x, end.x)..max(start.x, end.x)).flatMap { x ->
                        (min(start.y, end.y)..max(start.y, end.y)).map { y -> x to y }
                    }
                )

                acc.addAll(rocks)
            }
            return@fold acc
        }
}
