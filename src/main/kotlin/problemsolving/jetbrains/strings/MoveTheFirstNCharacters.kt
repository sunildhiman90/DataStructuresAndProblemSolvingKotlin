package problemsolving.jetbrains.strings


/**
 * Move the first N characters
 * Write a program that reads a string s and an int n, and then moves the first n characters of s to the end of the string. The program must output the changed string. If n is greater than the length of the string, it must output the string unchanged.
 *
 * Note, the substring() method can help you, but you may choose another way to solve the problem.
 *
 * Input data format
 *
 * The single input line contains s and n separated by a space.
 *
 * Sample Input 1:
 *
 * Hello 3
 * Sample Output 1:
 *
 * loHel
 */

class MoveTheFirstNCharacters {
}


fun main() {
    val strData = readln().split(" ")
    val str = strData.first()
    val n = strData.last().toInt()
    if (n > str.length) {
        println(str)
    } else {
        val substr1 = str.substring(0, n)
        val substr2 = str.substring(n)
        println(substr2 + substr1)
    }
}