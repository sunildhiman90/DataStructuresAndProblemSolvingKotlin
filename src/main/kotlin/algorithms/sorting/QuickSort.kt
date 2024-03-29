package algorithms.sorting

import utils.measureTimedValueCustom


/**
 * Definition:-
 *
 * QuickSort is a sorting algorithm based on the Divide and Conquer algorithm that picks an element as a pivot and partitions
 * the given array around the picked pivot by placing the pivot in its correct position in the sorted array.
 *
 * How does QuickSort work?
 * The key process in quickSort is a partition(). The target of partitions is to place the pivot (any element can be chosen
 * to be a pivot) at its correct position in the sorted array and put all smaller elements to the left of the pivot,
 * and all greater elements to the right of the pivot.
 *
 * Partition is done recursively on each side of the pivot after the pivot is placed in its correct position and this
 * finally sorts the array.
 *
 */
// check for elements smaller than pivot if we take last element as pivot, and we will store them in sorted order
// by swapping with current element if current element < pivot element,
// by this way, elements smaller than pivot will be come together before some index (pivot index)
// and elements larger than pivot will automatically shifted to positions
// greater than pivot index (used for keeping track of smaller elements than pivot)

// But if we take first element as pivot,  check for elements larger than pivot
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
        if (list[j] < pivot) {
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

fun main() {
    println("** QuickSort **")
    println("Enter array elements with comma after each element and then press enter:")
    val arr = readln().split(",").map { it.toInt() }
    val (result: List<Int>, duration: Double) = measureTimedValueCustom {
        quickSort(arr.toMutableList(), 0, arr.size - 1)
    }
    println("Sorter array is : $result")
    println("QuickSort took $duration seconds")
}

/**
 * It requires random access for swapping, so better for arrays, because random access is faster in arrays
 * <p>
 * Space Complexity -> O(logn) -> for recursive stack calls, on each stage its divided into 2 sub arrays
 * <p>
 * Time Complexity  ->
 * Best and average case: O(n*logn)
 * Worst case: (O(n*n)) and It happens when pivot element is either smallest or largest, it means either array is sorted in ascending order or descending
 */