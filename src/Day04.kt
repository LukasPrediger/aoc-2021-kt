fun main() {
    fun calculateBoards(input: List<String>): Pair<List<Int>, List<Pair<Int, List<List<Pair<Int, Int>>>>>> {
        val drawnNumbers = input.first().splitCsv()

        val boards = input.subList(1, input.size).asSequence().filter(String::isNotBlank).chunked(5).map { row ->
            row.map {
                it.trim().replace(Regex(" +"), ",").splitCsv()
            }
        }.map { board ->
            // add columns as additional rows
            board.toMutableList() + (board.indices).map { column -> board.map { it[column] } }
        }.map { board ->
            board
                // Calculate index of each cell in the drawn numbers
                .map { row -> row.map { drawnNumbers.indexOf(it) to it } }
                // get highest index of each row
                .map { it.maxByOrNull { cell -> cell.first }!!.first to it }.let {
                    // get lowest index per board
                    it.minByOrNull { row -> row.first }!!.first to it.map { row -> row.second }
                }
        }.toList()

        return drawnNumbers to boards;
    }

    fun calculateUnmarkedSum(
        winningBoard: List<List<Pair<Int, Int>>>,
        winningIndex: Int
    ): Int {
        val unmarkedSum = winningBoard.asSequence().flatten()
            // Get all numbers which would be marked later then our winner
            .filter { it.first > winningIndex }
            // Remove duplicate rows from turning columns into additional rows
            .map { it.second }.toSet()
            .sum()
        return unmarkedSum
    }

    fun part1(input: List<String>): Int {
        val (drawnNumbers, boards) = calculateBoards(input)

        val (winningIndex, winningBoard) = boards.minByOrNull { it.first }!!

        return calculateUnmarkedSum(winningBoard, winningIndex) * drawnNumbers[winningIndex]
    }

    fun part2(input: List<String>): Int {
        val (drawnNumbers, boards) = calculateBoards(input)

        val (loosingIndex, loosingBoard) = boards.maxByOrNull { it.first }!!

        return calculateUnmarkedSum(loosingBoard, loosingIndex) * drawnNumbers[loosingIndex]
    }

    val test_input = readInput("Day04_test")
    check(part1(test_input) == 4512)
    check(part2(test_input) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
