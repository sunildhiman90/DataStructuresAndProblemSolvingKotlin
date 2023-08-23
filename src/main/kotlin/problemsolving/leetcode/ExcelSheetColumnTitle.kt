package problemsolving.leetcode

class ExcelSheetColumnTitle {
}


// Need to assume 0 based start from A instead of 1, due to 26 % 26 = 0
fun excelSheetColumnTitle(columnNumber: Int): String {
    var columnNumber = columnNumber
    val string = StringBuilder()
    while (columnNumber > 0) {
        columnNumber--
        var remainder = columnNumber % 26
        columnNumber = columnNumber / 26
        val char = (remainder + 65).toChar()
        string.insert(0, char)
        //TODO, Insert a char at the beginning is not very efficient. The complexity will be n^2. We can append the character to the end and reverse the string in the end, and the complexity will be linear.
    }

    return string.toString()
}

fun main() {
    println(
        excelSheetColumnTitle(
            52
        )
    )

    //AZ for 52
}