fun sumOfCorrectPairIndices(input: String): Int {
    return input.split("\n")
        .asSequence()
        .filter { it.isNotBlank() }
        .map { Packet.of(it) }
        .chunked(2)
        .mapIndexed { i, pair ->
            return@mapIndexed if (pair[0] < pair[1]) i + 1 else 0
        }.sum()
}

fun decoderKey(input: String): Int {
    val packets = input.split("\n")
        .asSequence()
        .filter { it.isNotBlank() }
        .map { Packet.of(it) }

    val dividerPacket1 = Packet.of("[[2]]")
    val dividerPacket2 = Packet.of("[[6]]")
    val ordered = (packets + dividerPacket1 + dividerPacket2).sorted()
    return (ordered.indexOf(dividerPacket1) + 1) * (ordered.indexOf(dividerPacket2) + 1)
}

sealed class Packet : Comparable<Packet> {
    companion object {
        fun of(input: String): Packet =
            of(
                input.split("""((?<=[\[\],])|(?=[\[\],]))""".toRegex())
                    .filter { it.isNotBlank() }
                    .filter { it != "," }
                    .iterator()
            )

        private fun of(input: Iterator<String>): Packet {
            val packets = mutableListOf<Packet>()
            while (input.hasNext()) {
                when (val symbol = input.next()) {
                    "]" -> return ListPacket(packets)
                    "[" -> packets.add(of(input))
                    else -> packets.add(SinglePacket(symbol.toInt()))
                }
            }
            return ListPacket(packets)
        }
    }
}

private class SinglePacket(val amount: Int) : Packet() {
    fun asList(): Packet = ListPacket(listOf(this))

    override fun compareTo(other: Packet): Int =
        when (other) {
            is SinglePacket -> amount.compareTo(other.amount)
            is ListPacket -> asList().compareTo(other)
        }
}

private class ListPacket(val subPackets: List<Packet>) : Packet() {
    override fun compareTo(other: Packet): Int =
        when (other) {
            is SinglePacket -> compareTo(other.asList())
            is ListPacket -> subPackets.zip(other.subPackets)
                .map { it.first.compareTo(it.second) }
                .firstOrNull { it != 0 } ?: subPackets.size.compareTo(other.subPackets.size)
        }
}
