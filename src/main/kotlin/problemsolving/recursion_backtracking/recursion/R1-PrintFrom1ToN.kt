package problemsolving.recursion_backtracking.recursion

// Using Recursion:- starting from 1 and printing first and calling f(i+1)
fun printNumbers(i: Int, n: Int) {

    if (i > n) return

    println(i) // do work and then call next function
    printNumbers(i + 1, n)

}

// Using Recursion Backtracking:- starting from last and going till base-1 => (0) by calling f(i-1) at each iteration and backtracking from there and then printing
fun printNumbersBacktracking(i: Int, n: Int) {

    if (i < 1) return //backtrack here, we dont need to go down to 1

    printNumbersBacktracking(i - 1, n)
    println(i) // do work here after backtracking and returning from prev function after func execution

}

fun main() {
    printNumbers(1, 5)
    println("----------")
    printNumbersBacktracking(5, 5)
}