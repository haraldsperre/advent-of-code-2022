fun main() {

    fun isVisible(patch: List<List<Int>>, tree: Pair<Int, Int>): Boolean {
        val (x, y) = tree
        if (x == 0 || y == 0 || x == patch.size - 1 || y == patch[x].size - 1) {
            return true
        }
        val treeHeight = patch[x][y]
        return treeHeight > listOf(
            patch.subList(0, x).maxOf { it[y] },
            patch.subList(x + 1, patch.size).maxOf { it[y] },
            patch[x].subList(0, y).max(),
            patch[x].subList(y + 1, patch.size).max()
        ).min()
    }

    fun getScenicScore(patch: List<List<Int>>, tree: Pair<Int, Int>): Int {
        val (x, y) = tree
        val treeHeight = patch[x][y]
        var score = 1
        var tempScore = 0
        if (x == 0 || y == 0 || x == patch.size - 1 || y == patch[x].size - 1) {
            return 0
        }
        for (xi in (0 until x).reversed()) {
             tempScore++
            if (patch[xi][y] >= treeHeight) break
        }
        score *= tempScore
        tempScore = 0
        for (xi in (x + 1) until patch.size) {
            tempScore++
            if (patch[xi][y] >= treeHeight) break
        }
        score *= tempScore
        tempScore = 0
        for (yi in (0 until y).reversed()) {
            tempScore++
            if (patch[x][yi] >= treeHeight) break
        }
        score *= tempScore
        tempScore = 0
        for (yi in (y + 1) until patch[x].size) {
            tempScore++
            if (patch[x][yi] >= treeHeight) break
        }
        score *= tempScore
        return score
    }

    fun part1(input: List<List<Int>>): Int {
        val value = List(input.size) { y ->
            List(input[y].size) { x ->
                isVisible(input, x to y)
            }
        }
        return value.sumOf { row -> row.count { it } }
    }

    fun part2(input: List<List<Int>>): Int {
        val value = List(input.size) { y ->
            List(input[y].size) { x ->
                getScenicScore(input, x to y)
            }
        }
        return value.maxOf{ it.maxOf{ it } }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsIntLists("Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInputAsIntLists("Day08")
    println(part1(input))
    println(part2(input))
}
