
fun fileSystem1(input: String, maxSize: Int): Int {
    val commands = parseCommands(input)
    val fileSystem = parseFs(commands)

    return fileSystem.directories
        .filter { it.size() <= maxSize }
        .sumOf { it.size() }
}

fun fileSystem2(input: String, updateSize: Int): Int {
    val commands = parseCommands(input)
    val fileSystem = parseFs(commands)

    val freeSpace = 70000000 - (fileSystem.root?.size() ?: 0)

    return fileSystem.directories
        .map { it.size() }
        .filter { it >= updateSize - freeSpace }
        .minOf { it }
}

fun parseCommands(input: String): List<Command> {
    return input.split("$")
        .filter { it.isNotBlank() }
        .map {
            val terminalLines = it.trim()
            .split("\n")
                .toMutableList()
            val command = terminalLines.removeFirst()
                .split(" ")
            return@map if (command.size == 1){
                Command(command[0], output = terminalLines)
            } else {
                Command(
                    command[0],
                    command[1],
                    terminalLines
                )
            }
        }
}

fun parseFs(commands: List<Command>): FileSystem {
    val fs = FileSystem()

    commands.forEach {
        when(it.command) {
            "cd" -> fs.cd(it.args!!)
            "ls" -> fs.ls(it.output)
        }
    }

    return fs
}

interface FsItem {
    fun size(): Int
}

data class FileSystem(var root: Directory? = null, var current: Directory? = root): FsItem {
    val directories: MutableList<Directory> = emptyList<Directory>().toMutableList()
    fun cd(dir: String) {
        when (dir) {
            "/" -> {
                if (root == null) {
                    root = Directory(dir, null, emptyMap<String, FsItem>().toMutableMap())
                    directories.add(root!!)
                }
                current = root
            }
            ".." -> current = current?.parent
            else -> current = current?.content?.get(dir) as Directory
        }
    }

    fun ls(contents: List<String>) {
        contents.map {
            it.split(" ")
        }.forEach {
            when(it[0]) {
                "dir" -> {
                    val value = Directory(it[1], current)
                    current?.content?.put(it[1], value)
                    directories.add(value)
                }
                else -> current?.content?.put(it[1], File(it[1], it[0].toInt()))
            }
        }
    }

    override fun size(): Int {
        return root!!.size()
    }
}

data class Command(val command: String, val args: String? = null, val output: List<String>)

data class Directory(val name: String, val parent: Directory? = null, val content: MutableMap<String, FsItem> = emptyMap<String, FsItem>().toMutableMap()): FsItem {

    override fun size(): Int {
        return content.values.sumOf { it.size() }
    }
}
data class File(val name: String, val size: Int): FsItem {
    override fun size(): Int {
        return size
    }
}
