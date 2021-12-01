fun main() {
    fun part1(input: List<String>): Int {
        return input.map { it.toInt() }.zipWithNext { a, b -> a < b }.count { it }
    }

    fun part2(input: List<String>): Int {
        return input.windowed(3)
            .map { window -> window.sumOf { it.toInt() } }
            .zipWithNext { a, b -> a < b }
            .count { it }
    }

    val test_input = readInput("Day01_test")
    check(part1(test_input) == 7)
    check(part2(test_input) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
