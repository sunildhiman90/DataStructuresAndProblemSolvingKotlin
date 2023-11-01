package problemsolving.basicMaths

class ReverseNumber {
}

fun reverseNumber(num: Int): Int {
    var n = num
    var reversed = 0
    while (n > 0) {
        val rem = n % 10
        reversed = (reversed * 10) + rem
        n /= 10
    }
    return reversed
}

fun main() {

    val num = 789
    println(reverseNumber(num))

}