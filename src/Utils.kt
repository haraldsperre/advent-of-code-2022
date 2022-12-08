import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String): List<String> = File("input", "$name.txt")
    .readLines()

fun readInputAsIntLists(name: String): List<List<Int>> = readInput(name)
    .map { row: String ->
        List(row.length) { i ->
            row[i].toString().toInt()
        }
    }

/**
 * Splits list into chunks, delineated by the given predicate.
 */
fun <T> List<T>.chunkedBy(predicate: (T) -> Boolean): List<List<T>> =
    fold(mutableListOf(mutableListOf<T>())) { acc, item ->
        if (predicate(item)) acc.add(mutableListOf())
        else acc.last().add(item)
        acc
    }


/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')
