import be.thebeehive.wouterbauweraerts.common.readFromFile

fun main() {
    println(ropeBridge(readFromFile("input.txt")))
    println(ropeBridge(readFromFile("input.txt"), 10))
}
