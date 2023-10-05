package problemsolving.dp.twoDimensional


// Using Recursion
// TC : O(2.pow(n))
// SC: O(n)
fun minPathSumTriangleRec(triangle: List<List<Int>>): Int {
    val n = triangle.size

    return minPathSumTriangleRecHelper(i = 0, j = 0, triangle = triangle, n = n)

}

// IN this case we will start from 0 instead of n-1, becoz we have n terminating conditions in the last, so
fun minPathSumTriangleRecHelper(i: Int, j: Int, triangle: List<List<Int>>, n: Int): Int {

    //n base cases
    if (i == n - 1) return triangle[n - 1][j]

    val down = triangle[i][j] + minPathSumTriangleRecHelper(i + 1, j, triangle, n)
    val diagonal = triangle[i][j] + minPathSumTriangleRecHelper(i + 1, j + 1, triangle, n)

    return Math.min(down, diagonal)

}

// Using Memoization
// TC: O(n*n) => due to array of states
// SC: O(n*n) + O(n) => dp array + stack space
fun minPathSumTriangleMemo(triangle: List<List<Int>>): Int {
    val n = triangle.size

    val dp = Array(n) {
        IntArray(n) {
            -1
        }
    }

    return minPathSumTriangleMemoHelper(i = 0, j = 0, triangle = triangle, n = n, dp)
}


fun minPathSumTriangleMemoHelper(i: Int, j: Int, triangle: List<List<Int>>, n: Int, dp: Array<IntArray>): Int {

    if (i == n - 1) return triangle[n - 1][j]

    if (dp[i][j] != -1) return dp[i][j]

    val down = triangle[i][j] + minPathSumTriangleRecHelper(i + 1, j, triangle, n)
    val diagonal = triangle[i][j] + minPathSumTriangleRecHelper(i + 1, j + 1, triangle, n)

    dp[i][j] = Math.min(down, diagonal)
    return dp[i][j]

}

// Using Tabulation: TC: O(n), SC: O(n*n), stack space is reduced now
// IN this case we will start from 0 instead of n-1, becoz we have n terminating conditions in the last, so
// Tabulation will be opposite of recursive call, we will start from n-1
fun minPathSumTriangleTabulation(triangle: List<List<Int>>): Int {

    val n = triangle.size

    val dp = Array(n) {
        IntArray(n) {
            -1
        }
    }

    // n bases cases of last row
    for (j in 0 until n) {
        dp[n - 1][j] = triangle[n - 1][j]
    }

    //i done for n-1 in base case, so start from n-2 to 0
    for (i in n - 2 downTo 0) {

        // j will start from i if check in the logic
        for (j in i downTo 0) {
            val down = triangle[i][j] + dp[i + 1][j]
            val diagonal = triangle[i][j] + dp[i + 1][j + 1]

            dp[i][j] = Math.min(down, diagonal)
        }
    }

    return dp[0][0] // becoz in recursion we started from 0,0

}


// Using Tabulation plus space optimization
// TC: O(n), SC: O(n)
fun minPathSumTriangleTabuSpaceOpt(triangle: List<List<Int>>): Int {

    val n = triangle.size

    // prev from last, means i+1 for i, loop is running in decreasing order
    // So dp[i+1] will become prev and dp[i+1][j]=> [j]
    var prev = Array(n) {
        -1
    }

    // n bases cases of last row
    for (j in 0 until n) {
        prev[j] = triangle[n - 1][j]
    }

    //i done for n-1 in base case, so start from n-2 to 0
    for (i in n - 2 downTo 0) {

        val curr = Array(i + 1) {
            -1
        }

        // j will start from i if check in the logic
        for (j in i downTo 0) {
            val down = triangle[i][j] + prev[j]
            val diagonal = triangle[i][j] + prev[j + 1]

            curr[j] = Math.min(down, diagonal)
        }

        prev = curr
    }

    return prev[0] // becoz in recursion we started from 0,0

}


fun main() {

    val triangle = mutableListOf(listOf(2), listOf(3, 4), listOf(6, 5, 7), listOf(4, 1, 8, 3))
    //val triangle = mutableListOf(listOf(-10))

    println(minPathSumTriangleRec(triangle))
    println(minPathSumTriangleMemo(triangle))
    println(minPathSumTriangleTabulation(triangle))
    println(minPathSumTriangleTabuSpaceOpt(triangle))
}
