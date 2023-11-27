package datastructures.arrays.easy

//Brute: Tc: O(n), Sc: O(n)
fun moveZerosToEnd(arr: IntArray) {

    //step 1
    var index = 0
    val temp = IntArray(arr.size)
    for (i in arr.indices) {
        if (arr[i] != 0) {
            temp[index++] = arr[i]
        }
    }

    //step 2
    for (i in temp.indices) {
        arr[i] = temp[i]
    }

    //Step3
    for (i in temp.size until arr.size) {
        arr[i] = 0
    }

}

//Optimal: Tc: O(n), Sc: O(1)
fun moveZerosToEndOpt(arr: IntArray) {

    //Step 1
    var index = 0
    for (i in arr.indices) {
        if (arr[i] != 0) {
            arr[index++] = arr[i]
        }
    }

    //Step 2
    for (i in index until arr.size) {
        arr[i] = 0
    }
}

fun main() {

    val arr = intArrayOf(1, 0, 3, 0, 0, 6, 7)
    //moveZerosToEnd(arr)
    moveZerosToEndOpt(arr)
    println(arr.contentToString())
}