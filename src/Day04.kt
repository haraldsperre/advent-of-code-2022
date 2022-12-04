fun main() {

    fun getElfRanges(pair: String): Pair<IntRange, IntRange> {
        return pair
            .split(",")
            .map { elf ->
                val (low, high) = elf.split("-").map { it.toInt() }
                low..high
            }
            .let { it[0] to it[1] }
    }

    fun part1(input: List<String>): Int {
        return input
            .count { pair ->
                val (first, second) = getElfRanges(pair)
                val overlap = first.intersect(second)
                overlap == first.toSet() || overlap == second.toSet()
            }
    }

    fun part2(input: List<String>): Int {
        return input
            .count{ pair ->
                val (first, second) = getElfRanges(pair)
                first.intersect(second).isNotEmpty()
            }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
