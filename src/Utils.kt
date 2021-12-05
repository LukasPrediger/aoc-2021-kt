import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

fun Int.getBit(position: Int): Int = (this shr position) and 1

fun Int.setBit(position: Int, value: Int) = (value shl position) or this

fun String.splitCsv(): List<Int> = this.split(',').map(String::toInt)