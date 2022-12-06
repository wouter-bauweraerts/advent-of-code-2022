import java.util.concurrent.atomic.AtomicInteger

fun startOfPacketMarker(message: String): Int {
    val index = AtomicInteger(4)

    val windows = message.toCharArray()
        .toList()
        .windowed(4)

    for (window in windows) {
        if (window.toSet().size == 4) {
            break
        }
        index.incrementAndGet()
    }
    return index.getAndIncrement()
}
