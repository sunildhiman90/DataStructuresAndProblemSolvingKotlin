package problemsolving.recursion_backtracking.recursion

fun sumOfNNaturalNumbers(n: Int) {
    helper(n, 0)
}

//Parameterized way
fun helper(n: Int, sum: Int) {

    if (n < 1) {
        println(sum)
        return
    }

    helper(n - 1, sum + n)

}


fun sumOfNNaturalNumbers2(n: Int) {
    println(helper2(n))
}

//Functional way, Best for Backtracking and DP problems
fun helper2(n: Int): Int {

    if (n < 1) {
        return 0
    }

    //use current number and calculate for rest of the numbers
    return n + helper2(n - 1)

}

fun main() {
    sumOfNNaturalNumbers(5)
    sumOfNNaturalNumbers2(5)
}