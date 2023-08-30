package problemsolving.recursion_backtracking.backtracking

//https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem

class RatInMaze {
}

fun findPath(m: Array<IntArray>, n: Int): List<String?> {
    val output = mutableListOf<String>()
    val visited = Array(n) { BooleanArray(n) }
    if (m[0][0] == 1) {
        helper(0, 0, m, n, "", output, visited)
    }
    return output.toList()
}

// i is for row, j is for column
fun helper(
    i: Int,
    j: Int,
    input: Array<IntArray>,
    n: Int,
    current: String,
    output: MutableList<String>,
    visited: Array<BooleanArray>
) {
    if (i == n - 1 && j == n - 1) {
        output.add(current) //backtrack
        return
    }

    // check downward
    if (i + 1 <= n - 1 && !visited[i + 1][j] && input[i + 1][j] == 1) {
        visited[i][j] = true
        helper(i + 1, j, input, n, current + "D", output, visited)
        visited[i][j] = false
    }

    // check left
    if (j - 1 >= 0 && !visited[i][j - 1] && input[i][j - 1] == 1) {
        visited[i][j] = true
        helper(i, j - 1, input, n, current + "L", output, visited)
        visited[i][j] = false
    }

    // check right
    if (j + 1 <= n - 1 && !visited[i][j + 1] && input[i][j + 1] == 1) {
        visited[i][j] = true
        helper(i, j + 1, input, n, current + "R", output, visited)
        visited[i][j] = false
    }

    // check upward
    if (i - 1 >= 0 && !visited[i - 1][j] && input[i - 1][j] == 1) {
        visited[i][j] = true
        helper(i - 1, j, input, n, current + "U", output, visited)
        visited[i][j] = false
    }
}

fun main() {
    val n = 4
    val input = arrayOf(
        intArrayOf(1, 0, 0, 0),
        intArrayOf(1, 1, 0, 1),
        intArrayOf(1, 1, 0, 0),
        intArrayOf(0, 1, 1, 1)
    )

    println(findPath(input, n))
}