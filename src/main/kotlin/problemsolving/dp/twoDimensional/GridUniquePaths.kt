package problemsolving.dp.twoDimensional

// https://leetcode.com/problems/unique-paths

// Using Recursion, top-down
// i is row, j is column,
// TC: O(2.pow(m*n)), SC: O((m-1) + (n-1))
fun maxSumUniquePaths(i: Int, j: Int): Int {

    //success case
    if (i == 0 && j == 0) {
        return 1
    }

    //failure case, if we go beyond initial point
    if (i < 0 || j < 0) {
        return 0
    }

    //in problem, its given that w3 can traverse only in right or down direction,
    // But we are using top down approach using recursion, so it will be opposite -> left or top
    val left = maxSumUniquePaths(i - 1, j) //paths from left
    val up = maxSumUniquePaths(i, j - 1) // paths from top

    return left + up //return all unique path by adding both paths

}


//Using Memoization
// TC: O(m*n), SC: O((m-1) + (n-1)) + O(m*n) for dp
fun maxSumUniquePathsMemo(i: Int, j: Int): Int {

    val dp = Array(i) {
        IntArray(j) {
            -1
        }
    }

    return maxSumUniquePathsMemoHelper(i - 1, j - 1, dp)
}

fun maxSumUniquePathsMemoHelper(i: Int, j: Int, dp: Array<IntArray>): Int {

    //success case
    if (i == 0 && j == 0) {
        return 1
        //if no value is give for each grid, use 1 for picking the value and 0 for not picking ,
        // but if some value is given , then use that value and also in case of left or right or (whtever is given), in that case use current + recursive value
        // for example: https://leetcode.com/problems/minimum-path-sum/description/
    }

    //failure case, if we go beyond initial point
    if (i < 0 || j < 0) {
        return 0
        //in case of minimum path sum, use Int.MAX_VALUE here, so that, we can choose another value that will be minimum in case of getting minimum condition
        //Similarily in case of max path sum, use Int.MIN_VALUE here, so that, we can choose another value that will be maximum in case of getting maximum condition
    }

    if (dp[i][j] != -1) return dp[i][j]

    //in problem, its given that w3 can traverse only in right or down direction,
    // But we are using top down approach using recursion, so it will be opposite -> left or top
    val left = maxSumUniquePathsMemoHelper(i - 1, j, dp) //paths from left
    val up = maxSumUniquePathsMemoHelper(i, j - 1, dp) // paths from top

    dp[i][j] = left + up
    return dp[i][j] //return all unique path by adding both paths

}


//Using Tabulation
// TC: O(m*n), SC: O(m*n) for dp
fun maxSumUniquePathsTabulation(m: Int, n: Int): Int {

    val dp = Array(m) {
        IntArray(n) {
            -1
        }
    }

    for (i in 0 until m) {
        for (j in 0 until n) {

            if (i == 0 && j == 0) dp[i][j] = 1
            else {
                val left = if (i > 0) dp[i - 1][j] else 0
                val up = if (j > 0) dp[i][j - 1] else 0
                dp[i][j] = left + up
            }
        }
    }

    return dp[m - 1][n - 1] //return all unique path by adding both paths

}


// Using Tabulation with space optimization
// TC: O(m*n), SC: O(m) for dp
fun maxSumUniquePathsTabuSpaceOpt(m: Int, n: Int): Int {

    // here dp[i-1] become=> prev, so dp[i][j-1] will become current[j-1] and dp[i-1][j] will become prev[j]
    // dp[i] => current, So dp[i][j] will become current[j]

    var prev = Array(m) {
        -1
    }

    for (i in 0 until m) {
        val current = Array(n) {
            -1
        }
        for (j in 0 until n) {

            if (i == 0 && j == 0) current[j] = 1
            else {
                val left = if (i > 0) prev[j] else 0
                val up = if (j > 0) current[j - 1] else 0
                current[j] = left + up
            }
        }
        prev = current
    }

    return prev[n - 1] //return all unique path by adding both paths

}


fun main() {

    val m = 3
    val n = 7
    println(maxSumUniquePaths(m - 1, n - 1))
    println(maxSumUniquePathsMemo(m, n))
    println(maxSumUniquePathsTabulation(m, n))
    println(maxSumUniquePathsTabuSpaceOpt(m, n))

}