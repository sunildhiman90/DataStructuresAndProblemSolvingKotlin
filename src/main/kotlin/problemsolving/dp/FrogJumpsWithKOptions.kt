package problemsolving.dp

import kotlin.math.abs

//https://www.codingninjas.com/studio/problems/frog-jump_3621012
//If you look at the above example, there are only 1, 2 jumps os options, but here we have from 1 upto k jumps


// In case if some extra logic is not given, just like in Climbing Stairs, direct use the array, and manipulate for left and right if there are 2 choices, if more than 2, manipulate for all choices
// But in case if some extra logic is needed, like in this problem, for left call, use f(n-1) or whatever your call is
// and then do its current logic for work using multiple array elements according to logic, like energy in this example => abs(heights[n], heights[n-1])

//USING RECURSION:-
fun frogJumpsKOptionsRecursion(n: Int, heights: IntArray, k: Int): Int {
    //pass last index, not n
    return frogJumpsKOptionsRecursionHelper(n - 1, heights, k)
}

// Minimum energy of frog jumps to reach (n-1)th floor from 0th
fun frogJumpsKOptionsRecursionHelper(n: Int, heights: IntArray, k: Int): Int {
    if (n == 0) return 0 // from 0 to 0th floor no energy will be there, so return 0

    var minEnergy = Int.MAX_VALUE
    for (i in 1..k) {
        if (n >= i) {
            val currentEnergy =
                frogJumpsKOptionsRecursionHelper(n - i, heights, k) + abs(heights[n] - heights[n - i])
            minEnergy = Math.min(currentEnergy, minEnergy)
        }
    }
    return minEnergy

}

// USING MEMOIZATION:-
fun frogJumpsKOptionsMemo(n: Int, heights: IntArray, k: Int): Int {
    val dp = IntArray(n + 1) {
        -1
    }

    //pass last index, not n
    return frogJumpsKOptionsMemoHelper(n - 1, heights, dp, k)
}

// Minimum energy of frog jumps to reach nth floor from 0th
// TC: O(n)
// SC: O(n) + O(n) => due to stack calls: O(n) + dp array O(n)
fun frogJumpsKOptionsMemoHelper(n: Int, heights: IntArray, dp: IntArray, k: Int): Int {
    if (n == 0) return 0 // from 0 to 0th floor no energy will be there, so return 0

    if (dp[n] != -1) return dp[n]

    var minEnergy = Int.MAX_VALUE
    for (i in 1..k) {
        if (n >= i) {
            val currentEnergy = frogJumpsKOptionsMemoHelper(n - i, heights, dp, k) + abs(heights[n] - heights[n - i])
            minEnergy = Math.min(currentEnergy, minEnergy)
        }
    }
    dp[n] = minEnergy
    return dp[n]
}

// USING TABULATION:- CONVERTED FROM MEMOIZATION
//TC: O(n*k) , for each index, we are using loop for k
// SC: O(n) => due to DP array
fun frogJumpsKOptionsTabulation(n: Int, heights: IntArray, k: Int): Int {

    val dp = IntArray(n + 1) {
        -1
    }

    // from 0 to 0th floor no energy will be there, so return 0
    //if (n == 0) return 0, replace this step with below line
    dp[0] = 0

    for (i in 1 until n) {

        var minEnergy = Int.MAX_VALUE
        for (j in 1..k) {
            if (i >= j) {
                val currentEnergy = dp[i - j] + abs(heights[i] - heights[i - j])
                minEnergy = Math.min(currentEnergy, minEnergy)
            }
        }
        dp[i] = minEnergy
    }

    return dp[n - 1]
}


// IF there are index-1 and index-2, we can always do space optimization as well in Tabulation,
// But here we have k, so if in worst case if k = n then again it will become O(n)

fun main() {

    val heights = intArrayOf(
        10, 20, 30, 10, 50, 20
    )
    val k = 3
    println(frogJumpsKOptionsRecursion(heights.size, heights, k))
    println(frogJumpsKOptionsMemo(heights.size, heights, k))
    println(frogJumpsKOptionsTabulation(heights.size, heights, k))
}
