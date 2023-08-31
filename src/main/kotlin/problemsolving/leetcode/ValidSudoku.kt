package problemsolving.leetcode

//https://leetcode.com/problems/valid-sudoku
fun validSudoku(board: Array<CharArray>): Boolean {
    return helper(board)
}

fun helper(board: Array<CharArray>): Boolean {

    for (i in board.indices) {

        for (j in board.indices) {

            if (board[i][j] != '.') {

                if (!checkValidSudoku(i, j, board, board[i][j])) return false

            }

        }

    }

    return true
}

fun checkValidSudoku(row: Int, col: Int, board: Array<CharArray>, num: Char): Boolean {
    var isValid = true
    //val numAsChar = Character.forDigit(num, 10)
    val numAsChar = num

    // check in column
    var i = 0
    while (i < board.size) {
        //except current char
        if (i != row) {
            if (board[i][col] == numAsChar) {
                isValid = false
                break
            }
        }
        i++
    }

    // check in row
    var j = 0
    while (j < board.size) {
        //except current char
        if (j != col) {
            if (board[row][j] == numAsChar) {
                isValid = false
                break
            }
        }

        j++
    }

    //check in 3x3 matrix
    var rowStart = 0
    var colStart = 0
    when (row) {
        in 0..2 -> {
            rowStart = 0
        }

        in 3..5 -> {
            rowStart = 3
        }

        in 6..8 -> {
            rowStart = 6
        }
    }

    when (col) {
        in 0..2 -> {
            colStart = 0
        }

        in 3..5 -> {
            colStart = 3
        }

        in 6..8 -> {
            colStart = 6
        }
    }

    for (rowIndex in rowStart until rowStart + 3) {
        for (colIndex in colStart until colStart + 3) {
            //except current char
            if (rowIndex != row && colIndex != col) {
                if (board[rowIndex][colIndex] == numAsChar) {
                    isValid = false
                    break
                }
            }

        }
    }

    return isValid

}

fun main() {

    val board = arrayOf(
        charArrayOf('5', '3', '.', '.', '7', '.', '.', '.', '.'),
        charArrayOf('6', '.', '.', '1', '9', '5', '.', '.', '.'),
        charArrayOf('.', '9', '8', '.', '.', '.', '.', '6', '.'),
        charArrayOf('8', '.', '.', '.', '6', '.', '.', '.', '3'),
        charArrayOf('4', '.', '.', '8', '.', '3', '.', '.', '1'),
        charArrayOf('7', '.', '.', '.', '2', '.', '.', '.', '6'),
        charArrayOf('.', '6', '.', '.', '.', '.', '2', '8', '.'),
        charArrayOf('.', '.', '.', '4', '1', '9', '.', '.', '5'),
        charArrayOf('.', '.', '.', '.', '8', '.', '.', '7', '9')
    )


    println(validSudoku(board))

}