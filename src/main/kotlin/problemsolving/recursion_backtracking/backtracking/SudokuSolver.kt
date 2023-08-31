package problemsolving.recursion_backtracking.backtracking

class SudokuSolver {
}

fun solveSudoku(board: Array<CharArray>) {

    helper(board = board)

}

fun helper(board: Array<CharArray>): Boolean {

    for (i in board.indices) {

        for (j in board.indices) {

            if (board[i][j] == '.') {

                //try all valid numbers here from 1 to 9
                for (c in '1'..'9') {
                    if (checkValidSudoku(i, j, board, c)) {
                        board[i][j] = c //add current number c

                        //solve again from start with this char c filled up
                        if (helper(board)) {
                            // if next sudoku is valid return true, dont need to check with next number
                            return true
                        } else {
                            //undo current number
                            board[i][j] = '.'
                        }
                    }
                }

                // we tried all numbers from 1 to 9, but didnt got valid sudoku, so return false, for backtracking from if(helper(board)) condition
                return false
            }

        }

    }
    // if all the spaces are filled, return true
    return true
}

fun checkValidSudoku(row: Int, col: Int, board: Array<CharArray>, num: Char): Boolean {
    var isValid = true
    //val numAsChar = Character.forDigit(num, 10)
    val numAsChar = num

    // check in column
    var i = 0
    while (i < board.size) {
        if (board[i][col] == numAsChar) {
            isValid = false
            break
        }
        i++
    }

    // check in row
    var j = 0
    while (j < board.size) {
        if (board[row][j] == numAsChar) {
            isValid = false
            break
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
            if (board[rowIndex][colIndex] == numAsChar) {
                isValid = false
                break
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

    solveSudoku(board)
    println(board.map {
        println(it.contentToString())
    })

}