package datastructures.arrays.easy


// in Ascending order
fun isArraySorted(arr: IntArray): Boolean {

    for (i in 1 until arr.size) {
        if (arr[i] >= arr[i - 1] == false) return false
    }
    return true
}

// in Descending order
fun isArraySortedDesc(arr: IntArray): Boolean {

    for (i in 1 until arr.size) {
        if (arr[i] >= arr[i - 1] == true) return false
    }
    return true
}

fun main() {
    val arr = intArrayOf(3, 4, 2, 1, 6)
    val arrSorted = intArrayOf(1, 2, 3, 4, 6)
    val arrSortedDesc = intArrayOf(6, 5, 4, 3, 2)

    println(isArraySorted(arr))
    println(isArraySorted(arrSorted))

    println(isArraySortedDesc(arr))
    println(isArraySortedDesc(arrSortedDesc))
}