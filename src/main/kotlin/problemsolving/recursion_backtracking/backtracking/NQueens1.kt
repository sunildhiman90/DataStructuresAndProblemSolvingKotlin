package problemsolving.recursion_backtracking.backtracking

// https://leetcode.com/problems/n-queens/
class NQueens1 {
}

// check in all 8 directions
fun isSafe(row: Int, col: Int, board: Array<Array<Char>>): Boolean {

    //vertical(up and down), for col, check in all rows
    for (i in 0 until board.size) {
        if (board[i][col] == 'Q') {
            return false
        }
    }

    //horizontal(left and right), for row, check in all columns
    for (j in 0 until board.size) {
        if (board[row][j] == 'Q') {
            return false
        }
    }

    //diagonal(upper left)
    var r = row
    var c = col
    while (c >= 0 && r >= 0) {
        if (board[r][c] == 'Q') {
            return false
        }
        c--
        r--
    }


    //diagonal(upper right)
    r = row
    c = col
    while (c <= board.size - 1 && r >= 0) {
        if (board[r][c] == 'Q') {
            return false
        }
        c++
        r--
    }

    //diagonal(bottom left)
    r = row
    c = col
    while (c >= 0 && r <= board.size - 1) {
        if (board[r][c] == 'Q') {
            return false
        }
        c--
        r++
    }

    //diagonal(bottom right)
    r = row
    c = col
    while (c <= board.size - 1 && r <= board.size - 1) {
        if (board[r][c] == 'Q') {
            return false
        }
        c++
        r++
    }

    return true
}

fun helper(col: Int, board: Array<Array<Char>>, allBoards: MutableList<List<String>>) {

    if (col == board.size) {
        allBoards.add(board.map { it.joinToString("") }.toList())
        return
    }

    // loop over rows
    for (row in board.indices) {

        if (isSafe(row, col, board)) {
            board[row][col] = 'Q' //add
            helper(col = col + 1, board = board, allBoards = allBoards)
            board[row][col] = '.' //remove
        }

    }

}

fun solveNQueens(n: Int): List<List<String>> {
    val allBoards = mutableListOf<List<String>>()

    //create array with all dots initially, we will place Q while found solutions
    val board = Array(n) {
        Array(n) { '.' }
    }

    helper(col = 0, board = board, allBoards = allBoards)

    return allBoards.toList()
}

fun main() {

    println(solveNQueens(4))

}