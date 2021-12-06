import kotlin.math.max

fun main() {
    fun part1(input: List<String>): Int {
        val lines = input.map(Line::parse)

        val (maxX, maxY) = lines
            .map { max(it.a.x, it.b.x) to max(it.a.y, it.b.y) }
            .reduce { acc, value ->
                max(acc.first, value.first) to max(acc.second, value.second)
            }

        val (horizontalLines, verticalLines) = lines.partition { it.a.y == it.b.y }
            .let { pair -> pair.first to pair.second.partition { it.a.x == it.b.x }.first }

        println(horizontalLines)
        val points = (0..maxX).map { x ->
            (0..maxY).map { y ->
                val point = Point(x, y)
                point to (horizontalLines + verticalLines).map { it.intersects(point) }.count { it }
            }
        }.flatten()

        val debug = Array(maxY) { y ->
            Array(maxX) { x ->
                points.find { it.first.x == x && it.first.y == y }?.second ?: 0
            }
        }

        debug.map { rows -> rows.joinToString(" ") }.forEach(::println)

        return points.also(::println).count { it.second >= 2 }.also(::println)
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val test_input = readInput("Day05_test")
    check(part1(test_input) == 5)
//    check(part2(test_input) == 5)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}

data class Point(val x: Int, val y: Int) {
    companion object {
        fun parse(input: String): Point = input.splitCsv().let { Point(it[0], it[1]) }
    }
}

data class Line(val a: Point, val b: Point) {
    fun intersects(c: Point): Boolean = c.x in (a.x..b.x) && c.y in (a.y..b.y)

    companion object {
        fun parse(input: String): Line = input.split(" -> ")
            .map(Point::parse)
            .let { Line(it[0], it[1]) }
    }
}