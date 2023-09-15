package problemsolving.leetcode

// https://leetcode.com/problems/excel-sheet-column-number/

fun excelSheetColumNumber(columnTitle: String): Int {
    var result = 0

    for (char in columnTitle) {
        result *= 26
        val current = char - 'A' + 1
        result += current
    }

    return result

}

fun main() {
    println(
        excelSheetColumNumber(
            "FXSHRXW"
        )
    )
}