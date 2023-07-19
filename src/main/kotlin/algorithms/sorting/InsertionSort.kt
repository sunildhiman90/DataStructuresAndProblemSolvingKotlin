package algorithms.sorting

import utils.measureTimedValueCustom

class InsertionSort {

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