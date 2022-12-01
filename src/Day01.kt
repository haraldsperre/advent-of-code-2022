fun main() {
    fun getCollections(input: List<String>): List<Int> {
        return input
            .chunkedBy { it.isEmpty() }
            .map { elf ->
                elf.sumOf { it.toInt() }
            }
    }

    fun part1(input: List<String>): Int {
        return getCollections(input).maxOrNull() ?: 0
    }

    fun part2(input: List<String>): Int {
        val caloriesCarried = getCollections(input)

        return caloriesCarried.sortedDescending().subList(0, 3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
