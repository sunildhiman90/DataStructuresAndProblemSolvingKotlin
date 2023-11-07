package problemsolving.recursion_backtracking.recursion

//nth Fibonacci number
// TC: O(2.pow(n)) => exponential, Becoz in each case of n,  are calling 2 recursion calls
fun fib(n: Int): Int {

    if (n == 0 || n == 1) return n

    return fib(n - 1) + fib(n - 2)

}

fun main() {
    println(fib(4))
}