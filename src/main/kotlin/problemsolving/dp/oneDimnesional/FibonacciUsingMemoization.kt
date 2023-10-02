package problemsolving.dp.oneDimnesional

// Simple recursion has 2 pow(n) => 2 raised to power n
// With Recursion by Memorization
// TC: O(n)
// SC: O(n) for recursion stack + O(n) for dp array
fun fibUsingMemo(n: Int): Int {
    val dp = IntArray(n + 1) {
        -1
    }
    return fibHelper(n, dp)
}

fun fibHelper(n: Int, dp: IntArray): Int {
    if (n <= 1) return n

    //Using DP to use the solution
    if (dp[n] != -1) {
        return dp[n]
    }

    //Using DP to save the solution
    dp[n] = fibHelper(n - 1, dp) + fibHelper(n - 2, dp)
    return dp[n]
}

fun main() {

    println(fibUsingMemo(6))
}