package datastructures.arrays.easy

//Brute Force Approach : TC -> O(nlogn) if we use quick sort for sorting
fun secondLargestBrute(arr: IntArray): Int {
    arr.sort()
    val n = arr.size

    val largest = arr[n - 1] //take first largest initially
    var sLargest = -1
    for (i in n - 2 downTo 0) {
        if (arr[i] != largest) { //largest element can be same, so check here for diff element
            sLargest = arr[i]
            break
        }
    }
    return sLargest
}

fun secondLargestBetter(arr: IntArray): Int {
    val n = arr.size

    var largest = arr[0]
    for (i in 0 until n) {
        if (arr[i] > largest) {
            largest = arr[i]
        }
    }

    var sLargest = -1
    for (i in 0 until n) {
        if (arr[i] > sLargest && arr[i] != largest) { //largest element can be same, so check here for diff element
            sLargest = arr[i]
        }
    }
    return sLargest
}


//BUt normally we will use PQ with size 3 for third largest array and so on.., For more than second largest
//But for 2nd largest, this approach is better

//Optimal with array: Tc-> O(n)
fun secondLargestOpt(arr: IntArray): Int {
    var largest = arr[0]
    var sLargest = Int.MIN_VALUE

    for (element in arr) {
        if (element > largest) {
            sLargest = largest //it will become second largest
            largest = element
        } else if (element < largest && element > sLargest) {
            sLargest = element
        }
    }

    return sLargest
}

fun main() {
    val arr = intArrayOf(3, 4, 2, 1, 6)

    println(secondLargestBrute(arr))
    println(secondLargestBetter(arr))
    println(secondLargestOpt(arr))
}