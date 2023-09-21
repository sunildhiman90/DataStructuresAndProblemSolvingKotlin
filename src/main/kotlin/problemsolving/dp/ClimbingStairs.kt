package problemsolving.dp

// https://leetcode.com/problems/climbing-stairs/

// First try to solve it using memoization as we have done in fibonacci, you will understand this approach easily then
fun climbingStairs(n: Int): Int {

    //if number of stairs == 1, we have only 1 way to climb using 1 step
    if (n == 1) return 1

    // for going to 0 th step in recursive way, we have 1 way only
    // for edge case when n == 1, then also we have one way becoz we have only 1 stair we cant climb 2 steps here.
    var prev = 1
    var prev2 = 1

    for (i in 2..n) {
        val curr = prev + prev2
        prev2 = prev
        prev = curr
    }

    return prev
}

fun main() {

    println(climbingStairs(5))
}