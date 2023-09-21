package problemsolving.dp

// Without recursion by Tabulation
// TC: O(n)
// SC: O(n) for dp array
fun fibUsingTabu(n: Int): Int {
    val dp = IntArray(n + 1) {
        -1
    }

    dp[0] = 0
    dp[1] = 1

    for (i in 2..n) {
        dp[i] = dp[i - 1] + dp[i - 2]
    }

    return dp[n]

}

// Without recursion by Tabulation, More space optimized
// TC: O(n)
// SC: O(1)
fun fibUsingTabuOptimized(n: Int): Int {

    var prev = 1
    var prev2 = 0

    for (i in 2..n) {
        val curr = prev + prev2
        prev2 = prev
        prev = curr
    }

    return prev

}

fun main() {

    println(fibUsingTabu(5))
    println(fibUsingTabuOptimized(5))
}