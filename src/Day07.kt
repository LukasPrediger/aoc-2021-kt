import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val startingPositions = input.first().splitCsv()

        return Array(startingPositions.maxOf { it }) { goal -> startingPositions.sumOf { abs(it - goal) } }
            .minOf { it }
    }

    fun part2(input: List<String>): Int {
        val startingPositions = input.first().splitCsv()

        return Array(startingPositions.maxOf { it }) { goal -> startingPositions.sumOf { abs(it - goal).triangular() } }
            .minOf { it }
    }

    val test_input = readInput("Day07_test")
    check(part1(test_input) == 37)
    check(part2(test_input) == 168)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}

fun Int.triangular(): Int = (this*(this+1))/2