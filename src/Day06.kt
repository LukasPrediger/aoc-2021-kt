data class FishGroup(
    var timer: Int,
    val size: Long
)

fun main() {
    fun list(fish: List<Int>) = fish

    fun simulate(initialState: List<Int>, days: Int): Long {
        var fish = initialState.groupingBy { it }.eachCount().map { FishGroup(it.key, it.value.toLong()) }

        (1 until days).map { _ ->
            fish.forEach { it.timer -= 1 }

            val (reproduceGroups, otherGroups) = fish.partition { it.timer == 0 }

            val newGroups = reproduceGroups.map { FishGroup(9, it.size) }

            fish = (reproduceGroups.map { FishGroup(7, it.size) } + otherGroups + newGroups)
                .groupBy { it.timer }.map { FishGroup(it.key, it.value.sumOf(FishGroup::size)) }

        }
        return fish.sumOf(FishGroup::size)

    }

    fun part1(input: List<String>): Long {
        return simulate(input.first().splitCsv(), 80)
    }

    fun part2(input: List<String>): Long {
        return simulate(input.first().splitCsv(), 256)
    }

    val test_input = readInput("Day06_test")
    check(part1(test_input) == 5934L)
    check(part2(test_input) == 26984457539L)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
