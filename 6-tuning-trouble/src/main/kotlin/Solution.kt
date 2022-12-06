import java.util.concurrent.atomic.AtomicInteger

fun startOfPacketMarker(message: String): Int {
    return distinctWindow(message, 4)
}

fun startOfMessageMarker(message: String): Int {
    return distinctWindow(message, 14)
}

private fun distinctWindow(input: String, window: Int): Int {
    val marker = AtomicInteger(window)

    val windows = input.toCharArray()
        .toList()
        .windowed(window)

    for (w in windows) {
        if (w.toSet().size == window) {
            break
        }
        marker.incrementAndGet()
    }
    return marker.getAndIncrement()
}
