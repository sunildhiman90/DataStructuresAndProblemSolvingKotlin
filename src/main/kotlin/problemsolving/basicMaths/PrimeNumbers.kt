package problemsolving.basicMaths

import kotlin.math.sqrt

// Prime number is the number which have only 2 factors 1 and the number itself
//O(n)
fun checkPrimeNumber(num: Int): Boolean {
    var factors = 0
    for (i in 1..num) {
        if (num % i == 0) factors++
    }
    return if (factors == 2) true else false
}


//O(sqrt(n))
fun checkPrimeNumber2(num: Int): Boolean {
    var factors = 0

    //O(sqrt(n))
    for (i in 1..sqrt(num.toDouble()).toInt()) {
        if (num % i == 0) {
            factors++
            if (num / i != i)
                factors++
        }
    }

    return if (factors == 2) true else false
}

fun main() {

    val num = 13
    println(checkPrimeNumber(num))
    println(checkPrimeNumber2(num))

}