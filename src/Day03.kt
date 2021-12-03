fun main() {
    fun part1(input: List<String>): Int =
        (0 until input.first().length).map { pos ->
            input.map { it.toInt(2) }.map {
                it.getBit(pos)
            }.groupingBy { it }.eachCount().entries.let { entries ->
                entries.maxByOrNull { it.value }!!.key to
                        entries.minByOrNull { it.value }!!.key
            }
        }.reversed().reduce { acc, i ->
            Pair(
                (acc.first shl 1) or i.first,
                (acc.second shl 1) or i.second,
            )
        }.let { it.first * it.second }

    fun part2(input: List<String>): Int {
        var inputNumbers = input.map { it.toInt(2) }

        var pos = input.first().length - 1
        while (inputNumbers.size != 1) {
            val mostSignificant = inputNumbers
                .map { it.getBit(pos) }
                .groupingBy { it }
                .eachCount().entries.sortedByDescending { it.key }
                .maxByOrNull { it.value }!!.key

            inputNumbers = inputNumbers.filter { it.getBit(pos) == mostSignificant }
            pos -= 1;
        }
        val oxygenGeneratorRating = inputNumbers.first()

        inputNumbers = input.map { it.toInt(2) }

        pos = input.first().length - 1
        while (inputNumbers.size != 1) {
            val mostSignificant = inputNumbers
                .map { it.getBit(pos) }
                .groupingBy { it }
                .eachCount().entries.sortedBy { it.key }
                .minByOrNull { it.value }!!.key

            inputNumbers = inputNumbers.filter { it.getBit(pos) == mostSignificant }
            pos -= 1;
        }

        val coTwoScrubber = inputNumbers.first()

        return oxygenGeneratorRating * coTwoScrubber
    }

    val test_input = readInput("Day03_test")
    check(part1(test_input) == 198)
    check(part2(test_input) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
