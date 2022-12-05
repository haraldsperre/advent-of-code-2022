typealias CrateStack = ArrayDeque<Char>

fun CrateStack.pop(): Char = removeLast()
fun CrateStack.pop(count: Int): CrateStack {
    val a = CrateStack()
    repeat(count) { a.addFirst(pop()) }
    return a
}

fun CrateStack.push(crate: Char) = addLast(crate)
fun CrateStack.push(crates: CrateStack) {
    while (crates.isNotEmpty()) push(crates.removeFirst())
}

fun main() {
    fun createStacks(stackVisualization: List<String>): List<CrateStack> {
        val stacks = stackVisualization
            .last()
            .chunked(4)
            .map { ArrayDeque<Char>() }

        for (line in stackVisualization.reversed().listIterator(1)) {
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
            repeat(number) {
                stacks[to - 1].push(stacks[from - 1].pop())
            }
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
            stacks[to - 1].push(stacks[from - 1].pop(number))
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
