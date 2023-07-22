package algorithms.sorting

fun heapSort(list: MutableList<Int>, n: Int) {

    //Step1: build heap, starting from (n/2)-1 to 0(start), becoz all leaf nodes start from (n/2) to n-1, so before that we have non leaf nodes
    for (i in (n / 2) - 1 downTo 0) {
        maxHeapify(list, n, i) //from i to n
    }

    //Step2: deletion
    for (i in n - 1 downTo 0) {

        //swap first and last elements, so that largest element from root will go to last position in array
        var temp = list[i]
        list[i] = list[0]
        list[0] = temp

        maxHeapify(
            list,
            i,
            0
        ) //from start 0 to ith element only, before that all elements will become sorted: last, then second last and then second to second last etc.
    }
}

fun maxHeapify(list: MutableList<Int>, n: Int, startIndex: Int) {
    var largest = startIndex
    var l = 2 * startIndex + 1 //left child
    var r = 2 * startIndex + 2 //right child

    if (l <= n-1 && list[l] > list[largest]) largest = l
    if (r <= n-1 && list[r] > list[largest]) largest = r
    if(largest != startIndex) {
        //if largest is changed , swap them
        var temp = list[largest]
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