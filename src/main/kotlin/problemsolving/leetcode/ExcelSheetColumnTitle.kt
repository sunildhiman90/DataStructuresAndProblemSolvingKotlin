package problemsolving.leetcode

// https://leetcode.com/problems/excel-sheet-column-title/description/

// Need to assume 0 based start from A instead of 1, due to 26 % 26 = 0
fun convertToTitle(columnNumber: Int): String {
    var columnNumber = columnNumber
    val string = StringBuilder()
    while (columnNumber != 0) {
        // becoz remainder will be between 0 to 25, so we decrease number by 1 to get desired result,
        // otherwise mapping will not work, becoz we have A..Z as 1..26, but we need 0..25 thats why
        var remainder = (--columnNumber) % 26
        columnNumber = columnNumber / 26
        val char = (remainder + 65).toChar()
        string.append(char)
        // Insert a char at the beginning is not very efficient. The complexity will be n^2.
        // We can append the character to the end and reverse the string in the end, and the
        // complexity will be linear. Becoz append has O(1) and insert has O(n)
    }
    return string.reverse().toString()
}

fun main() {
    println(
        convertToTitle(
            52
        )
    )

    //AZ for 52
}