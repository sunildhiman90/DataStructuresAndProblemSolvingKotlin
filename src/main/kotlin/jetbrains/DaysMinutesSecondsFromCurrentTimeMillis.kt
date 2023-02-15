package jetbrains

//https://hyperskill.org/learn/step/15729

/**
 * You are given a number that represents the number of seconds passed since 1.1.1970.
Write a program that calculates the current time, i.e., finds the hours, minutes, and seconds of the given number of seconds.

Format: hours:minutes:seconds

Example: 14:9:7

You don't have to import anything, just use % and /, and remember how long one day is.
You do not need to print the number of days and do not think about UTC and GMT.
 */

fun main() {
    val totalSeconds = System.currentTimeMillis() / 1000 // do not change this line
    val days = totalSeconds / 86400
    val remainderAfterDays = totalSeconds % 86400
    val hours = remainderAfterDays / 3600
    val remainderAfterHours = remainderAfterDays % 3600
    val minutes = remainderAfterHours / 60
    val seconds = remainderAfterHours % 60
    println("$hours:$minutes:$seconds")
}