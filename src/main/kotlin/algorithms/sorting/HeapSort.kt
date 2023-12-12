package algorithms.sorting

/**
 * Definition:-
 * Heap sort is a comparison-based sorting technique based on Binary Heap data structure. It is similar to the selection sort where we
 * first find the minimum element and place the minimum element at the beginning. Repeat the same process for the remaining elements.
 *
 * How Heap Sort Algorithm works ?
 * First convert the array into heap data structure using heapify, then one by one delete the root node of the Max-heap and replace
 * it with the last node in the heap and then heapify the root of the heap. Repeat this process until size of heap is greater than 1.
 *
 * 1. Build a heap from the given input array.
 * 2. Repeat the following steps until the heap contains only one element:
 *   a. Swap the root element of the heap (which is the largest element) with the last element of the heap.
 *   b. Remove the last element of the heap (which is now in the correct position).
 *   c. Heapify the remaining elements of the heap.
 */

/**
 * Step 1:
 * build heap from non leaf nodes, starting from (n/2)-1 to 0(start),
 * becoz all leaf nodes start from (n/2) to n-1, so before that we have non leaf nodes
 *
 * Step2: Deletion
 * for all elements, loop in descending order and swap first(which is root and is largest element) and current elements(last element in
 * unsorted list) in each iteration, so that largest element from root will go to last position in array
 */
fun heapSort(list: MutableList<Int>, n: Int) {

    //Step1: build heap, starting from (n/2)-1 to 0(start), becoz all leaf nodes start from (n/2) to n-1, so before that we have non leaf nodes
    for (i in (n / 2) - 1 downTo 0) {
        maxHeapify(list, n = n, startIndex = i) //heapify from i to n
    }

    //Step2: deletion
    for (i in n - 1 downTo 0) {

        //swap first(that is root that is largest element) and last elements, so that largest element from root will go to last position in array
        val temp = list[i]
        list[i] = list[0]
        list[0] = temp

        maxHeapify(
            list,
            i,
            0
        ) //heapify from start 0 to ith element only, before that all elements will become sorted: last, then second last and then second to second last etc.
    }
}

fun maxHeapify(list: MutableList<Int>, n: Int, startIndex: Int) {
    var largest = startIndex
    val l = 2 * startIndex + 1 //left child
    val r = 2 * startIndex + 2 //right child

    if (l <= n - 1 && list[l] > list[largest]) largest = l
    if (r <= n - 1 && list[r] > list[largest]) largest = r
    if (largest != startIndex) {
        //if largest is changed , swap them
        val temp = list[largest]
        list[largest] = list[startIndex]
        list[startIndex] = temp

        //and now again check for maxHeapify for largest
        maxHeapify(list, n, largest)
    }
}


fun main() {
    println("Enter array elements using comma as separator and then press enter")
    var arr = readln().split(",").map { it.toInt() }
    var mutableList = arr.toMutableList()
    heapSort(mutableList, arr.size)
    println("sorted array is : $mutableList")
}


/**
 * When building heap, it will make sure that roots are larger than childs on each level, and it means largest elemtn will be on root
 *
 * Then deletion will happen, it will move that largest element (root) to the last position, and then we will again check for building heap,
 * because building heap will ensure that now second largest element will be on top, (We will not consider last element)
 *
 * ANd then again, that second last element will be moved to last position by swapping, now we have 2 largest elements sorted in ith and (i-1)th position
 * and again after that we will check for heapify for i-2 elements only, because last 2 elements are already sorted.
 *
 * and simiilarily it will repeat for all remaining elements
 *
 */