package algorithms.sorting


/**
 * Definition:-
 *
 * Selection sort is a simple and efficient sorting algorithm that works by repeatedly selecting the smallest (or largest)
 * element from the unsorted portion of the list and moving it to the sorted portion of the list.
 *
 * The algorithm repeatedly selects the smallest (or largest) element from the unsorted portion of the list and swaps it with
 * the first element of the unsorted part. This process is repeated for the remaining unsorted portion until the entire list
 * is sorted.
 *
 */

/** Assume the current element as min and then loop through remaining elements(i+1)
 *  for checking smaller than this and then swap if found
 */
fun List<Int>.selectionSort(): List<Int> {
    val list = this.toMutableList()
    val n = list.size
    for (i in 0 until n - 1) {
        var min = i
        for (j in i + 1 until n) {
            if (list[j] < list[min]) {
                min = j
            }
        }
        //Only do swapping if min has changed,
        // for example if array is already sorted in asc order,
        // then min will not change
        if (min != i) {
            val temp = list[i]
            list[i] = list[min]
            list[min] = temp
        }
    }
    return list
}

fun main() {
    println("** Selection Sort **")
    println(
        "Enter array elements with comma after each element " +
                "and then press enter:"
    )
    val arr = readLine()!!.split(",").map { it.toInt() }
    val result = arr.selectionSort()
    println("Sorter array is : $result")
}


/**
 * It is based on finding and picking up the minimum element in the remaining elements and then swapping that element
 * with the current element of the iteration
 *
 *
 */


/***
 * Complexity Analysis
 *  Time complexity -> O(n*n) for all cases
 *  Space Complexity -> O(1) :- Becoz we are not using any extra array
 *
 *  Becoz if array is already sorted, then also we need to apply the same logic of the inner loop,
 *  so complexity will be always O(n*n)
 */