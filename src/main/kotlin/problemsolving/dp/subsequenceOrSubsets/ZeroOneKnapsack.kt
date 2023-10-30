package problemsolving.dp.subsequenceOrSubsets

// https://www.codingninjas.com/studio/problems/0-1-knapsack_920542?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf


// Using REcursion:
// TC: O(2.pow(n))
// SC: O(n)
fun zeroOneKnapsack(ind: Int, w: Int, wt: IntArray, value: IntArray): Int {

    return zeroOneKnapsackHelper(ind, w, wt, value)

}

fun zeroOneKnapsackHelper(ind: Int, w: Int, wt: IntArray, value: IntArray): Int {

    // base case
    if (ind == 0) {
        if (wt[0] <= w) return value[0] //only take if wt[0] <= w
        else return 0
    }

    val notTake = zeroOneKnapsackHelper(ind - 1, w, wt, value) // not taking means, not adding anything
    var take = Int.MIN_VALUE
    if (wt[ind] <= w) {
        take = value[ind] + zeroOneKnapsackHelper(ind - 1, w - wt[ind], wt, value)
    }

    return Math.max(take, notTake)
}


// Using Memo:
// TC: O(n*w)
// SC: O(n*w) + O(n) => state + recursion stack
fun zeroOneKnapsackMemo(ind: Int, w: Int, wt: IntArray, value: IntArray): Int {

    val n = wt.size
    val dp = Array(n) {
        IntArray(w + 1) {
            -1
        }
    }

    return zeroOneKnapsackMemoHelper(ind, w, wt, value, dp)

}

fun zeroOneKnapsackMemoHelper(ind: Int, w: Int, wt: IntArray, value: IntArray, dp: Array<IntArray>): Int {

    // base case
    if (ind == 0) {
        if (wt[0] <= w) return value[0] //only take if wt[0] <= w
        else return 0
    }

    if (dp[ind][w] != -1) return dp[ind][w]

    val notTake = zeroOneKnapsackMemoHelper(ind - 1, w, wt, value, dp) // not taking means, not adding anything
    var take = Int.MIN_VALUE
    if (wt[ind] <= w) {
        take = value[ind] + zeroOneKnapsackMemoHelper(ind - 1, w - wt[ind], wt, value, dp)
    }

    dp[ind][w] = Math.max(take, notTake)
    return dp[ind][w]
}


fun zeroOneKnapsackTabu(maxWeight: Int, wt: IntArray, value: IntArray): Int {


    val n = wt.size
    val dp = Array(n) {
        IntArray(maxWeight + 1) {
            0
        }
    }

    // base case
    for (i in wt[0]..maxWeight) dp[0][i] = value[0] //only take all weights from wt[ind] to w

    for (ind in 1 until n) {
        for (w in 0..maxWeight) {
            val notTake = dp[ind - 1][w] // not taking means, not adding anything
            var take = Int.MIN_VALUE
            if (wt[ind] <= w) {
                take = value[ind] + dp[ind - 1][w - wt[ind]]
            }

            dp[ind][w] = Math.max(take, notTake)
        }
    }

    return dp[n - 1][maxWeight]
}

fun zeroOneKnapsackTabuSpaceOpt(maxWeight: Int, wt: IntArray, value: IntArray): Int {

    val n = wt.size
    var prev = IntArray(maxWeight + 1) {
        0
    }

    // base case
    for (i in wt[0]..maxWeight) prev[i] = value[0] //only take all weights from wt[ind] to w

    for (ind in 1 until n) {

        val curr = IntArray(maxWeight + 1) {
            0
        }

        for (w in maxWeight downTo 0) {
            val notTake = 0 + prev[w] // not taking means, not adding anything
            var take = Int.MIN_VALUE
            if (wt[ind] <= w) {
                take = value[ind] + prev[w - wt[ind]]
            }

            curr[w] = Math.max(take, notTake)
        }

        prev = curr
    }

    return prev[maxWeight]
}

fun zeroOneKnapsackTabuSpaceOpt2(maxWeight: Int, wt: IntArray, value: IntArray): Int {

    // We can omit curr array, becoz we can directly update in prev array, becoz we can traverse loop from max weight to 0(reverse) and it requires prev values only not prev row

    val n = wt.size
    var prev = IntArray(maxWeight + 1) {
        0
    }

    // base case
    for (i in wt[0]..maxWeight) prev[i] = value[0] //only take all weights from wt[ind] to w

    for (ind in 1 until n) {

        for (w in maxWeight downTo 0) {
            val notTake = prev[w] // not taking means, not adding anything
            var take = Int.MIN_VALUE
            if (wt[ind] <= w) {
                take = value[ind] + prev[w - wt[ind]]
            }

            prev[w] = Math.max(take, notTake)
        }

    }

    return prev[maxWeight]
}

fun main() {

    val wt = intArrayOf(1, 2, 4, 5)
    val values = intArrayOf(5, 4, 8, 6)
    val maxWeight = 5
    val n = 4

    println(zeroOneKnapsack(n - 1, w = maxWeight, wt = wt, value = values))
    println(zeroOneKnapsackMemo(n - 1, w = maxWeight, wt = wt, value = values))
    println(zeroOneKnapsackTabu(maxWeight = maxWeight, wt = wt, value = values))
    println(zeroOneKnapsackTabuSpaceOpt(maxWeight = maxWeight, wt = wt, value = values))
    println(zeroOneKnapsackTabuSpaceOpt2(maxWeight = maxWeight, wt = wt, value = values))
}
