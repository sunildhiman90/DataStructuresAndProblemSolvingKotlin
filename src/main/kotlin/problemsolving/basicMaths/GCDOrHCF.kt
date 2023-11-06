package problemsolving.basicMaths

import kotlin.math.min

//GCD/HCF -> Highest Common Factor

//O(min(num1, num2))
fun hcf(num1: Int, num2: Int) {
    var gcd = 1
    for (i in 1..min(num1, num2)) {
        if (num1 % i == 0 && num2 % i == 0) gcd = i
    }
    println(gcd)
}

//O(min(num1, num2))
fun hcf2(num1: Int, num2: Int) {
    for (i in min(num1, num2) downTo 1) {
        if (num1 % i == 0 && num2 % i == 0) {
            println(i)
            break
        }
    }
}

fun main() {

    val num1 = 12
    val num2 = 18
    hcf(num1, num2)
    hcf2(num1, num2)

}