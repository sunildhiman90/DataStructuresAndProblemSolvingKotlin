package problemsolving.dp.`3d`

//https://www.codingninjas.com/studio/problems/chocolate-pickup_3125885?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf

//Using recursion
// TC: O(3.pow(n) * 3.pow(n))
// SC: O(n)
fun maxChocolates(r: Int, c: Int, grid: Array<IntArray>): Int {

    //i (row) will be common for both Alice and bob, only column will change, j1 = 0 for ALice, and j2 = c-1 for bob
    return maxChocolatesHelper(0, 0, c - 1, r, c, grid)

}

val largeNumber = -(Math.pow(10.toDouble(), 9.toDouble()).toInt())

fun maxChocolatesHelper(i: Int, j1: Int, j2: Int, r: Int, c: Int, grid: Array<IntArray>): Int {

    // r by c grid

    // bounding condition
    if (j1 < 0 || j1 > c - 1 || j2 < 0 || j2 > c - 1) return largeNumber

    //base case
    if (i == r - 1) {
        //if they both are at same position, use value once
        if (j1 == j2) return grid[i][j1]
        else return grid[i][j1] + grid[i][j2]
    }

    //step work done, for 9 ways of 3x3, we are using both recursions together, so we will have to cover all 9 conditions,
    // if suppose alice runs down, then for that case, bob can run in 3 directions, so for one movement of alice, bob runs 3 times, so for 3 movement, it will be 3x3 => 9
    // loop from -1 to 1 => -1, 0, 1 => left, down, right
    var max = largeNumber
    for (dj1 in -1..1) {
        for (dj2 in -1..1) {
            var value = 0
            if (j1 == j2) value = grid[i][j1]
            else value = grid[i][j1] + grid[i][j2]
            value += maxChocolatesHelper(i + 1, j1 + dj1, j2 + dj2, r, c, grid)
            max = Math.max(value, max)
        }
    }

    return max


}

// using Memoization
// TC: state complexity=> O(n*m*m)*9
// SC: O(n*m*m) + O(n)
fun maxChocolatesMemo(r: Int, c: Int, grid: Array<IntArray>): Int {

    //dp of r by c by c => dp[r][c][c]
    val dp = arrayOf(
        Array(r) {
            IntArray(c) {
                -1
            }
        }
    )

    //i (row) will be common for both Alice and bob, only column will change, j1 = 0 for ALice, and j2 = c-1 for bob
    return maxChocolatesMemoHelper(0, 0, c - 1, r, c, grid, dp)

}

fun maxChocolatesMemoHelper(
    i: Int,
    j1: Int,
    j2: Int,
    r: Int,
    c: Int,
    grid: Array<IntArray>,
    dp: Array<Array<IntArray>>
): Int {

    // bounding condition
    if (j1 < 0 || j1 > c - 1 || j2 < 0 || j2 > c - 1) return largeNumber

    //base case
    if (i == r - 1) {
        //if they both are at same position, use value once
        if (j1 == j2) return grid[i][j1]
        else return grid[i][j1] + grid[i][j2]
    }

    if (dp[i][j1][j2] != -1) return dp[i][j1][j2]

    //step work done, for 9 ways of 3x3, we are using both recursions together, so we will have to cover all 9 conditions,
    // if suppose alice runs down, then for that case, bob can run in 3 directions, so for one movement of alice, bob runs 3 times, so for 3 movement, it will be 3x3 => 9
    // loop from -1 to 1 => -1, 0, 1 => left, down, right
    var max = largeNumber
    for (dj1 in -1..1) {
        for (dj2 in -1..1) {
            var value = if (j1 == j2) grid[i][j1] else grid[i][j1] + grid[i][j2]
            value += maxChocolatesHelper(i + 1, j1 + dj1, j2 + dj2, r, c, grid)
            max = Math.max(value, max)
        }
    }

    dp[i][j1][j2] = max

    return dp[i][j1][j2]

}


//Using Tabulation
// TC: O(n*m*m) * 9
// SC: O(n*m*m)

//State reduction can be done as give below:
// 1D DP :- Array to 2 variables
// 2D DP:- 2D array to 1D array
// 3D DP:- 3D array to 2D array
fun maxChocolatesTabu(
    r: Int,
    c: Int,
    grid: Array<IntArray>,
): Int {


    val dp = Array(r) {
        Array(c) {
            IntArray(c) {
                -1
            }
        }
    }

    //base case for r-1
    for (j1 in 0 until c) {
        for (j2 in 0 until c) {
            //if they both are at same position, use value once
            if (j1 == j2) dp[r - 1][j1][j2] = grid[r - 1][j1]
            else dp[r - 1][j1][j2] = grid[r - 1][j1] + grid[r - 1][j2]
        }
    }


    for (i in r - 2 downTo 0) {

        //each step combination
        for (j1 in 0 until c) {
            for (j2 in 0 until c) {


                //step work done, for 9 ways of 3x3, we are using both recursions together, so we will have to cover all 9 conditions,
                // if suppose alice runs down, then for that case, bob can run in 3 directions, so for one movement of alice, bob runs 3 times, so for 3 movement, it will be 3x3 => 9
                // loop from -1 to 1 => -1, 0, 1 => left, down, right
                var max = largeNumber
                for (dj1 in -1..1) {
                    for (dj2 in -1..1) {
                        var value = if (j1 == j2) grid[i][j1] else grid[i][j1] + grid[i][j2]
                        if (j1 + dj1 >= 0 && j1 + dj1 <= c - 1 && j2 + dj2 >= 0 && j2 + dj2 <= c - 1)
                            value += dp[i + 1][j1 + dj1][j2 + dj2]
                        else value += largeNumber
                        max = Math.max(value, max)
                    }
                }

                dp[i][j1][j2] = max
            }
        }

    }


    return dp[0][0][c - 1] // becoz we started recursion from 0, 0, c-1

}


// Using Tabulation space optimization
// TC: O(n*m*m) * 9
// SC: O(m*m)
fun maxChocolatesTabuSpaceOpt(
    r: Int,
    c: Int,
    grid: Array<IntArray>,
): Int {

    // use dp[c][c] only , so dp[r+1][c][c] will become prev[c][c], becoz we are traversing from last to first, so i+1 means prev row

    var prev = Array(c) {
        IntArray(c) {
            -1
        }
    }


    //base case for r-1
    for (j1 in 0 until c) {
        for (j2 in 0 until c) {
            //if they both are at same position, use value once
            if (j1 == j2) prev[j1][j2] = grid[r - 1][j1]
            else prev[j1][j2] = grid[r - 1][j1] + grid[r - 1][j2]
        }
    }


    for (i in r - 2 downTo 0) {

        val curr = Array(c) {
            IntArray(c) {
                -1
            }
        }

        //each step combination
        for (j1 in 0 until c) {
            for (j2 in 0 until c) {


                //step work done, for 9 ways of 3x3, we are using both recursions together, so we will have to cover all 9 conditions,
                // if suppose alice runs down, then for that case, bob can run in 3 directions, so for one movement of alice, bob runs 3 times, so for 3 movement, it will be 3x3 => 9
                // loop from -1 to 1 => -1, 0, 1 => left, down, right
                var max = largeNumber
                for (dj1 in -1..1) {
                    for (dj2 in -1..1) {
                        var value = if (j1 == j2) grid[i][j1] else grid[i][j1] + grid[i][j2]
                        if (j1 + dj1 >= 0 && j1 + dj1 <= c - 1 && j2 + dj2 >= 0 && j2 + dj2 <= c - 1)
                            value += prev[j1 + dj1][j2 + dj2]
                        else value += largeNumber
                        max = Math.max(value, max)
                    }
                }

                curr[j1][j2] = max
            }
        }

        prev = curr

    }


    return prev[0][c - 1] // becoz we started recursion from 0, 0, c-1

}


fun main() {
    val r = 3
    val c = 4
    val arr = arrayOf(
        intArrayOf(2, 3, 1, 2),
        intArrayOf(3, 4, 2, 2),
        intArrayOf(5, 6, 3, 5)
    )

    println(maxChocolates(r, c, grid = arr))
    println(maxChocolatesMemo(r, c, grid = arr))
    println(maxChocolatesTabu(r, c, grid = arr))
    println(maxChocolatesTabuSpaceOpt(r, c, grid = arr))
}