fun main() {
    fun getScore(round: Pair<Char, Char>): Int {
        val (opponent, you) = round
        return when (you) {
            'X' -> 1 + when (opponent) {
                'A' -> 3
                'B' -> 0
                else -> 6
            }
            'Y' -> 2 + when (opponent) {
                'A' -> 6
                'B' -> 3
                else -> 0
            }
            else -> 3 + when (opponent) {
                'A' -> 0
                'B' -> 6
                else -> 3
            }
        }
    }

    fun getScoreByStrategy(round: Pair<Char, Char>): Int {
        val (opponent, you) = round
        return when (you) {
            'X' -> when (opponent) {
                'A' -> getScore('A' to 'Z')
                'B' -> getScore('B' to 'X')
                else -> getScore('C' to 'Y')
            }
            'Y' -> when (opponent) {
                'A' -> getScore('A' to 'X')
                'B' -> getScore('B' to 'Y')
                else -> getScore('C' to 'Z')
            }
            else -> when (opponent) {
                'A' -> getScore('A' to 'Y')
                'B' -> getScore('B' to 'Z')
                else -> getScore('C' to 'X')
            }
        }
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { getScore(it[0] to it[2]) }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { getScoreByStrategy(it[0] to it[2]) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
