fun startOfPacketMarker(message: String): Int {
    return distinctWindow(message, 4)
}

fun startOfMessageMarker(message: String): Int {
    return distinctWindow(message, 14)
}

private fun distinctWindow(input: String, window: Int): Int {
    return input.indexOf(
        input.toCharArray()
            .toList()
            .windowed(window)
            .map { it.toSet() }
            .first { it.size == window }
            .joinToString(separator = "")
    ) + window
}
