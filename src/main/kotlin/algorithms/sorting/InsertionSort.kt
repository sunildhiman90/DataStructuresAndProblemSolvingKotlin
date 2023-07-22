package algorithms.sorting

import utils.measureTimedValueCustom

/**
 *  Two lists -> sorted and unsorted
 *  1. Insertion means we will require shifting of elements in the array(sorted list) for
 *  inserting any current(temp) element at its desired position in sorted list
 *  2. Single element is always sorted, so we will start loop from i=1 for unsorted list
 */
fun List<Int>.insertionSort(): List<Int> {
    var list = this.toMutableList()
    for (i in 1 until list.size) { //iteration loop
        val temp = list[i]
        var j = i - 1
        while (j >= 0 && list[j] > temp) {
            list[j + 1] = list[j] //right shift  elements larger than current element in sorted list
            j--
        }
        //increment j and then insert temp, becoz when for the j=0 (if true), it will decrement it to -1
        list[j + 1] = temp
    }
    return list
}

fun main() {
    println("** Insertion Sort **")
    println("Enter array elements with comma after each element and then press enter:")
    val arr = readln().split(",").map { it.toInt() }
    val (result: List<Int>, duration: Double) = measureTimedValueCustom {
        arr.insertionSort()
    }
    println("Sorter array is : $result")
    println("Insertion Sort took $duration seconds")
}