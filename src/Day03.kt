fun main() {

    fun getPriority(input: Char): Int {
        return when (input) {
            in 'a'..'z' -> input - 'a' + 1
            in 'A'..'Z' -> input - 'A' + 27
            else -> throw Exception("Invalid input")
        }
    }

    fun getCompartments(rucksack: String): Pair<String, String> {
        val compartmentSize = rucksack.length / 2
        return rucksack.take(compartmentSize) to rucksack.takeLast(compartmentSize)
    }

    fun findMatching(rucksack: String): Set<Char> {
        val (left, right) = getCompartments(rucksack)
        return left.filter { item ->
            right.contains(item)
        }.toSet()
    }

    fun findBadge(elves: List<String>): Set<Char> {
        return elves.map { elf -> elf.toSet() }
            .reduce { common, rucksack -> common.intersect(rucksack) }
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { rucksack ->
            findMatching(rucksack).sumOf { getPriority(it) }
        }
    }

    fun part2(input: List<String>): Int {
        return input.chunked(3)
            .sumOf { elves ->
                findBadge(elves).sumOf { getPriority(it) }
            }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
