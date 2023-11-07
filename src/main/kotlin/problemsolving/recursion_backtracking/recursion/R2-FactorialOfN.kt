package problemsolving.recursion_backtracking.recursion

fun factorial(n: Int) {
    factHelper(n, 1)
}

//Parameterized way
fun factHelper(n: Int, fact: Int) {

    if (n == 0) {
        println(fact)
        return
    }

    factHelper(n - 1, fact * n)

}


fun factorial2(n: Int) {
    println(factHelper2(n))
}

//Functional way, Best for Backtracking and DP problems
fun factHelper2(n: Int): Int {

    if (n == 0) return 1

    //use current number and calculate for rest of the numbers
    return n * factHelper2(n - 1)

}

fun main() {
    factorial(5)
    factorial2(5)
}