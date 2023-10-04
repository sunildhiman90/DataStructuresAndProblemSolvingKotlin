package problemsolving.dp.twoDimensional

// https://leetcode.com/problems/minimum-path-sum/description/

val largeNumber = Math.pow(10.toDouble(), 9.toDouble()).toInt()
//use 1000000000, becoz if we use Int.MAX_VALUE, it will give some negative value after adding some value to max value,
// so logic will not work, that value will become lesser than other value in comparison

//OR alternatively if recursion call return Int.MAX_VALUE, in that case just return Int.MAX_VALUE and dont add in grid[i][j]


// Using MeRecursion, top-down
// i is row, j is column,
// TC: O(2.pow(m*n)), SC: O((m-1) + (n-1))
fun gridMinPathSumRec(grid: Array<IntArray>): Int {
    val m = grid.size
    val n = grid[0].size

    return gridMinPathSumRecHelper(m - 1, n - 1, grid)
}


fun gridMinPathSumRecHelper(i: Int, j: Int, grid: Array<IntArray>): Int {

    //success case
    if (i == 0 && j == 0) {
        return grid[i][j]
        //if no value is give for each grid, use 1 for picking the value and 0 for not picking ,
        // but if some value is given , then use that value and also in case of left or right or (whtever is given), in that case use current + recursive value
    }

    //failure case, if we go beyond initial point
    if (i < 0 || j < 0) {
        return largeNumber //due to becoz we need min path sum
        //in case of minimum path sum, use Int.MAX_VALUE here, so that, we can choose another value that will be minimum in case of getting minimum condition
    }

    //in problem, its given that w3 can traverse only in right or down direction,
    // But we are using top down approach using recursion, so it will be opposite -> left or top
    var left =
        grid[i][j] + gridMinPathSumRecHelper(
            i - 1,
            j,
            grid
        ) //paths from left
    var up = grid[i][j] + gridMinPathSumRecHelper(
        i,
        j - 1,
        grid
    ) // paths from top

    return Math.min(left, up) //return min of two

}


//Using Memoization
// TC: O(m*n), SC: O((m-1) + (n-1)) + O(m*n) for dp
fun gridMinPathSumMemo(grid: Array<IntArray>): Int {

    val m = grid.size
    val n = grid[0].size

    val dp = Array(m) {
        IntArray(n) {
            -1
        }
    }

    return gridMinPathSumMemoHelper(m - 1, n - 1, dp, grid)
}

fun gridMinPathSumMemoHelper(i: Int, j: Int, dp: Array<IntArray>, grid: Array<IntArray>): Int {

    //success case
    if (i == 0 && j == 0) {
        return grid[i][j]
    }

    //failure case, if we go beyond initial point
    if (i < 0 || j < 0) {
        return Int.MAX_VALUE
    }

    if (dp[i][j] != -1) return dp[i][j]

    //in problem, its given that w3 can traverse only in right or down direction,
    // But we are using top down approach using recursion, so it will be opposite -> left or top
    val left = if (gridMinPathSumMemoHelper(
            i - 1,
            j,
            dp,
            grid
        ) == Int.MAX_VALUE
    ) Int.MAX_VALUE else grid[i][j] + gridMinPathSumMemoHelper(i - 1, j, dp, grid) //paths from left
    val up = if (gridMinPathSumMemoHelper(
            i,
            j - 1,
            dp,
            grid
        ) == Int.MAX_VALUE
    ) Int.MAX_VALUE else grid[i][j] + gridMinPathSumMemoHelper(i, j - 1, dp, grid) // paths from top

    dp[i][j] = Math.min(left, up)
    return dp[i][j] //return all unique path by adding both paths

}


//Using Tabulation
// TC: O(m*n), SC: O(m*n) for dp
fun gridMinPathSumTabulation(grid: Array<IntArray>): Int {

    val m = grid.size
    val n = grid[0].size


    val dp = Array(m) {
        IntArray(n) {
            -1
        }
    }

    for (i in 0 until m) {
        for (j in 0 until n) {

            if (i == 0 && j == 0) dp[i][j] = grid[i][j]
            else {
                val left = if (i > 0) grid[i][j] + dp[i - 1][j] else Int.MAX_VALUE
                val up = if (j > 0) grid[i][j] + dp[i][j - 1] else Int.MAX_VALUE
                dp[i][j] = Math.min(left, up)
            }
        }
    }

    return dp[m - 1][n - 1] //return all unique path by adding both paths

}


// Using Tabulation with space optimization
// TC: O(m*n), SC: O(m) for dp
fun gridMinPathSumTabuSpaceOpt(grid: Array<IntArray>): Int {

    val m = grid.size
    val n = grid[0].size


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

            if (i == 0 && j == 0) current[j] = grid[i][j]
            else {
                val left = if (i > 0) grid[i][j] + prev[j] else Int.MAX_VALUE
                val up = if (j > 0) grid[i][j] + current[j - 1] else Int.MAX_VALUE
                current[j] = Math.min(left, up)
            }
        }
        prev = current
    }

    return prev[n - 1] //return all unique path by adding both paths

}


fun main() {

    val grid = arrayOf(
        intArrayOf(1, 3, 1),
        intArrayOf(1, 5, 1),
        intArrayOf(4, 2, 1)
    )
    println(gridMinPathSumRec(grid))
    println(gridMinPathSumMemo(grid))
    println(gridMinPathSumTabulation(grid))
    println(gridMinPathSumTabuSpaceOpt(grid))

}