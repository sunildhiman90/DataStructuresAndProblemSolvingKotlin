package algorithms.sorting


/** Assume the current element as min and then loop through remaining elements(i+1)
 *  for checking smaller than this and then swap if found
 */
fun List<Int>.selectionSort(): List<Int> {
    var list = this.toMutableList()
    for (i in 0 until list.size) {
        var min = i
        for (j in i+1 until list.size) {
            if(list[j] < list[min]) {
                min = j
            }
        }
        //Only do swapping if min has changed,
        // for example if array is already sorted in asc order,
        // then min will not change
        if(min != i) {
            val temp = list[i]
            list[i] = list[min]
            list[min] = temp
        }
    }
    return list
}

fun main() {
    println("** Selection Sort **")
    println("Enter array elements with comma after each element " +
            "and then press enter:")
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