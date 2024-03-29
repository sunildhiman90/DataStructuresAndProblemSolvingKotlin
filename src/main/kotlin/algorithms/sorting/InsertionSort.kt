package algorithms.sorting

import utils.measureTimedValueCustom


/**
 * Definition:-
 *
 * Insertion sort is a simple sorting algorithm that works similar to the way you sort playing cards in your hands.
 * The array is virtually split into a sorted and an unsorted part. Values from the unsorted part are picked and placed at the
 * correct position in the sorted part.
 *
 * Insertion Sort Algorithm
 * To sort an array of size N in ascending order iterate over the array and compare the current element (key) to its predecessor,
 * if the key element is smaller than its predecessor, compare it to the elements before. Move the greater elements one position
 * up to make space for the swapped element. Or alternatively we can keep swapping if current element is smaller than predecessor
 *
 */


/**
 *  Two lists -> sorted and unsorted
 *  1. Insertion means we will require shifting of elements in the array(sorted list) for
 *  inserting any current(temp) element at its desired position in sorted list
 *  2. Single element is always sorted, so we will start loop from i=1 for unsorted list
 */
fun List<Int>.insertionSort(): List<Int> {
    val list = this.toMutableList()
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

fun List<Int>.insertionSortAlt(): List<Int> {
    val list = this.toMutableList()
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


/**
 *
 * WE know single element is always sorted, So we will start the loop from i=1 for unsorted list and
 * we will assume arr[0] is already sorted and in sorted list.
 *
 * Then we will pick up arr[i] in temp and this will create the empty hole for this element's position in the array becoz we have already picked up it in the temp for later use
 * And we will insert this temp element at its desired position in the sorted list,
 * ANd for doing that we need to shift all the elements larger than this element in sorted list to the right till we find any element smaller than this
 * and then insert this element at that position
 *
 * Repeat the same for all elements in the unsorted list
 *
 */

/***
 * Complexity Analysis
 *  Time complexity -> O(n) for best case, and O(n*n) for worst and average case
 *  Space Complexity -> O(1) :- Becoz we are not using any extra array
 *
 *  Becoz if array is already sorted, then the inner loop will not be executed becoz arr[j]>temp will not be true,
 *  becoz all the elements before current element will be smaller than current element(temp),
 *  so complexity will be always O(n) due to outer loop only
 */