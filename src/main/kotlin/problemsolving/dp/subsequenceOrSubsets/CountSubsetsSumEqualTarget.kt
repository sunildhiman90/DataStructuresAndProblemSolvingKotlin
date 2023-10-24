package problemsolving.dp.subsequenceOrSubsets

//https://www.codingninjas.com/studio/problems/count-subsets-with-sum-k_3952532?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf

// Using Recursion
// TC: O(2.pow(n))
// SC: O(n)
fun countSubsetSum(n: Int, k: Int, array: IntArray): Int {
    return countSubsetSumHelper(n - 1, k, array)
}

fun countSubsetSumHelper(ind: Int, target: Int, array: IntArray): Int {

    // base case 1
    if (target == 0) return 1

    //base case 2
    if (ind == 0) return if (array[ind] == target) 1 else 0

    //not take
    val notTake = countSubsetSumHelper(ind - 1, target, array)

    //take
    var take = 0
    if (array[ind] <= target)
        take = countSubsetSumHelper(ind - 1, target - array[ind], array)

    return take + notTake
}

// Using Memoization
// TC: O(n*k) => dp state
// SC: O(n*k) + O(n)
fun countSubsetSumMemo(n: Int, k: Int, array: IntArray): Int {

    val dp = Array(n + 1) {
        IntArray(k + 1) {
            -1
        }
    }

    return countSubsetSumMemoHelper(n - 1, k, array, dp)
}

fun countSubsetSumMemoHelper(ind: Int, target: Int, array: IntArray, dp: Array<IntArray>): Int {

    // base case 1
    if (target == 0) return 1

    //base case 2
    if (ind == 0) return if (array[ind] == target) 1 else 0

    if (dp[ind][target] != -1) return dp[ind][target]

    //not take
    val notTake = countSubsetSumMemoHelper(ind - 1, target, array, dp)

    //take
    var take = 0
    if (array[ind] <= target)
        take = countSubsetSumMemoHelper(ind - 1, target - array[ind], array, dp)

    dp[ind][target] = take + notTake
    return dp[ind][target]
}

// Using Tabulation
fun countSubsetSumTabulation(k: Int, array: IntArray): Int {

    val n = array.size
    val dp = Array(n + 1) {
        IntArray(k + 1) {
            0
        }
    }

    // base case 1 // It means target can be 0 and index can be any other from 0 to n-1
    //if (target == 0) return true
    for (i in 0 until n) dp[i][0] = 1

    //base case 2, It means ind can be 0 and target can be any other from target, target-1... to 0, but we need to return only for array[0]
    //if (ind == 0) return array[ind] == target
    if (array[0] <= k) dp[0][array[0]] = 1

    for (ind in 1 until n) {
        for (target in 0..k) {
            //not take
            val notTake = dp[ind - 1][target]

            //take
            var take = 0
            if (array[ind] <= target)
                take = dp[ind - 1][target - array[ind]]

            dp[ind][target] = take + notTake
        }
    }

    return dp[n - 1][k] // becoz we started recursion from n-1, k
}


fun countSubsetSumTabuSpaceOpt(k: Int, array: IntArray): Int {

    val n = array.size

    //use array indexes for index and values just for target match
    var prev = IntArray(k + 1) {
        0 //use 0 here as we are counting
    }

    // base case 1 // It means target can be 0 and index can be any other from 0 to n-1
    //if (target == 0) return true
    //for (i in 0 until n) dp[i][0] = true
    prev[0] = 1 //, becoz dp[index] will become prev

    //base case 2, It means ind can be 0 and target can be any other from target, target-1... to 0, but we need to return only for array[0]
    //if (ind == 0) return array[ind] == target
    //dp[0][array[0]] = true
    if (array[0] <= k) prev[array[0]] = 1

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
            if (array[ind] <= target)
                take = prev[target - array[ind]]

            curr[target] = take + notTake
        }

        prev = curr
    }

    return prev[k] // becoz we started recursion from n-1, k
}

fun main() {

    val arr = intArrayOf(1, 1, 4, 5)
    val k = 5

    println(countSubsetSum(arr.size, k = k, array = arr))
    println(countSubsetSumMemo(arr.size, k = k, array = arr))
    println(countSubsetSumTabulation(k = k, array = arr))
    println(countSubsetSumTabuSpaceOpt(k = k, array = arr))

}