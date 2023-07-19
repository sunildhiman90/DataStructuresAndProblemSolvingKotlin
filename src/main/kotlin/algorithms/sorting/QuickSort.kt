package algorithms.sorting

import utils.measureTimedValueCustom

class QuickSort {
}

fun main() {
    println("** QuickSort **")
    println("Enter array elements with comma after each element and then press enter:")
    val arr = readln().split(",").map { it.toInt() }
    val (result: List<Int>, duration: Double) = measureTimedValueCustom {
        quickSort(arr.toMutableList(), 0, arr.size-1)
    }
    println("Sorter array is : $result")
    println("QuickSort took $duration seconds")
}

//check for elements smaller than pivot, and swap with larger(Mostly it will be so)
fun quickSort(list: MutableList<Int>, low: Int, high: Int): List<Int> {
    if (low < high) {
        val pivotIndex = partition(list, low, high)
        quickSort(list, low, pivotIndex - 1)
        quickSort(list, pivotIndex + 1, high)
    }
    return list
}

fun partition(list: MutableList<Int>, low: Int, high: Int): Int {
    val pivot = list[high]

    //index(for last smaller element) where we will put smaller element(all elements before and at this index will be smaller than pivot)
    var i = low - 1

    for (j in low until high) {
        //check for elements smaller than pivot and increment i and swap
        if(list[j] < pivot) {
            i++ //create space for swap ith element with smaller(jth index) element than pivot
            val temp = list[j]
            list[j] = list[i]
            list[i] = temp
        }
    }

    //put pivot at its place at  i++ because before i all elements are smaller than pivot
    i++
    val temp = list[high]
    list[high] = list[i]
    list[i] = temp

    return i
}



