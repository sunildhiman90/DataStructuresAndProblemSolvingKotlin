package problemsolving.dp.subsequenceOrSubsets

//https://www.codingninjas.com/studio/problems/unbounded-knapsack_1215029?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf

// Using REcursion:
// TC: O(2.pow(n))
// SC: O(n)
fun unboundedZeroOneKnapsack(ind: Int, w: Int, wt: IntArray, value: IntArray): Int {

    return unboundedZeroOneKnapsackHelper(ind, w, wt, value)

}

fun unboundedZeroOneKnapsackHelper(ind: Int, w: Int, wt: IntArray, value: IntArray): Int {

    // base case
    if (ind == 0) {
        return (w / wt[0]) * value[0] //becoz we can select single item multiple times
    }

    val notTake = unboundedZeroOneKnapsackHelper(ind - 1, w, wt, value) // not taking means, not adding anything
    var take =
        Int.MIN_VALUE //if we need to use some value and then calculate sum or max, thn use Int.MIN_VALUE for max, and if we just need to count the ways, the use 0 here
    if (wt[ind] <= w) {
        take = value[ind] + unboundedZeroOneKnapsackHelper(ind, w - wt[ind], wt, value)
    }

    return Math.max(take, notTake)
}


// Using Memo:
// TC: O(n*w)
// SC: O(n*w) + O(n) => state + recursion stack
fun unboundedZeroOneKnapsackMemo(ind: Int, w: Int, wt: IntArray, value: IntArray): Int {

    val n = wt.size
    val dp = Array(n) {
        IntArray(w + 1) {
            -1
        }
    }

    return unboundedZeroOneKnapsackMemoHelper(ind, w, wt, value, dp)

}

fun unboundedZeroOneKnapsackMemoHelper(
    ind: Int,
    maxWeight: Int,
    wt: IntArray,
    value: IntArray,
    dp: Array<IntArray>
): Int {

    // base case
    if (ind == 0) {
        return (maxWeight / wt[0]) * value[0]
    }

    if (dp[ind][maxWeight] != -1) return dp[ind][maxWeight]

    val notTake =
        unboundedZeroOneKnapsackMemoHelper(ind - 1, maxWeight, wt, value, dp) // not taking means, not adding anything
    var take = Int.MIN_VALUE
    if (wt[ind] <= maxWeight) {
        take = value[ind] + unboundedZeroOneKnapsackMemoHelper(
            ind,
            maxWeight - wt[ind],
            wt,
            value,
            dp
        ) //use ind here not ind-1, becoz same element is repeating
    }

    dp[ind][maxWeight] = Math.max(take, notTake)
    return dp[ind][maxWeight]
}

fun unboundedZeroOneKnapsackTabu(maxWeight: Int, wt: IntArray, value: IntArray): Int {

    val n = wt.size
    val dp = Array(n) {
        IntArray(maxWeight + 1) {
            0
        }
    }

    // base case, instead of wt[0] just use 0 to maxWeight
    for (i in 0..maxWeight) {
        dp[0][i] = (i / wt[0]) * value[0] //only take all weights from wt[ind] to w
    }

    for (ind in 1 until n) {
        for (w in 0..maxWeight) {
            val notTake = dp[ind - 1][w] // not taking means, not adding anything
            var take = 0
            if (wt[ind] <= w) {
                take = value[ind] + dp[ind][w - wt[ind]]
            }

            dp[ind][w] = Math.max(take, notTake)
        }
    }

    return dp[n - 1][maxWeight]
}

fun unboundedZeroOneKnapsackTabuSpaceOpt(maxWeight: Int, wt: IntArray, value: IntArray): Int {

    val n = wt.size
    var prev = IntArray(maxWeight + 1) {
        0
    }

    // base case., instead of wt[0] just use 0 to maxWeight
    for (i in 0..maxWeight) {
        prev[i] = (i / wt[0]) * value[0] //only take all weights from wt[ind] to w
    }

    for (ind in 1 until n) {

        val curr = IntArray(maxWeight + 1) {
            0
        }

        for (w in 0..maxWeight) {
            val notTake = 0 + prev[w] // not taking means, not adding anything
            var take = Int.MIN_VALUE
            if (wt[ind] <= w) {
                take = value[ind] + curr[w - wt[ind]]
            }

            curr[w] = Math.max(take, notTake)
        }

        prev = curr
    }

    return prev[maxWeight]
}

fun unboundedZeroOneKnapsackTabuSpaceOpt2(maxWeight: Int, wt: IntArray, value: IntArray): Int {

    // We can omit curr array, becoz we can directly update in prev array, becoz we can traverse loop from max weight to 0(reverse) and it requires prev values only not prev row

    val n = wt.size
    var prev = IntArray(maxWeight + 1) {
        0
    }

    // base case, instead of wt[0] just use 0 to maxWeight
    for (i in 0..maxWeight) {
        prev[i] = (i / wt[0]) * value[0] //only take all weights from wt[ind] to w
    }

    for (ind in 1 until n) {

        for (w in 0..maxWeight) {
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

    val wt = intArrayOf(2, 4, 6)
    val values = intArrayOf(5, 11, 13)
    val maxWeight = 10
    val n = 3

    println(unboundedZeroOneKnapsack(n - 1, w = maxWeight, wt = wt, value = values))
    println(unboundedZeroOneKnapsackMemo(n - 1, w = maxWeight, wt = wt, value = values))
    println(unboundedZeroOneKnapsackTabu(maxWeight = maxWeight, wt = wt, value = values))
    println(unboundedZeroOneKnapsackTabuSpaceOpt(maxWeight = maxWeight, wt = wt, value = values))
    println(unboundedZeroOneKnapsackTabuSpaceOpt2(maxWeight = maxWeight, wt = wt, value = values))
}
