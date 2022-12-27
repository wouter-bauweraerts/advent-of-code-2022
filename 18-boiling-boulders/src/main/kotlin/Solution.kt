typealias IntTriple = Triple<Int, Int, Int>

private fun IntTriple.neighbours() = listOf(
    copy(first = first - 1),
    copy(first = first + 1),
    copy(second = second - 1),
    copy(second = second + 1),
    copy(third = third - 1),
    copy(third = third + 1),
)

fun boilingBoulders1(input: String): Int {
    val droplets = input.split("\n")
        .map { line ->
            val (x, y, z) = "(\\d+),(\\d+),(\\d+)".toRegex().matchEntire(line)!!.destructured
            IntTriple(x.toInt(), y.toInt(), z.toInt())
        }
    return droplets.sumOf { droplet ->
        droplet.neighbours().count { it !in droplets }
    }
}

fun boilingBoulders2(input: String): Int {
    val droplets = input.split("\n")
        .map { line ->
            val (x, y, z) = "(\\d+),(\\d+),(\\d+)".toRegex().matchEntire(line)!!.destructured
            IntTriple(x.toInt(), y.toInt(), z.toInt())
        }

    var minX = Int.MAX_VALUE
    var maxX = Int.MIN_VALUE
    var minY = Int.MAX_VALUE
    var maxY = Int.MIN_VALUE
    var minZ = Int.MAX_VALUE
    var maxZ = Int.MIN_VALUE
    for ((x, y, z) in droplets) {
        if (x < minX) minX = x
        if (x > maxX) maxX = x
        if (y < minY) minY = y
        if (y > maxY) maxY = y
        if (z < minZ) minZ = z
        if (z > maxZ) maxZ = z
    }
    val outside = buildSet {
        val queue = mutableListOf(IntTriple(minX - 1, minY - 1, minZ - 1).also { add(it) })
        while (queue.isNotEmpty()) {
            for (neighbor in queue.removeLast().neighbours()) {
                neighbor.first in minX - 1..maxX + 1 &&
                        neighbor.second in minY - 1..maxY + 1 &&
                        neighbor.third in minZ - 1..maxZ + 1 &&
                        neighbor !in droplets &&
                        add(neighbor) &&
                        queue.add(neighbor)
            }
        }
    }
    return droplets.sumOf { droplet -> droplet.neighbours().count { it in outside } }
}
