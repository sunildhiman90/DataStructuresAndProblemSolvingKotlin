package problemsolving.dp.subsequenceOrSubsets

import kotlin.math.abs
import kotlin.math.min

// https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/


//TODO, not working for negative numbers
fun subsetMinSumDiffTabulation(array: IntArray): Int {

    val total = array.sumOf { abs(it) }

    val k = total

    val n = array.size
    val dp = Array(n) {
        BooleanArray(k + 1) {
            false
        }
    }


    // base case 1 // It means target can be 0 and index can be any other from 0 to n-1
    //if (target == 0) return true
    for (i in 0 until n) dp[i][0] = true

    //base case 2, It means ind can be 0 and target can be any other from target, target-1... to 0, but we need to return only for array[0]
    //if (ind == 0) return array[ind] == target
    if (abs(array[0]) <= k) dp[0][array[0]] = true

    for (ind in 1 until n) {
        for (target in 1..k) {
            //not take
            val notTake = dp[ind - 1][target]

            //take
            var take = false
            if (abs(array[ind]) <= target)
                take = dp[ind - 1][target - abs(array[ind])]

            dp[ind][target] = take || notTake
        }
    }


    // targets will be from 0 (min sum) to total sum( max sum)
    //for last row dp[n-1][0...total], calculate minimum sum diff, we can use total/2, bcecoz first half will contains same pairs from second half
    var min = Math.pow(10.toDouble(), 9.toDouble()).toInt()
    for (sum1 in 0 until total / 2) {
        if (dp[n - 1][sum1]) {
            val sum2 = total - sum1
            min = Math.min(min, abs(sum1 - sum2))
        }
    }
    return min
}


fun subsetMinSumDiffTabuSpaceOpt(array: IntArray): Int {

    val total = array.sumOf { abs(it) }

    val n = array.size

    val k = total

    //use array indexes for index and values just for target match
    var prev = BooleanArray(k + 1) {
        false
    }

    // base case 1 // It means target can be 0 and index can be any other from 0 to n-1
    //if (target == 0) return true
    //for (i in 0 until n) dp[i][0] = true
    prev[0] = true //, becoz dp[index] will become prev


    //base case 2, It means ind can be 0 and target can be any other from target, target-1... to 0, but we need to return only for array[0]
    //if (ind == 0) return array[ind] == target
    //dp[0][array[0]] = true
    if (array[0] >= 0 && array[0] <= k) prev[array[0]] = true

    for (ind in 1 until n) {

        val curr = BooleanArray(k + 1) {
            false
        }
        curr[0] = true //for some value target has to be true

        for (target in 1..k) {

            //not take
            val notTake = prev[target]

            //take
            var take = false
            if (array[ind] <= target)
                take = prev[target - array[ind]]

            curr[target] = take || notTake
        }

        prev = curr
    }


    // targets will be from 0 (min sum) to total sum( max sum)
    //for last row dp[n-1][0...total], calculate minimum sum diff, we can use total/2, bcecoz first half will contains same pairs from second half
    var min = Math.pow(10.toDouble(), 9.toDouble()).toInt()
    for (sum1 in 0 until total / 2) {
        if (prev[sum1]) {
            val sum2 = total - sum1
            min = Math.min(min, abs(sum1 - sum2))
        }
    }
    return min
}


fun minPartitionDifference(nums: IntArray): Int {
    val n = nums.size / 2
    var totalSum = 0
    for (num in nums) {
        totalSum += num
    }
    totalSum = Math.abs(totalSum)
    val range = 2 * totalSum + 1
    val dp = Array(n + 1) { BooleanArray(range) }
    dp[0][totalSum] = true
    for (i in 1..n) {
        val num = nums[i - 1]
        for (j in -totalSum..totalSum) {
            if (j - num + totalSum >= 0 && j - num + totalSum < range) {
                dp[i][j + totalSum] = dp[i - 1][j + totalSum] || dp[i - 1][j - num + totalSum]
            }
        }
    }
    var minDiff = Int.MAX_VALUE
    for (j in 0 until range) {
        if (dp[n][j]) {
            minDiff = min(minDiff.toDouble(), abs((j - totalSum).toDouble())).toInt()
        }
    }
    return minDiff
}


fun minPartitionDifference2(array: IntArray): Int {
    val n = array.size
    val total = Math.abs(array.sum())
    val k = total + total // Adjust the range to account for negative numbers.

    val dp = Array(n) {
        BooleanArray(k + 1) { false }
    }

    // Base case 1: If target is 0, it's always possible by not taking any elements.
    for (i in 0 until n) dp[i][0] = true

    // Base case 2: Initialize for the first element (index 0).
    if (array[0] + total in 0 until k) dp[0][array[0] + total] = true

    for (ind in 1 until n) {
        for (target in 1 until k) {
            // Not take
            val notTake = dp[ind - 1][target]

            // Take
            var take = false
            if (target - array[ind] + total in 0 until k)
                take = dp[ind - 1][target - array[ind] + total]

            dp[ind][target] = take || notTake
        }
    }

    // Targets will be from 0 (min sum) to total (max sum).
    // Calculate minimum sum diff.
    var min = Int.MAX_VALUE
    for (sum1 in 0 until k) {
        if (dp[n - 1][sum1]) {
            val sum2 = k - sum1
            min = minOf(min, Math.abs(sum1 - sum2))
        }
    }

    return min
}

fun main() {
    //val nums = intArrayOf(3, 9, 7, 3)
    val nums = intArrayOf(-36, 36)
    //val nums = intArrayOf(2, -1, 0, 4, -2, -9)

    //println(subsetMinSumDiffTabulation(nums))
    //println(subsetMinSumDiffTabuSpaceOpt(nums))
    println(minPartitionDifference(nums))
    println(minPartitionDifference2(nums))
}