fun main() {
    fun getCollections(input: List<String>): List<Int> {
        var curr = 0
        val result = mutableListOf<Int>()
        for (i in input) {
            if (i != "") {
                curr += i.toInt()
            } else {
                result.add(curr)
                curr = 0
            }
        }
        if (curr != 0) result.add(curr)
        return result
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
