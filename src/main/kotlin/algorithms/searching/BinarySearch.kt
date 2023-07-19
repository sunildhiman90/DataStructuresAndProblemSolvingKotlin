package algorithms.searching

import utils.measureTimedValueCustom

class BinarySearch

//Array should be sorted
fun main(args: Array<String>) {
    println("** Binary Search **")
    println("Enter array elements with comma after each element" +
            " and then press enter:")
    val arr = readLine()!!.split(",").map { it.toInt() }
    println("Now Enter element to search:")
    val element = readLine()!!.toInt()
    val (result: Int?, duration: Double) = measureTimedValueCustom {
        //element binarySearchIn arr.sorted()
        binarySearch(arr, element, 0, arr.size - 1)
    }
    result?.let {
        println("Element found at index: $it")
    } ?: println("Element not found")
    println("Binary Search took $duration seconds")
}

//Iterative approach, array need to be sorted
//simple non infix method //For input : 1,3,5,7,9,11,13,14 -> it took 0.009 seconds
fun binarySearch(list: List<Int>, elementToFind: Int, start: Int, end: Int): Int? {
    var start = start
    var end = end

    //check for <= here but not < only, consider case for 1,2,3 and search for 1 using <, it will not work
    while (start <= end) {
        val mid = start + (end - start) / 2
        if (elementToFind == list[mid]) return mid
        else if (elementToFind < list[mid]) end = mid - 1
        else start = mid + 1
    }
    return null
}

//For input : 1,3,5,7,9,11,13,14 -> it took 0.011 seconds
fun binarySearchRecursive(list: List<Int>, elementToFind: Int, start: Int, end: Int): Int? {
    var start = start
    var end = end
    var mid = start + (end - start) / 2
    if (elementToFind == list[mid]) return mid
    else if (elementToFind < list[mid]) return binarySearchRecursive(list, elementToFind, start = start, end = mid - 1)
    else return binarySearchRecursive(list, elementToFind, start = mid + 1, end = end)
}


//Iterative approach, array need to be sorted
//It wil return index if element is found , otherwise null
infix fun Int.binarySearchIn(list: List<Int>): Int? {
    val elementToFind = this
    var start = 0
    var end = list.size - 1
    while (start <= end) {
        println("start=$start")

        var mid = start + (end - start) / 2

        if (elementToFind == list[mid]) return mid
        else if (elementToFind < list[mid]) {
            end = mid - 1
        } else {
            start = mid + 1
        }

    }
    return null
}



/***
 *
 * TIME AND SPACE COMPLEXITY
 *
 *
 *
 * Analysis of Space Complexity of Binary Search
In the case of the iterative approach, no extra space is used. Hence, the space complexity is O(1).

In the worst case, logn recursive calls are stacked in the memory.

i comparisons require i recursive calls to be stacked in memory.
Since average time complexity analysis has logn comparisons, the average memory will be O(logn).
Thus, in recursive implementation the overall space complexity will be O(logn).
 *
 *
 * WHY MID IS CALCULATED as mid = low+(high-low)/2?
 *
Let us assume that the array we're searching in, is of length INT_MAX. Hence initially:

high = INT_MAX
low = 0
In the first iteration, we notice that the target element is greater than the middle element and so we shift the start index to mid as

low = mid + 1
In the next iteration, when mid is calculated, it is calculated as (high + low)/2 which essentially translates to INT_MAX + low(which is half of INT_MAX + 1) / 2

Now, the first part of this operation i.e. (high + low) would lead to an overflow since we're going over the max Int range i.e. INT_MAX
 *
 */

//Reference link for complexity: https://www.scaler.com/topics/time-complexity-of-binary-search/
