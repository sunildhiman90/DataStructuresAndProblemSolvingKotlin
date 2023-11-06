package problemsolving.basicMaths

//GCD/HCF -> Highest Common Factor

//O(log (min(num1, num2)))
fun hcfUsingEuclideanAlgo(num1: Int, num2: Int) {
    var a = num1
    var b = num2
    while (a > 0 && b > 0) {
        if (a > b) a = a % b
        else b = b % a
    }

    if (a == 0) println(b) else println(a)
}

fun main() {

    val num1 = 12
    val num2 = 18
    hcf(num1, num2)

}