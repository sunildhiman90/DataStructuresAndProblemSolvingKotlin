package problemsolving.dp.subsequenceOrSubsets


//https://leetcode.com/problems/target-sum/submissions/1088076996/

// Using Recursion
// TC: O(2.pow(n))
// SC: O(n)
fun targetSumWithSymbols(n: Int, diff: Int, array: IntArray): Int {

    val total = array.sum()

    // If there are 2 subsets sums s1, s2
    // derived from if s1 - s2 = diff => total - s2 - s2 = diff => 2*s2 = total - diff => s2 = (total - diff) / 2
    val target = (total - diff) / 2

    // edge cases
    if (total - diff < 0 || ((total - diff) % 2) != 0) return 0

    return targetSumWithSymbolsHelper(n - 1, target, array)
}

fun targetSumWithSymbolsHelper(ind: Int, target: Int, array: IntArray): Int {

    //removed the base case of target == 0, becoz that was not covering the zero on starting index, just let it travel till last index for counting that subset

    //base case
    if (ind == 0) {
        if (target == 0 && array[0] == 0) return 2 // there are 2 ways, either we can take zero or not, ti does not make any different if target is alse zer
        if (target == 0 || target == array[0]) return 1

        return 0
    }

    //not take
    val notTake = targetSumWithSymbolsHelper(ind - 1, target, array)

    //take
    var take = 0
    if (array[ind] <= target) take = targetSumWithSymbolsHelper(ind - 1, target - array[ind], array)

    return (take + notTake)
}

// Using Memoization
// TC: O(n*k) => dp state
// SC: O(n*k) + O(n)
fun targetSumWithSymbolsMemo(n: Int, diff: Int, array: IntArray): Int {

    val total = array.sum()

    // If there are 2 subsets sums s1, s2
    // derived from if s1 - s2 = diff => total - s2 - s2 = diff => 2*s2 = total - diff => s2 = (total - diff) / 2
    val target = (total - diff) / 2

    // edge cases
    if (total - diff < 0 || ((total - diff) % 2) != 0) return 0

    val dp = Array(n + 1) {
        IntArray(target + 1) {
            -1
        }
    }

    return targetSumWithSymbolsMemoHelper(n - 1, target, array, dp)
}

fun targetSumWithSymbolsMemoHelper(ind: Int, target: Int, array: IntArray, dp: Array<IntArray>): Int {

    if (ind == 0) {
        if (target == 0 && array[ind] == 0) return 2 // there are 2 ways, either we can take zero or not, ti does not make any different if target is alse zer
        if (target == 0 || target == array[ind]) return 1

        return 0
    }

    if (dp[ind][target] != -1) return dp[ind][target]

    //not take
    val notTake = targetSumWithSymbolsMemoHelper(ind - 1, target, array, dp)

    //take
    var take = 0
    if (array[ind] <= target) take = targetSumWithSymbolsMemoHelper(ind - 1, target - array[ind], array, dp)

    dp[ind][target] = take + notTake
    return dp[ind][target]
}

// Using Tabulation
fun targetSumWithSymbolsTabulation(diff: Int, array: IntArray): Int {

    val total = array.sum()

    // If there are 2 subsets sums s1, s2
    // derived from if s1 - s2 = diff => total - s2 - s2 = diff => 2*s2 = total - diff => s2 = (total - diff) / 2
    val k = (total - diff) / 2

    // edge cases
    if (total - diff < 0 || ((total - diff) % 2) != 0) return 0

    val n = array.size
    val dp = Array(n + 1) {
        IntArray(k + 1) {
            0
        }
    }

    //base case for target 0, It means ind can be 0 and target can be any other from target, target-1... to 0, but we need to return only for array[0]
    //if (ind == 0) return array[ind] == target
    if (array[0] == 0) dp[0][0] =
        2  // there are 2 ways, either we can take zero or not, ti does not make any different if target is alse zer
    else dp[0][0] = 1 // it means array[0] != 0 and target == 0, so only one way to not take becoz target is zero

    //for target not zero
    if (array[0] != 0 && array[0] <= k) dp[0][array[0]] = 1

    for (ind in 1 until n) {
        for (target in 0..k) {
            //not take
            val notTake = dp[ind - 1][target]

            //take
            var take = 0
            if (array[ind] <= target) take = dp[ind - 1][target - array[ind]]

            dp[ind][target] = (take + notTake) % moduloNumber
        }
    }

    return dp[n - 1][k] // becoz we started recursion from n-1, k
}


fun targetSumWithSymbolsTabuSpaceOpt(diff: Int, array: IntArray): Int {

    val n = array.size

    val total = array.sum()

    // If there are 2 subsets sums s1, s2
    // derived from if s1 - s2 = diff => total - s2 - s2 = diff => 2*s2 = total - diff => s2 = (total - diff) / 2
    val k = (total - diff) / 2 //target

    // edge cases
    if (total - diff < 0 || ((total - diff) % 2) != 0) return 0


    //use array indexes for index and values just for target match
    var prev = IntArray(k + 1) {
        0 //use 0 here as we are counting
    }

    //base case for target 0, It means ind can be 0 and target can be any other from target, target-1... to 0, but we need to return only for array[0]
    //if (ind == 0) return array[ind] == target
    if (array[0] == 0) prev[0] =
        2  // there are 2 ways, either we can take zero or not, ti does not make any different if target is alse zer
    else prev[0] = 1 // it means array[0] != 0 and target == 0, so only one way to not take becoz target is zero


    //for target not zero
    if (array[0] != 0 && array[0] <= k) prev[array[0]] = 1

    for (ind in 1 until n) {

        val curr = IntArray(k + 1) {
            -1
        }
        curr[0] = 1 //for some value target has to be true

        for (target in 0..k) {

            //not take
            val notTake = prev[target]

            //take
            var take = 0
            if (array[ind] <= target) take = prev[target - array[ind]]

            curr[target] = (take + notTake) % moduloNumber
        }

        prev = curr
    }

    return prev[k] // becoz we started recursion from n-1, k
}

fun main() {

    /*    val arr = intArrayOf(5, 2, 6, 4)
        val difference = 3    */

    val arr = intArrayOf(1, 1, 1, 1, 1)
    val difference = 3

    println(targetSumWithSymbols(arr.size, diff = difference, array = arr))
    println(targetSumWithSymbolsMemo(arr.size, diff = difference, array = arr))
    println(targetSumWithSymbolsTabulation(diff = difference, array = arr))
    println(targetSumWithSymbolsTabuSpaceOpt(diff = difference, array = arr))

}