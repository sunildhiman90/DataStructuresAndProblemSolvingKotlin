package algorithms.searching

import utils.measureTimedValueCustom

class LinearSearch

fun main(args: Array<String>) {
    println("** Linear Search **")
    println("Enter array elements with comma after each element and then press enter:")
    val arr = readLine()!!.split(",").map { it.toInt() }
    println("Now Enter element to search:")
    val element = readLine()!!.toInt()
    val (result: Int?, duration: Double )= measureTimedValueCustom {
        element linearSearchIn arr  //alternative of element.linearSearchIn(arr)
    }
    result?.let {
        println("Element found at index: $it")
    } ?: println("Element not found")
    println("Linear Search took $duration seconds")
}

//It wil return index if element is found , otherwise null
infix fun Int.linearSearchIn(list: List<Int>): Int? {
    for ((index, value) in list.withIndex()) {
        if(value == this) {
            return index
        }
    }
    return null
}

/**
 * Time and Space Complexity
 *
 *  Worst Case(element found at last index) Time Complexity: O(n)
 *  Best Case(if element found at first index) Time Complexity: O(1)
 *  Average Case(if element found at any other index) Time Complexity: O(n/2) => O(n)
 *
 *  Space Complexity: O(1)
 *
 *  What does O(1) space mean?
 *  It means that the amount of memory your algorithm consumes doesn't depend on the input.
 *  The algorithm should use the same amount of memory for all inputs.
 *  So, the linear search only need one element need(that is the variable you used to compare with all the elements in the array),
 *  so no matter how big or small your array is, the algorithm only consumes the same amount of memory for all input,
 *  and thus is space complexity is O(1)
 */