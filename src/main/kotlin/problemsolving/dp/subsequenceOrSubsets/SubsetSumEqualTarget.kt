package problemsolving.dp.subsequenceOrSubsets

//https://www.codingninjas.com/studio/problems/subset-sum-equal-to-k_1550954?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf

// Using Recursion
// TC: O(2.pow(n))
// SC: O(n)
fun subsetSum(n: Int, k: Int, array: IntArray): Boolean {
    return subsetSumHelper(n - 1, k, array)
}

fun subsetSumHelper(ind: Int, target: Int, array: IntArray): Boolean {

    // base case 1
    if (target == 0) return true

    //base case 2
    if (ind == 0) return array[ind] == target

    //not take
    val notTake = subsetSumHelper(ind - 1, target, array)

    //take
    var take = false
    if (array[ind] <= target)
        take = subsetSumHelper(ind - 1, target - array[ind], array)

    return take || notTake
}

// Using Memoization
// TC: O(n*k) => dp state
// SC: O(n*k) + O(n)
fun subsetSumMemo(n: Int, k: Int, array: IntArray): Boolean {

    val dp = Array(n + 1) {
        BooleanArray(n + 1) {
            false
        }
    }

    return subsetSumMemoHelper(n - 1, k, array, dp)
}

fun subsetSumMemoHelper(ind: Int, target: Int, array: IntArray, dp: Array<BooleanArray>): Boolean {

    // base case 1
    if (target == 0) return true

    //base case 2
    if (ind == 0) return array[ind] == target

    if (dp[ind][target]) return true

    //not take
    val notTake = subsetSumHelper(ind - 1, target, array)

    //take
    var take = false
    if (array[ind] <= target)
        take = subsetSumHelper(ind - 1, target - array[ind], array)

    dp[ind][target] = take || notTake
    return dp[ind][target]
}

// Using Tabulation
fun subsetSumTabulation(k: Int, array: IntArray): Boolean {

    val n = array.size
    val dp = Array(n + 1) {
        BooleanArray(n + 1) {
            false
        }
    }

    // base case 1 // It means target can be 0 and index can be any other from 0 to n-1
    //if (target == 0) return true
    for (i in 0 until n) dp[i][0] = true

    //base case 2, It means ind can be 0 and target can be any other from target, target-1... to 0, but we need to return only for array[0]
    //if (ind == 0) return array[ind] == target
    dp[0][array[0]] = true

    for (ind in 1 until n) {
        for (target in 1..k) {
            //not take
            val notTake = dp[ind - 1][target]

            //take
            var take = false
            if (array[ind] <= target)
                take = dp[ind - 1][target - array[ind]]

            dp[ind][target] = take || notTake
        }
    }

    return dp[n - 1][k] // becoz we started recursion from n-1, k
}


fun subsetSumTabuSpaceOpt(k: Int, array: IntArray): Boolean {

    val n = array.size

    //use array indexes for index and values just for target match
    var prev = BooleanArray(n + 1) {
        false
    }


    // base case 1 // It means target can be 0 and index can be any other from 0 to n-1
    //if (target == 0) return true
    //for (i in 0 until n) dp[i][0] = true
    prev[0] = true //, becoz dp[index] will become prev


    //base case 2, It means ind can be 0 and target can be any other from target, target-1... to 0, but we need to return only for array[0]
    //if (ind == 0) return array[ind] == target
    //dp[0][array[0]] = true
    prev[array[0]] = true

    for (ind in 1 until n) {

        val curr = BooleanArray(n + 1) {
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

    return prev[k] // becoz we started recursion from n-1, k
}

fun main() {

    val arr = intArrayOf(1, 2, 3, 4)
    val k = 4

    println(subsetSum(arr.size, k = k, array = arr))
    println(subsetSumMemo(arr.size, k = k, array = arr))
    println(subsetSumTabulation(k = k, array = arr))
    println(subsetSumTabuSpaceOpt(k = k, array = arr))

}
