package datastructures.arrays.easy

//Brute Force Approach : TC -> O(nlogn) if we use quick sort for sorting
fun largestBrute(arr: IntArray): Int {
    arr.sort()
    val n = arr.size
    return arr[n - 1]
}

//Optimal: Tc-> O(n)
fun largestOpt(arr: IntArray): Int {
    var largest = arr[0]

    for (element in arr) {
        if (element > largest) largest = element
    }

    return largest
}

fun main() {
    val arr = intArrayOf(3, 4, 2, 1, 6)

    println(largestBrute(arr))
    println(largestOpt(arr))
}