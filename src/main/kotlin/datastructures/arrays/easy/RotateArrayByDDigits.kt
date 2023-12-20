package datastructures.arrays.easy

//Left Rotate
//Brute: TC-> O(n+d), SC-> O(d)
fun leftRotateArrayByDDigits(arr: IntArray, d: Int) {
    val temp = IntArray(d)
    val n = arr.size

    //store d elements in temp array
    var index = 0
    for (i in 0 until d) {
        temp[index++] = arr[i]
    }

    //shift elements back to d places
    for (i in d until n) {
        arr[i - d] = arr[i] // i th element will move to i-d, becoz d places
    }

    //put back temp elements in arr from (n-d)th position to end
    index = 0
    for (i in n - d until n) {
        arr[i] = temp[index++]
    }

}

//Optimal: TC-> O(2n), SC-> O(1)
fun leftRotateArrayByDDigitsOpt(arr: IntArray, d: Int) {
    val n = arr.size
    val d = d % n
    reverseArray(arr, 0, d - 1)
    reverseArray(arr, d, n - 1)
    reverseArray(arr, 0, n - 1)
}

fun reverseArray(arr: IntArray, start: Int, end: Int) {
    var s = start
    var e = end
    while (s <= e) {
        val temp = arr[s]
        arr[s] = arr[e]
        arr[e] = temp
        s++
        e--
    }
}


//Optimal: TC-> O(2n), SC-> O(1)
fun rightRotateArrayByDDigitsOpt(arr: IntArray, d: Int) {
    val n = arr.size
    val d = d % n
    reverseArray(arr, n - d, n - 1)
    reverseArray(arr, 0, d)
    reverseArray(arr, 0, n - 1)
}

fun main() {

    val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7)
    val d = 3
    //rotateArrayByDDigits(arr, d)
    leftRotateArrayByDDigitsOpt(arr, d)
    //rightRotateArrayByDDigitsOpt(arr, d)
    println(arr.contentToString())
}