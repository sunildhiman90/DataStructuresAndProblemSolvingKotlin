package problemsolving.basicMaths

//https://www.codingninjas.com/studio/problems/check-armstrong_589?utm_medium=website&utm_campaign=a_zcoursetuf
fun armstrongNumber(num: Int): Boolean {
    var n = num
    var sum = 0
    while (n > 0) {
        val rem = n % 10
        sum += (rem * rem * rem)
        n /= 10
    }
    return sum == num
}

fun main() {

    val num = 371
    println(armstrongNumber(num))

}