import java.util.concurrent.ForkJoinPool
import kotlin.streams.toList

fun main() {
    fun list(fish: List<Int>) = fish

    fun simulate(initialState: List<Int>, days: Int): Int {
        var fish = initialState

        val executorService = ForkJoinPool.commonPool()

        (1..days).map {
            println(it)
            fish = fish.flatMap {
                val newFish = mutableListOf(it)
                if (newFish.first() == 0) {
                    newFish[0] = 7
                    newFish.add(9)
                }
                newFish
            }.map { it - 1 }.toList()
        }
        return fish.size

    }

    fun part1(input: List<String>): Int {
        return simulate(input.first().splitCsv(), 80)
    }

    fun part2(input: List<String>): Int {
        return simulate(input.first().splitCsv(), 255)
    }

    val test_input = readInput("Day06_test")
    check(part1(test_input) == 5934)
//    check(part2(test_input) == 5)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
