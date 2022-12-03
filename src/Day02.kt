enum class Hand(val value: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3)
}

fun Hand.beats(): Hand {
    return when (this) {
        Hand.ROCK -> Hand.SCISSORS
        Hand.PAPER -> Hand.ROCK
        Hand.SCISSORS -> Hand.PAPER
    }
}

fun Hand.beatenBy(): Hand {
    return when (this) {
        Hand.ROCK -> Hand.PAPER
        Hand.PAPER -> Hand.SCISSORS
        Hand.SCISSORS -> Hand.ROCK
    }
}

fun getHand(input: Char): Hand {
    return when (input) {
        'A', 'X' -> Hand.ROCK
        'B', 'Y' -> Hand.PAPER
        'C', 'Z' -> Hand.SCISSORS
        else -> throw Exception("Invalid input")
    }
}

fun scoreMatch(opponent: Hand, you: Hand): Int {
    return when (opponent) {
        you.beats() -> 6
        you.beatenBy() -> 0
        else -> 3
    }
}

fun main() {

    fun part1(input: List<String>): Int {
        return input.sumOf { match ->
            val (opponent, you) = (getHand(match[0]) to getHand(match[2]))
            scoreMatch(opponent, you) + you.value
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { match ->
            val opponent = getHand(match[0])
            val you = when (match[2]) {
                'X' -> opponent.beats()
                'Y' -> opponent
                'Z' -> opponent.beatenBy()
                else -> throw Exception("Invalid input")
            }
            scoreMatch(opponent, you) + you.value
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
