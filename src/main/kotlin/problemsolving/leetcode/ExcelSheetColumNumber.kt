package problemsolving.leetcode

class ExcelSheetColumNumber {
}

fun excelSheetColumNumber(columnTitle: String): Int {
    var finalNum = 0

    for (char in columnTitle) {
        val intVal = char.toInt() - 64
        finalNum = finalNum * 26 + intVal
    }

    return finalNum

}

fun main() {
    println(
        excelSheetColumNumber(
            "FXSHRXW"
        )
    )
}