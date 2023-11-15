package algorithms.sorting

import utils.measureTimedValueCustom

class RecursiveInsertionSort {
}

fun insertionSortIterative(list: MutableList<Int>): List<Int> {
    for (i in 0 until list.size) { //iteration loop
        var j = i
        //do swapping with prev element until its > 0
        while (j > 0 && list[j - 1] > list[j]) {
            val temp = list[j - 1]
            list[j - 1] = list[j]
            list[j] = temp
            j--
        }
    }
    return list
}


//Convert outer loop to recursion call
fun recursiveInsertionSort(list: MutableList<Int>, i: Int, n: Int) {

    //it will run only upto n-1
    if (i == n) return

    var j = i
    //do swapping with prev element until its > 0
    while (j > 0 && list[j - 1] > list[j]) {
        val temp = list[j - 1]
        list[j - 1] = list[j]
        list[j] = temp
        j--
    }

    recursiveInsertionSort(list, i + 1, n)

}

fun main() {
    println("** Insertion Sort **")
    println("Enter array elements with comma after each element and then press enter:")
    val arr = readln().split(",").map { it.toInt() }
    val list = arr.toMutableList()
    val (result: Unit, duration: Double) = measureTimedValueCustom {
        recursiveInsertionSort(list, i = 0, n = list.size)
    }
    println("Sorter array is : $list")
    println("Insertion Sort took $duration seconds")
}