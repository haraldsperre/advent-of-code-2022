fun main() {
    fun part1(input: List<String>): Int {
        var max = 0
        var curr = 0

        for (foodItem in input) {
            if (foodItem == "") {
                if (curr > max) {
                    max = curr
                }
                curr = 0
            } else {
                curr += foodItem.toInt()
            }
        }
        return max
    }

    fun part2(input: List<String>): Int {
        val caloriesCarried: MutableList<Int> = mutableListOf()
        var curr = 0

        for (foodItem in input) {
            if (foodItem == "") {
                caloriesCarried.add(curr)
                curr = 0
            } else {
                curr += foodItem.toInt()
            }
        }
        if (curr != 0) caloriesCarried.add(curr)
        caloriesCarried.sortDescending()

        return caloriesCarried.subList(0, 3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
