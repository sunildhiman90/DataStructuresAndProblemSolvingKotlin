package dsa.sorting

import utils.measureTimedValueCustom

fun main() {
    println("** Bubble Sort **")
    println("Enter array elements with comma after each element and then press enter:")
    val arr = readLine()!!.split(",").map { it.toInt() }
    val (result: List<Int>, duration: Double) = measureTimedValueCustom {
        arr.bubbleSort()
    }
    println("Sorter array is : $result")
    println("Bubble Sort took $duration seconds")
}

fun List<Int>.bubbleSort(): List<Int> {
    var list = this.toMutableList()
    for (i in 0 until list.size) { //iteration loop
        var flag = 0
        for (j in 0 until list.size - i - 1) { //comparison loop
            if (list[j] > list[j + 1]) {
                val temp = list[j]
                list[j] = list[j + 1]
                list[j + 1] = temp
            }
            flag = 1
        }
        //If no swapping is done, then list is already sorted, break the outer iteration loop
        if (flag == 0) {
            break
        }
    }
    return list
}