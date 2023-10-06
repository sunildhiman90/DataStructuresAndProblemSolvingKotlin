package problemsolving.dp.twoDimensional


//https://leetcode.com/problems/minimum-falling-path-sum/

//Using Recursion
// TC: O(3 raised to power n) => exponential
// SC: O(n)
fun minFallingPathSumRec(matrix: Array<IntArray>): Int {
    val m = matrix.size
    val n = matrix[0].size

    var min = Int.MAX_VALUE
    // We can start from any cell in first row, so its opposite will be we can start from any cell in last row
    for (j in 0 until n) {
        min = Math.min(min, minFallingPathSumRecHelper(m - 1, j, matrix))
    }
    return min

    //return minFallingPathSumRecHelper(m - 1, n - 1, matrix)
}

// start from top(last index)
fun minFallingPathSumRecHelper(i: Int, j: Int, matrix: Array<IntArray>): Int {

    val m = matrix.size
    val n = matrix[0].size

    //out of bounds condition
    if (j < 0 || j > n - 1) {
        return Int.MAX_VALUE
    }

    //base case
    if (i == 0) return matrix[i][j]

    //in problem, its given that w3 can traverse only in rightdown, leftdown diagonal or down direction,
    // But we are using top down approach using recursion, so it will be opposite -> rightup, leftup and up

    //up
    var up =
        matrix[i][j] + minFallingPathSumRecHelper(
            i - 1,
            j,
            matrix
        )

    //left diagonal
    val ld = if (j > 0)
        matrix[i][j] + minFallingPathSumRecHelper(
            i - 1,
            j - 1,
            matrix
        ) else Int.MAX_VALUE

    //right diagonal
    val rd = if (j < n - 1) matrix[i][j] + minFallingPathSumRecHelper(
        i - 1,
        j + 1,
        matrix
    ) else Int.MAX_VALUE

    return Math.min(up, Math.min(ld, rd)) //return min of two

}


// Using Tabulation with Space Optimization
fun minFallingPathSum(matrix: Array<IntArray>): Int {

    val n = matrix.size

    var prev = Array(n) {
        -1
    }

    //all base cases for first row
    for (j in 0 until n) {
        prev[j] = matrix[0][j]
    }

    // for 0th row, we have already covered in base case, run from row 1 to last row
    for (i in 1 until n) {

        val curr = Array(n) {
            -1
        }

        // go through all columns
        for (j in 0 until n) {
            val up = matrix[i][j] + prev[j]
            val ld = if (j > 0) matrix[i][j] + prev[j - 1] else Int.MAX_VALUE
            val rd = if (j < n - 1) matrix[i][j] + prev[j + 1] else Int.MAX_VALUE
            curr[j] = Math.min(up, Math.min(ld, rd))
        }
        prev = curr
    }

    var min = Int.MAX_VALUE
    //for all multiple ending cases of (n-1, 0), (n-1, 1), (n-1, 2).... (n-1, n-1) for last row
    for (i in 0 until n) {
        min = Math.min(min, prev[i])
    }

    return min

}


fun main() {

    val matrix = arrayOf(
        intArrayOf(2, 1, 3),
        intArrayOf(6, 5, 4),
        intArrayOf(7, 8, 9)
    )

    println(minFallingPathSumRec(matrix))
    println(minFallingPathSum(matrix))
}