fun main() {

    fun getFirstDistinctSubSequence(input: String, length: Int): Int {
        for (i in length..input.length) {
            if (input.subSequence(i - length, i).toSet().size == length) {
                return i
            }
        }
        return -1
    }

    fun part1(input: String): Int {
        return getFirstDistinctSubSequence(input, 4)
    }

    fun part2(input: String): Int {
        return getFirstDistinctSubSequence(input, 14)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test").first()
    check(part1(testInput) == 5)
    check(part2(testInput) == 23)

    val input = readInput("Day06").first()
    println(part1(input))
    println(part2(input))
}
