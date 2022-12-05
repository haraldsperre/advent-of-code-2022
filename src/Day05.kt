import java.util.Stack

fun main() {
    fun createStacks(input: List<String>): List<Stack<Char>> {
        val stacks = mutableListOf<Stack<Char>>()
        repeat(input.last().split(Regex("\\s+")).size-1) { _ ->
            stacks.add(Stack<Char>())
        }

        for (line in input.reversed().listIterator(1)) {
            line
                .replace("    ", " ")
                .split(" ")
                .forEachIndexed { index, card ->
                    if (card.isNotEmpty()) stacks.get(index = index).push(card[1])
                }
        }
        return stacks
    }

    fun getInstruction(instruction: String): Triple<Int, Int, Int> {
        val pattern = Regex("move (\\d+) from (\\d+) to (\\d+)")
        val (number, from, to) = pattern.find(instruction)!!.destructured
        return Triple(number.toInt(), from.toInt(), to.toInt())
    }

    fun part1(input: List<String>): String {
        val (stackDescription, instructions) = input.chunkedBy(String::isEmpty)
        val stacks = createStacks(stackDescription)
        for (instruction in instructions) {
            val (number, from, to) = getInstruction(instruction)
            repeat(number) { stacks[to - 1].push(stacks[from - 1].pop()) }
        }
        return stacks
            .map { it.pop() }
            .joinToString("")
    }

    fun part2(input: List<String>): String {
        val (stackDescription, instructions) = input.chunkedBy(String::isEmpty)
        val stacks = createStacks(stackDescription)
        for (instruction in instructions) {
            val (number, from, to) = getInstruction(instruction)
            val moved = ArrayDeque<Char>()
            repeat(number) { moved.addFirst(stacks[from - 1].pop()) }
            repeat(number) { stacks[to - 1].push(moved.removeFirst()) }
        }
        return stacks
            .map { it.pop() }
            .joinToString("")
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
