package problemsolving.dp.oneDimnesional

// https://leetcode.com/problems/climbing-stairs/

//Using Tabulation:- First try to solve it using memoization as we have done in fibonacci, you will understand this approach easily then
fun climbingStairsTabu(n: Int): Int {

    //if number of stairs == 1, we have only 1 way to climb using 1 step
    if (n == 1) return 1

    // for edge case when n == 1, then also we have one way becoz we have only 1 stair we cant climb 2 steps here.
    var prev = 1

    // for going to 0 th step in recursive way, we have 1 way only
    var prev2 = 1

    for (i in 2..n) {
        val curr = prev + prev2
        prev2 = prev
        prev = curr
    }

    return prev
}

fun climbingStairsMemo(n: Int): Int {
    val dp = IntArray(n + 1) {
        -1
    }
    return climbingStairsMemoHelper(n, dp)
}

//Using Memoization:-  this function's meaning is number of ways for going from 0th to nth stair
fun climbingStairsMemoHelper(n: Int, dp: IntArray): Int {

    //if number of stairs == 1, we have only 1 way to climb using 1 step
    if (n == 1) return 1

    //in going from 0th to 0th step, it is only 1 way
    if (n == 0) return 1

    if (dp[n] != -1) return dp[n]

    val left = climbingStairsMemoHelper(n - 1, dp)

    val right = climbingStairsMemoHelper(n - 2, dp)

    return left + right

}


//Using Core Recursion:-
fun climbingStairsRecursion(n: Int): Int {
    return climbingStairsRecursionHelper(n)
}

fun climbingStairsRecursionHelper(n: Int): Int {
    //if number of stairs == 1, we have only 1 way to climb using 1 step
    if (n == 1) return 1

    //in going from 0th to 0th step, it is only 1 way
    if (n == 0) return 1

    val left = climbingStairsRecursionHelper(n - 1)

    val right = climbingStairsRecursionHelper(n - 2)

    return left + right
}

fun main() {

    println(climbingStairsTabu(6))
    println(climbingStairsMemo(6))
    println(climbingStairsRecursion(6))
}