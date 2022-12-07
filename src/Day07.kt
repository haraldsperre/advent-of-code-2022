abstract class FilesystemEntity(val name: String, val parent: Directory? = null, var size: Long = 0L)

class Directory(name: String, parent: Directory? = null) : FilesystemEntity(name, parent) {
    private val children = mutableListOf<FilesystemEntity>()

    fun add(entity: FilesystemEntity) {
        children.add(entity)
    }

    fun getChildren() = children.toList()

    fun getChild(name: String) = children.find { it.name == name }
}

class File(name: String, parent: Directory, size: Long) : FilesystemEntity(name, parent, size)

fun getEntityFromLine(line: String, currentDir: Directory): FilesystemEntity {
    val (info, name) = line.split(" ")
    return try {
        File(name, currentDir, info.toLong())
    } catch (e: NumberFormatException) {
        Directory(name, currentDir)
    }
}

fun buildFilesystemTree(input: List<String>): Directory {
    val root = Directory("/")
    var currentDir = root
    for (line in input) {
        if (line == "$ ls") {
            continue
        } else if (line.startsWith("$ cd")) {
            val name = line.removePrefix("$ cd ")
            currentDir = when (name) {
                ".." -> currentDir.parent ?: currentDir
                "/" -> root
                else -> currentDir.getChild(name) as Directory
            }
        } else {
            val entity = getEntityFromLine(line, currentDir)
            if (currentDir.getChild(entity.name) == null) currentDir.add(entity)
        }
    }
    return root
}

fun getTopologicalOrdering(root: Directory): List<FilesystemEntity> {
    val sorted = mutableListOf<FilesystemEntity>()
    val visited = mutableSetOf<FilesystemEntity>()
    fun dfs(entity: FilesystemEntity) {
        if (entity in visited) return
        visited.add(entity)
        if (entity is Directory) {
            entity.getChildren().forEach { dfs(it) }
        }
        sorted.add(entity)
    }
    dfs(root)
    return sorted
}

fun getDirectoriesWithTotalSize(input: List<String>): List<Directory> {
    val root = buildFilesystemTree(input)
    val sorted = getTopologicalOrdering(root)
    for (entity in sorted) {
        entity.parent?.size = entity.parent?.size?.plus(entity.size) ?: 0L
    }
    return sorted.filterIsInstance<Directory>()
}
fun main() {

    fun part1(input: List<Directory>): Long {
        return input
            .sumOf { directory ->
                if (directory.size <= 100000L) directory.size else 0L
            }
    }

    fun part2(input: List<Directory>): Long {
        val totalSpace = 70000000L
        val availableSpace = totalSpace - input.find { it.name == "/" }!!.size
        val neededSpace = 30000000L - availableSpace

        return input
            .filter { it.size > neededSpace }
            .minOf { it.size }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = getDirectoriesWithTotalSize(readInput("Day07_test"))
    check(part1(testInput) == 95437L)
    check(part2(testInput) == 24933642L)

    val input = getDirectoriesWithTotalSize(readInput("Day07"))
    println(part1(input))
    println(part2(input))
}
