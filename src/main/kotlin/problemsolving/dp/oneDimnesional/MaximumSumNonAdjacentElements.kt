package problemsolving.dp.oneDimnesional

// https://leetcode.com/problems/house-robber

class MaximumSumNonAdjacentElements {
}

fun robUsingRecursion(nums: IntArray): Int {
    val n = nums.size
    return robUsingRecursionHelper(n - 1, nums)
}

fun robUsingRecursionHelper(ind: Int, nums: IntArray): Int {
    if (ind == 0) return nums[ind]
    if (ind < 0) return 0

    //due to adjacent element requirement, we need to pick ind-2 becoz we cant pick ind-1
    // this is current element +  picking non adjacent element from n-2 to 0
    val pick = nums[ind] + robUsingRecursionHelper(ind - 2, nums)
    val nonPick =
        0 + robUsingRecursionHelper(ind - 1, nums) //here we can pick ind-1, becoz this is case of not picking ind-2

    return Math.max(pick, nonPick)
}


fun robUsingMemo(nums: IntArray): Int {
    val n = nums.size
    val dp = IntArray(n + 1) {
        -1
    }
    return robUsingMemoHelper(n - 1, nums, dp)
}

fun robUsingMemoHelper(ind: Int, nums: IntArray, dp: IntArray): Int {
    if (ind == 0) return nums[ind]
    if (ind < 0) return 0


    if (dp[ind] != -1) return dp[ind]

    //due to adjacent element requirement, we need to pick ind-2 becoz we cant pick ind-1
    val pick = nums[ind] + robUsingMemoHelper(ind - 2, nums, dp)
    val nonPick =
        0 + robUsingMemoHelper(ind - 1, nums, dp) //here we can pick ind-1, becoz this is case of not picking ind-2

    val result = Math.max(pick, nonPick)
    dp[ind] = result
    return dp[ind]
}


fun robUsingTabulation(n: Int, nums: IntArray): Int {

    val dp = IntArray(n) {
        -1
    }

    dp[0] = nums[0]

    for (i in 1 until n) {

        var pick = nums[i]
        if (i > 1) {
            pick += dp[i - 2]
        }

        val nonPick = 0 + dp[i - 1] //here we can pick ind-1, becoz this is case of not picking ind-2

        val result = Math.max(pick, nonPick)
        dp[i] = result
    }
    return dp[n - 1]
}

fun robUsingTabuSpaceOptimization(n: Int, nums: IntArray): Int {

    var prev = nums[0]
    var prev2 = 0 //for negative index case

    for (i in 1 until n) {

        val pick = nums[i] + prev2 //pick ind-2

        val nonPick = 0 + prev //here we can pick ind-1, becoz this is case of not picking ind-2

        val curr = Math.max(pick, nonPick)
        prev2 = prev
        prev = curr
    }
    return prev
}

fun main() {

    val intArray = intArrayOf(2, 7, 9, 3, 1)
    println(robUsingRecursion(intArray))

    println(robUsingMemo(intArray))

    println(robUsingTabulation(intArray.size, intArray))
    println(robUsingTabuSpaceOptimization(intArray.size, intArray))
}