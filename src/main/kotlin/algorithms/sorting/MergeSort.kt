package algorithms.sorting


/**
 *
 * Definition:-
 *
 * Merge sort is defined as a sorting algorithm that works by dividing an array into smaller subarrays, sorting each subarray,
 * and then merging the sorted subarrays back together to form the final sorted array.
 *
 * In simple terms, we can say that the process of merge sort is to divide the array into two halves, sort each half,
 * and then merge the sorted halves back together. This process is repeated until the entire array is sorted.
 *
 * How does Merge Sort work-:
 *
 * Merge sort is a recursive algorithm that continuously splits the array in half until it cannot be further divided i.e., the array has
 * only one element left (an array with one element is always sorted). Then the sorted subarrays are merged into one sorted array.
 */


fun divide(list: MutableList<Int>, start: Int, end: Int) {
    //keep dividing until single element, then start == end, and it will return and will proceed with conquer calls then
    if (start >= end) {
        return
    }

    val mid = start + (end - start) / 2
    divide(list, start, mid)
    divide(list, mid + 1, end)
    conquer(list, start, mid, end)
}

fun conquer(list: MutableList<Int>, start: Int, mid: Int, end: Int) {
    val mergedList = mutableListOf<Int>()
    var k = 0
    var idx1 = start
    var idx2 = mid + 1

    while (idx1 <= mid && idx2 <= end) {
        //copy smaller elements first
        if (list[idx1] <= list[idx2]) {
            mergedList.add(k++, list[idx1++])
        } else {
            mergedList.add(k++, list[idx2++])
        }
    }

    //copy remaining element of first list
    while (idx1 <= mid) {
        mergedList.add(k++, list[idx1++])
    }

    //copy remaining element of second list
    while (idx2 <= end) {
        mergedList.add(k++, list[idx2++])
    }


    // j will be initialized to startIndex because startIndex will change for each recursive call ,
    // before that element will be sorted
    // copy all elements of new merged array to original array from startIndex before which all elements will be already sorted
    var index2 = start
    for (i in 0 until mergedList.size) {
        list[index2++] = mergedList[i]
    }

}

fun main() {

    println("Enter array elements using comma as separator and then press enter")
    val arr = readln().split(",").map { it.toInt() }
    val mutableList = arr.toMutableList()
    divide(mutableList, 0, arr.size - 1)

    println("sort array is : $mutableList")
}


/**
 *
 * It does not requires random access it requires sequential access, so better for linked lists
 *
 * Space Complexity ->
 * O(n) -> for 1 extra array of size n for n elements
 * +
 * O(n*logn) -> for recursive stack calls, on each stage its divided into 2 subarrays =>
 *
 */