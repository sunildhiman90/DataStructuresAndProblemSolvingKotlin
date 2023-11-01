package problemsolving.basicMaths


fun checkPalindromeNumber(num: Int): Boolean {
    val reversedNumber = reverseNumber(num)
    return if (reversedNumber == num) true else false
}

fun main() {
    val num = 789
    println(checkPalindromeNumber(num))
    println(checkPalindromeNumber(12321))

}

