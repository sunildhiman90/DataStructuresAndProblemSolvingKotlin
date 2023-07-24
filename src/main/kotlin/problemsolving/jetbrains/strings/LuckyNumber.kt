package problemsolving.jetbrains.strings


/**
 * Given the number N with an even number of digits.
 * If the sum of the first half of the digits equals the sum of the second half of the digits,
 * then this number is considered lucky.
 * For a given number, output "YES" if this number is lucky, otherwise output "NO".
*/

//
fun main() {
    val number = readln()

    //My solution
    val size = number.length
    val firstHalfSum = number.substring(0, size / 2).sumOf { it.digitToInt() }
    val secondHalfSum = number.substring(size / 2, size).sumOf { it.digitToInt() }
    if (firstHalfSum == secondHalfSum) println("YES") else println("NO")

    //Other solution1
    readln().run {
        chunked(length / 2) { numbers ->
            numbers.sumBy { Character.getNumericValue(it) }
        }.run {
            println(if (first() == last()) "YES" else "NO")
        }
    }

}
