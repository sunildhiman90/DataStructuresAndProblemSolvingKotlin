package problemsolving.basicMaths

import kotlin.math.sqrt

// https://www.codingninjas.com/studio/problems/sum-of-all-divisors_8360720?utm_medium=website&utm_campaign=a_zcoursetuf

//O(n)
fun printDivisers1(num: Int) {
    for (i in 1..num) {
        if (num % i == 0) println(i)
    }
}


//O(sqrt(n))
fun printDivisers2(num: Int) {
    val list = mutableListOf<Int>()

    //O(sqrt(n))
    for (i in 1..sqrt(num.toDouble()).toInt()) {
        if (num % i == 0) {
            list.add(i)
            if (num / i != i)
                list.add(num / i)
        }
    }

    //O(num of factors * log (num of factors))
    list.sort()

    list.forEach { println(it) }
}

fun main() {

    val num = 36
    printDivisers1(num)
    printDivisers2(num)

}