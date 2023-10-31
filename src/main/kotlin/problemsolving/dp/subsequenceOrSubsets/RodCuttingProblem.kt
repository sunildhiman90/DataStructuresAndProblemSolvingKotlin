package problemsolving.dp.subsequenceOrSubsets

//https://www.codingninjas.com/studio/problems/rod-cutting-problem_800284?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf


//The problem can be reversed , instead of cutting, we can assume collecting rod one piece each time

fun rodCuttingProblem(prices: IntArray, n: Int): Int {

    return rodCuttingProblemHelper(n - 1, prices, n)

}

fun rodCuttingProblemHelper(ind: Int, prices: IntArray, maxRodLength: Int): Int {


    if (ind == 0) {
        return maxRodLength * prices[0]
    }

    val notTake = rodCuttingProblemHelper(ind - 1, prices, maxRodLength)
    var take = 0
    val rodLength = ind + 1 //becoz 0 based indexing
    if (rodLength <= maxRodLength) {
        take = prices[ind] + rodCuttingProblemHelper(ind, prices, maxRodLength - rodLength)
    }

    return Math.max(take, notTake)

}

fun rodCuttingProblemTabu(prices: IntArray, maxRodLength: Int): Int {

    val n = prices.size
    val dp = Array(n) {
        IntArray(maxRodLength + 1) {
            0
        }
    }

    // base case,
    for (i in 0..maxRodLength) {
        dp[0][i] = i * prices[0]
    }

    for (ind in 1 until n) {
        for (N in 0..maxRodLength) {
            val notTake = dp[ind - 1][N] // not taking means, not adding anything
            var take = 0
            val rodLength = ind + 1
            if (rodLength <= N) {
                take = prices[ind] + dp[ind][N - rodLength]
            }

            dp[ind][N] = Math.max(take, notTake)
        }
    }

    return dp[n - 1][maxRodLength]
}

fun rodCuttingProblemTabuSpaceOpt(prices: IntArray, maxRodLength: Int): Int {

    val n = prices.size
    var prev = IntArray(maxRodLength + 1) {
        0
    }

    // base case,
    for (i in 0..maxRodLength) {
        prev[i] = i * prices[0]
    }

    for (ind in 1 until n) {
        val curr = IntArray(maxRodLength + 1) {
            0
        }
        for (N in 0..maxRodLength) {
            val notTake = prev[N] // not taking means, not adding anything
            var take = 0
            val rodLength = ind + 1
            if (rodLength <= N) {
                take = prices[ind] + curr[N - rodLength]
            }

            curr[N] = Math.max(take, notTake)
        }
        prev = curr
    }

    return prev[maxRodLength]
}


fun main() {

    val prices = intArrayOf(2, 5, 7, 8, 10)
    val maxLength = 5

    println(rodCuttingProblem(prices, maxLength))
    //println(rodCuttingProblemMemo(n - 1, w = maxWeight, wt = wt, value = values))
    println(rodCuttingProblemTabu(prices, maxLength))
    println(rodCuttingProblemTabuSpaceOpt(prices, maxLength))
}