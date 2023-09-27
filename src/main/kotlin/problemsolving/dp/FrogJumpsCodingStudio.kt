package problemsolving.dp

import kotlin.math.abs

//https://www.codingninjas.com/studio/problems/frog-jump_3621012

// In case if some extra logic is not given, just like in Climbing Stairs, direct use the array, and manipulate for left and right if there are 2 choices, if more than 2, manipulate for all choices
// But in case if some extra logic is needed, like in this problem, for left call, use f(n-1) or whatever your call is
// and then do its current logic for work using multiple array elements according to logic, like energy in this example => abs(heights[n], heights[n-1])

//USING RECURSION:-
fun frogJumpsRecursion(n: Int, heights: IntArray): Int {
    //pass last index, not n
    return frogJumpsRecursionHelper(n - 1, heights)
}

// Minimum energy of frog jumps to reach nth floor from 0th
fun frogJumpsRecursionHelper(n: Int, heights: IntArray): Int {
    if (n == 0) return 0 // from 0 to 0th floor no energy will be there, so return 0

    // from n to n-1 means, total energy from (n-1)th floor to 0th floor + energy from nth to (n-1)th
    val left = frogJumpsRecursionHelper(n - 1, heights) + abs(heights[n] - heights[n - 1])

    // from n to n-2 means, total energy from (n-2)th floor to 0th floor + energy from nth to (n-2)th
    var right = Int.MAX_VALUE
    if (n > 1) //in case of n == 1, we cant jump 2 steps
        right = frogJumpsRecursionHelper(n - 2, heights) + abs(heights[n] - heights[n - 2])

    return Math.min(left, right)
}

// USING MEMOIZATION:-
fun frogJumpsMemo(n: Int, heights: IntArray): Int {
    val dp = IntArray(n + 1) {
        -1
    }

    //pass last index, not n
    return frogJumpsMemoHelper(n - 1, heights, dp)
}

// Minimum energy of frog jumps to reach nth floor from 0th
// TC: O(n)
// SC: O(n) + O(n) => due to stack calls: O(n) + dp array O(n)
fun frogJumpsMemoHelper(n: Int, heights: IntArray, dp: IntArray): Int {
    if (n == 0) return 0 // from 0 to 0th floor no energy will be there, so return 0

    if (dp[n] != -1) return dp[n]

    // from n to n-1 means, total energy from (n-1)th floor to 0th floor + energy from nth to (n-1)th
    val left = frogJumpsMemoHelper(n - 1, heights, dp) + abs(heights[n] - heights[n - 1])

    // from n to n-2 means, total energy from (n-2)th floor to 0th floor + energy from nth to (n-2)th
    var right = Int.MAX_VALUE
    if (n > 1) //in case of n == 1, we cant jump 2 steps
        right = frogJumpsMemoHelper(n - 2, heights, dp) + abs(heights[n] - heights[n - 2])

    dp[n] = Math.min(left, right)
    return dp[n]
}

// USING TABULATION:- CONVERTED FROM MEMOIZATION
//TC: O(n) and
// SC: O(n) => due to DP array
fun frogJumpsTabulation(n: Int, heights: IntArray): Int {

    val dp = IntArray(n + 1) {
        -1
    }

    // from 0 to 0th floor no energy will be there, so return 0
    //if (n == 0) return 0, replace this step with below line
    dp[0] = 0

    for (i in 1 until n) {

        //val left = frogJumpsMemoHelper(n - 1, heights, dp) + abs(heights[n] - heights[n - 1]), replace it with below line, mainly replace recursion call with dp and n with i
        val left = dp[i - 1] + abs(heights[i] - heights[i - 1])

        // from n to n-2 means, total energy from (n-2)th floor to 0th floor + energy from nth to (n-2)th
        var right = Int.MAX_VALUE
        //in case of i == 1, we cant jump 2 steps, so it wil run in case if i > 1
        if (i > 1) {
            //right = frogJumpsMemoHelper(n - 2, heights, dp) + abs(heights[n] - heights[n - 2]), replace it with below line, mainly replace recursion call with dp and n with i
            right = dp[i - 2] + abs(heights[i] - heights[i - 2])
        }

        dp[i] = Math.min(left, right)
    }

    return dp[n - 1]
}


// IF there are index-1 and index-2, we can always do space optimization as well in Tabulation
// SPACE OPTIMIZATION USING TABULATION:- CONVERTED FROM TABULATION
//TC: O(n)
// SC: O(1) => becoz no dp arrays used now
fun frogJumpsTabulationSpaceOptimized(n: Int, heights: IntArray): Int {

    // from 0 to 0th floor no energy will be there, so return 0
    //if (n == 0) return 0, replace this step with below line
    //dp[0] = 0 . convert it to prev
    var prev = 0
    var prev2 =
        0 // this is due to i  == 1, becoz for i = 1 we dont have any value for dp[i-2], it will become dp[-1], index will become negative, so use 0

    for (i in 1 until n) {

        //val left = frogJumpsMemoHelper(n - 1, heights, dp) + abs(heights[n] - heights[n - 1]) //replace it with below line, mainly replace recursion call with dp and n with i
        //val left = dp[i - 1] + abs(heights[i] - heights[i - 1]) // replace it with below line
        val left = prev + abs(heights[i] - heights[i - 1])

        // from n to n-2 means, total energy from (n-2)th floor to 0th floor + energy from nth to (n-2)th
        var right = Int.MAX_VALUE
        //in case of i == 1, we cant jump 2 steps, so it wil run in case if i > 1
        if (i > 1) {
            //right = frogJumpsMemoHelper(n - 2, heights, dp) + abs(heights[n] - heights[n - 2]) // replace it with below line, mainly replace recursion call with dp and n with i
            //right = dp[i - 2] + abs(heights[i] - heights[i - 2]) // replace it with below line
            right = prev2 + abs(heights[i] - heights[i - 2])
        }

        val curr = Math.min(left, right)
        prev2 = prev
        prev = curr
    }

    return prev
}

fun main() {

    val heights = intArrayOf(
        10, 20, 30, 10
    )
    println(frogJumpsRecursion(4, heights))
    println(frogJumpsMemo(4, heights))
    println(frogJumpsTabulation(4, heights))
    println(frogJumpsTabulationSpaceOptimized(4, heights))
}
