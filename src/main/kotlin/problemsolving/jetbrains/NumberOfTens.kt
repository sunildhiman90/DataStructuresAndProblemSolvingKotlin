package problemsolving.jetbrains

//https://hyperskill.org/learn/step/4494

/**
 * The number of tens
There is a non-negative integer N (0 ≤ N ≤ 1000000).
Find the number of tens in it (next to the last digit of the number).
If there is only one digit, you can consider that the number of tens equals zero.
 */

class NumberOfTens {
}

fun main() {
    val number = readLine()!!.toInt()
    val remainder1 = number%100 //Because we need to count number of tens in last 2 digits(next to the last digit of the number), so dividing any number by 100 will return the last 2 digits as remainder
    val quotient = remainder1/10  //and two digits number divided by 10 will return the first digit
    println(quotient)
}

/**
 * Example: 173->
 * last 2 digits:- 73
 * and 73/10 = 7
 * And 73 = 7*10+3 => 7 tens are there
 */