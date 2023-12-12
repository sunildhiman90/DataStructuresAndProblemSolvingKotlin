package algorithms.sorting

import utils.measureTimedValueCustom


/**
 * Definition:-
 *
 * Bubble Sort is the simplest sorting algorithm that works by repeatedly swapping the adjacent elements if they are in the
 * wrong order. This algorithm is not suitable for large data sets as its average and worst-case time complexity is quite high.
 *
 * How it Works ?
 *
 * In Bubble Sort algorithm,
 * traverse from left and compare adjacent elements and the higher one is placed at right side.
 * In this way, the largest element is moved to the rightmost end at first.
 * This process is then continued to find the second largest and place it and so on until the data is sorted.
 *
 */

/**
 * It is based on comparing current element with next element if next is smaller(in the inner for loop), then swap, by this way
 * after the end of first iteration, the largest element will be bubble up to the end of list,
 * And if no swapping is done in first iteration then break the loop becoz it means array is already sorted.
 */
fun List<Int>.bubbleSort(): List<Int> {
    val list = this.toMutableList()
    for (i in 0 until list.size) { //iteration loop
        var flag = 0

        // run inner loop for length-i-1 because,
        // -1 is for handling the last case for comparison arr[j]>arr[j+1] so that no IndexOutOfBoundException occurs
        // and -i is because with each iteration, last i elements will be already in sorted order
        for (j in 0 until list.size - i - 1) { //comparison loop
            if (list[j] > list[j + 1]) {
                val temp = list[j]
                list[j] = list[j + 1]
                list[j + 1] = temp
                flag = 1
            }
        }
        //If no swapping is done, then list is already sorted, break the outer iteration loop
        if (flag == 0) {
            break
        }
    }
    return list
}

fun List<Int>.bubbleSortAlt(): List<Int> {
    val list = this.toMutableList()

    val n = list.size
    for (i in n - 1 downTo 0) { //iteration loop
        var flag = 0

        for (j in 0 until i) { //comparison loop
            if (list[j] > list[j + 1]) {
                val temp = list[j]
                list[j] = list[j + 1]
                list[j + 1] = temp
                flag = 1
            }
        }
        //If no swapping is done, then list is already sorted, break the outer iteration loop
        if (flag == 0) {
            break
        }
    }
    return list
}


fun main() {
    println("** Bubble Sort **")
    println("Enter array elements with comma after each element and then press enter:")
    val arr = readLine()!!.split(",").map { it.toInt() }
    val (result: List<Int>, duration: Double) = measureTimedValueCustom {
        arr.bubbleSort()
    }
    println("Sorter array is : $result")
    println("Bubble Sort took $duration seconds")
}


/**
 * It is based on comparing current element with next element if next is smaller, then swap, by this way
 * after the end of first iteration, the largest element will be bubble up to the end of list and similar for other iterations
 * <p>
 * <p>
 * Complexity Analysis
 * Time complexity -> O(n) for best case, and O(n*n) for worst and average case
 * Space Complexity -> O(1) :- Becoz we are not using any extra array
 * <p>
 * Becoz if array is already sorted, then we will break the loop becoz after completion of inner loop,
 * there will be no swapping done, so outer loop will run only one time and for that iteration inner loop will run n times
 * -> so complexity will be 1*n => O(n)
 */


/**
 * Complexity Analysis
 *  Time complexity -> O(n) for best case, and O(n*n) for worst and average case
 *  Space Complexity -> O(1) :- Becoz we are not using any extra array
 *
 *  Becoz if array is already sorted, then we will break the loop becoz after completion of inner loop,
 *  there will be no swapping done, so outer loop will run only one time and for that iteration inner loop will run n times
 *  -> so complexity will be 1*n => O(n)
 */