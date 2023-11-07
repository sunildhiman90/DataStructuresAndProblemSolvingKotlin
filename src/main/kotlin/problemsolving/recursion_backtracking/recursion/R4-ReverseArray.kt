package problemsolving.recursion_backtracking.recursion


//Reverse Array using 2 pointers
fun reverseArray(arr: IntArray, n: Int) {
    reverseArrayHelper(arr, n, 0, n - 1)
}

fun reverseArrayHelper(arr: IntArray, n: Int, l: Int, r: Int) {

    if (l >= r) {
        return
    }

    swap(arr, l, r)

    reverseArrayHelper(arr, n, l + 1, r - 1)

}


//Using single variable
fun reverseArray2(arr: IntArray, n: Int) {
    reverseArrayHelper2(arr, n, 0)
}

fun reverseArrayHelper2(arr: IntArray, n: Int, i: Int) {

    if (i >= n / 2) {
        return
    }

    swap(arr, i, n - i - 1)

    reverseArrayHelper2(arr, n, i + 1)

}

fun swap(arr: IntArray, a: Int, b: Int) {
    val temp = arr[a]
    arr[a] = arr[b]
    arr[b] = temp
}

fun main() {
    val arr = intArrayOf(3, 4, 2, 5, 6)
    reverseArray(arr, 5)
    println(arr.contentToString())

    println("-----")
    val arr2 = intArrayOf(9, 3, 7, 4, 6)
    reverseArray2(arr2, 5)
    println(arr2.contentToString())
    //sumOfNNaturalNumbers2(5)
}