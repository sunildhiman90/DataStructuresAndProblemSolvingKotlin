package leetcode

import java.util.*

class ReverseWordsInAString {}

fun reverseWords(s: String): String {
    val words = s.trim().split("\\s+".toRegex()).toMutableList() //remove one or more spaces and convert List to MutableList for updating elements
    var i = 0
    var j = words.size - 1
    while(i < j) {
        val temp = words[i]
        words[i] = words[j]
        words[j] = temp
        i++
        j--
    }

    return words.joinToString(separator = " ")
}

fun reverseWordsUsingStack(s: String): String {
    val words = s.trim().split("\\s+".toRegex()).toMutableList() //remove one or more spaces and convert List to MutableList for updating elements
    val stack = Stack<String>()
    for(word in words) {
      stack.push(word)
    }

    val string = StringBuilder()
    while (!stack.isEmpty()) {
        string.append(stack.peek()+" ")
        stack.pop()
    }

    return string.toString().trimEnd()
}

fun main() {
    println(reverseWords("kotlin is great"))
    println(reverseWordsUsingStack("kotlin is great"))
}

