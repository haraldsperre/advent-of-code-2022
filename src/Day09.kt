import kotlin.math.abs
import kotlin.math.sign

fun main() {

    fun moveHead(position: Pair<Int, Int>, direction: Char): Pair<Int, Int> = when (direction) {
        'U' -> position.copy(first = position.first + 1)
        'D' -> position.copy(first = position.first - 1)
        'L' -> position.copy(second = position.second - 1)
        'R' -> position.copy(second = position.second + 1)
        else -> throw IllegalArgumentException("Invalid direction: $direction")
    }

    fun moveTail(head: Pair<Int, Int>, tail: Pair<Int, Int>): Pair<Int, Int> {
        val horizontalDistance = head.second - tail.second
        val verticalDistance = head.first - tail.first
        return if (abs(horizontalDistance) <= 1 && abs(verticalDistance) <= 1) {
            tail
        } else {
            tail.copy(
                first = tail.first + verticalDistance.sign,
                second = tail.second + horizontalDistance.sign
            )
        }
    }

    fun getVisitedByTail(
        headMovements: List<String>,
        ropeLength: Int
    ): MutableSet<Pair<Int, Int>> {
        val visited = mutableSetOf<Pair<Int, Int>>()
        val knots = MutableList(ropeLength) { 0 to 0 }
        headMovements.forEach { line ->
            val (direction, steps) = line.split(" ")
            repeat(steps.toInt()) {
                knots[0] = moveHead(knots[0], direction[0])
                for (knotIndex in knots.indices.drop(1)) {
                    knots[knotIndex] = moveTail(knots[knotIndex - 1], knots[knotIndex])
                }
                visited += knots.last()
            }
        }
        return visited
    }

    fun part1(input: List<String>): Int {
        return getVisitedByTail(input, 2).size
    }

    fun part2(input: List<String>): Int {
        return getVisitedByTail(input, 10).size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 1)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
