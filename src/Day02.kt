fun main() {
    fun part1(input: List<String>): Int {
        return Uboat().apply { input.forEach(this::processCommandPart1) }.let { it.horizontalPos * it.depth }
    }

    fun part2(input: List<String>): Int {
        return Uboat().apply { input.forEach(this::processCommandPart2) }.let { it.horizontalPos * it.depth }
    }

    val test_input = readInput("Day02_test")
    check(part1(test_input) == 150)
    check(part2(test_input) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

data class Uboat(var horizontalPos: Int = 0, var depth: Int = 0, var aim: Int = 0) {
    fun processCommandPart1(command: String) {
        val (instruction, param) = splitCommand(command)

        when (instruction) {
            "forward" -> horizontalPos += param
            "down" -> depth += param
            "up" -> depth -= param
        }
    }

    fun processCommandPart2(command: String) {
        val (instruction, param) = splitCommand(command)

        when (instruction) {
            "forward" -> {
                horizontalPos += param
                depth += aim * param
            }
            "down" -> aim += param
            "up" -> aim -= param
        }
    }

    private fun splitCommand(command: String): Pair<String, Int> =
        command.split(' ').let { it[0] to it[1].toInt() }
}