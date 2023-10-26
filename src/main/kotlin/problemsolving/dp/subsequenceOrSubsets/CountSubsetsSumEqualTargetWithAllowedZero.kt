package problemsolving.dp.subsequenceOrSubsets

//https://www.codingninjas.com/studio/problems/count-subsets-with-sum-k_3952532?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf

// Using Recursion
// TC: O(2.pow(n))
// SC: O(n)
fun countSubsetSum2(n: Int, k: Int, array: IntArray): Int {
    return countSubsetSum2Helper(n - 1, k, array)
}

fun countSubsetSum2Helper(ind: Int, target: Int, array: IntArray): Int {


    //removed the base case of target == 0, becoz that was not covering the zero on starting index, just let it travel till last index for counting that subset

    //base case
    if (ind == 0) {
        if (target == 0 && array[0] == 0) return 2 // there are 2 ways, either we can take zero or not, ti does not make any different if target is alse zer
        if (target == 0 || target == array[0]) return 1

        return 0
    }

    //not take
    val notTake = countSubsetSum2Helper(ind - 1, target, array)

    //take
    var take = 0
    if (array[ind] <= target) take = countSubsetSum2Helper(ind - 1, target - array[ind], array)

    return take + notTake
}

// Using Memoization
// TC: O(n*k) => dp state
// SC: O(n*k) + O(n)
fun countSubsetSum2Memo(n: Int, k: Int, array: IntArray): Int {

    val dp = Array(n + 1) {
        IntArray(k + 1) {
            -1
        }
    }

    return countSubsetSum2MemoHelper(n - 1, k, array, dp)
}

fun countSubsetSum2MemoHelper(ind: Int, target: Int, array: IntArray, dp: Array<IntArray>): Int {

    if (ind == 0) {
        if (target == 0 && array[ind] == 0) return 2 // there are 2 ways, either we can take zero or not, ti does not make any different if target is alse zer
        if (target == 0 || target == array[ind]) return 1

        return 0
    }

    if (dp[ind][target] != -1) return dp[ind][target]

    //not take
    val notTake = countSubsetSum2MemoHelper(ind - 1, target, array, dp)

    //take
    var take = 0
    if (array[ind] <= target) take = countSubsetSum2MemoHelper(ind - 1, target - array[ind], array, dp)

    dp[ind][target] = take + notTake
    return dp[ind][target]
}

// Using Tabulation
fun countSubsetSum2Tabulation(k: Int, array: IntArray): Int {

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

            dp[ind][target] = take + notTake
        }
    }

    return dp[n - 1][k] // becoz we started recursion from n-1, k
}


fun countSubsetSum2TabuSpaceOpt(k: Int, array: IntArray): Int {

    val n = array.size

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

            curr[target] = take + notTake
        }

        prev = curr
    }

    return prev[k] // becoz we started recursion from n-1, k
}

fun main() {

    //val arr = intArrayOf(1, 1, 4, 5)
    val arr = intArrayOf(0, 0, 1)
    val k = 1

    println(countSubsetSum2(arr.size, k = k, array = arr))
    println(countSubsetSum2Memo(arr.size, k = k, array = arr))
    println(countSubsetSum2Tabulation(k = k, array = arr))
    println(countSubsetSum2TabuSpaceOpt(k = k, array = arr))

}