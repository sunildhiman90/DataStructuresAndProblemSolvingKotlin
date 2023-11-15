package algorithms.sorting

import utils.measureTimedValueCustom

//Iterative bubble sort
fun bubbleSortAlt(list: List<Int>): List<Int> {
    val list = list.toMutableList()

    val n = list.size
    for (i in n - 1 downTo 0) { //iteration loop
        var flag = 0

        // run inner loop for i-1 because,
        // -1 is for handling the last case for comparison arr[j]>arr[j+1] so that no IndexOutOfBoundException occurs
        // and -i is because with each iteration, last i elements will be already in sorted order
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


//convert outer loop to single recursive iteration for making it recursive and use inner loop in each recursive call
fun recursiveBubbleSortAlt(list: MutableList<Int>, n: Int) {

    //base case
    if (n == 1) return //return, becoz single element is always sorted, it will run upto 2 only becoz we are using n-2 in inner loop

    var flag = 0
    //run loop over n-2, becoz n-1 is last index and loop will run n-1-1
    for (j in 0..n - 2) { //comparison loop
        if (list[j] > list[j + 1]) {
            val temp = list[j]
            list[j] = list[j + 1]
            list[j + 1] = temp
            flag = 1
        }
    }

    //If no swapping is done, then list is already sorted, break the outer recursion
    if (flag == 0) return

    recursiveBubbleSortAlt(list, n - 1)

}

fun main() {
    println("** Bubble Sort **")
    println("Enter array elements with comma after each element and then press enter:")
    val arr = readLine()!!.split(",").map { it.toInt() }
    val list = arr.toMutableList()
    val (result: Unit, duration: Double) = measureTimedValueCustom {
        recursiveBubbleSortAlt(list, arr.size)
    }
    println("Sorter array is : $list")
    println("Bubble Sort took $duration seconds")
}