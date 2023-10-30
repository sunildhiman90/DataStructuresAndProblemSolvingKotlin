package problemsolving.dp.subsequenceOrSubsets

//https://www.codingninjas.com/studio/problems/minimum-elements_3843091?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf

val largeNumber = Math.pow(10.toDouble(), 9.toDouble()).toInt()
fun minimumCoins(ind: Int, target: Int, coins: IntArray): Int {


    var ans = minimumCoinsHelper(ind, target, coins)
    if (ans >= largeNumber) ans = -1
    return ans
}

fun minimumCoinsHelper(ind: Int, target: Int, coins: IntArray): Int {

    if (ind == 0) {
        if (target % coins[0] == 0) return target / coins[0]
        else largeNumber // else there can eb any combinations
    }

    val notTake = 0 + minimumCoinsHelper(ind - 1, target, coins)

    var take = Int.MAX_VALUE
    if (target >= coins[ind])
        take = 1 + minimumCoinsHelper(
            ind,
            target - coins[ind],
            coins
        ) //take one coin, but call with same index as it can be repeated

    return Math.min(notTake, take)

}

fun minimumCoinsMemo(ind: Int, target: Int, coins: IntArray): Int {

    val n = coins.size
    val dp = Array(n) {
        IntArray(target + 1) {
            -1
        }
    }

    var ans = minimumCoinsMemoHelper(ind, target, coins, dp)
    if (ans >= largeNumber) ans = -1
    return ans
}

fun minimumCoinsMemoHelper(ind: Int, target: Int, coins: IntArray, dp: Array<IntArray>): Int {

    if (ind == 0) {
        if (target % coins[0] == 0) return target / coins[0]
        else largeNumber // else there can eb any combinations
    }

    val notTake = 0 + minimumCoinsMemoHelper(ind - 1, target, coins, dp)

    if (dp[ind][target] != -1) return dp[ind][target]

    var take = Int.MAX_VALUE
    if (target >= coins[ind])
        take = 1 + minimumCoinsMemoHelper(
            ind,
            target - coins[ind],
            coins,
            dp
        ) //take one coin, but call with same index as it can be repeated

    dp[ind][target] = Math.min(notTake, take)
    return dp[ind][target]

}

fun minimumCoinsTabu(targetMax: Int, coins: IntArray): Int {

    val n = coins.size
    val dp = Array(n) {
        IntArray(targetMax + 1) {
            0
        }
    }

    for (t in 0..targetMax) {
        dp[0][t] = if (t % coins[0] == 0) t / coins[0] else largeNumber
    }

    for (ind in 1 until n) {
        for (target in 0..targetMax) {

            val notTake = 0 + dp[ind - 1][target]

            var take = Int.MAX_VALUE
            if (target >= coins[ind])
                take = 1 + dp[ind][target - coins[ind]] //take one coin, but call with same index as it can be repeated

            dp[ind][target] = Math.min(notTake, take)
        }
    }

    var ans = dp[n - 1][targetMax]
    if (ans >= largeNumber) ans = -1
    return ans

}

fun minimumCoinsTabuSpaceOpt(targetMax: Int, coins: IntArray): Int {

    val n = coins.size
    var prev = IntArray(targetMax + 1) {
        0
    }


    for (t in 0..targetMax) {
        prev[t] = if (t % coins[0] == 0) t / coins[0] else largeNumber
    }

    for (ind in 1 until n) {

        val curr = IntArray(targetMax + 1) {
            0
        }

        for (target in 0..targetMax) {

            val notTake = 0 + prev[target]

            var take = Int.MAX_VALUE
            if (target >= coins[ind])
                take = 1 + curr[target - coins[ind]] //take one coin, but call with same index as it can be repeated

            curr[target] = Math.min(notTake, take)
        }

        prev = curr
    }

    var ans = prev[targetMax]
    if (ans >= largeNumber) ans = -1
    return ans

}

fun main() {
    val coins = intArrayOf(1, 2, 3)
    val target = 7
    val n = coins.size
    println(minimumCoins(ind = n - 1, target = target, coins = coins))
    println(minimumCoinsMemo(ind = n - 1, target = target, coins = coins))
    println(minimumCoinsTabu(targetMax = target, coins = coins))
    println(minimumCoinsTabuSpaceOpt(targetMax = target, coins = coins))
}