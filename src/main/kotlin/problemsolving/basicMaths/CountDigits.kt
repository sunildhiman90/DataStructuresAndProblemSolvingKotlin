package problemsolving.basicMaths

import kotlin.math.log10

// TC: O(log10(n))
fun countDigits(num: Int): Int {
    var n = num
    var count = 0
    while (n > 0) {
        count++
        n /= 10
    }
    return count
}

// TC: O(log10(n))
fun countDigits2(num: Int): Int {
    var count = log10(num.toDouble()).toInt() + 1
    return count
}

fun main() {

    val num = 789
    println(countDigits(num))
    println(countDigits2(num))

}


