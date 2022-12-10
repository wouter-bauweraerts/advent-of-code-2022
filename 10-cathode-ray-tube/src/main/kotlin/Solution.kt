import java.util.concurrent.atomic.AtomicInteger

fun draw(input: String): String {
    return execute(input).chunked(40)
        .joinToString(separator = "\n") { drawRow(it) }
}

fun drawRow(sprites: List<Pair<Int, Int>>): String {
    return sprites.map { it.second }
        .map { it - 1..it + 1 }
        .mapIndexed { crt, sprite ->
            when (sprite.contains(crt)) {
                true -> "#"
                false -> "."
            }
        }.joinToString(separator = "")
}

fun summateSignalStrength(input: String, cycles: List<Int>): Int {
    return execute(input).filter { cycles.contains(it.first) }
        .sumOf { (cycle, register) -> cycle * register }
}

fun execute(input: String): List<Pair<Int, Int>> {
    val xRegister = AtomicInteger(1)
    val cycleCount = AtomicInteger(1)

    return input.split("\n")
        .flatMap { command ->
            when (command.trim()) {
                "noop" -> listOf(Pair(cycleCount.getAndIncrement(), xRegister.get()))
                else -> {
                    listOf(
                        Pair(cycleCount.getAndIncrement(), xRegister.get()),
                        Pair(cycleCount.getAndIncrement(), xRegister.getAndAdd(
                            command.trim().substringAfter(" ").toInt()
                        ))
                    )
                }
            }
        }
}