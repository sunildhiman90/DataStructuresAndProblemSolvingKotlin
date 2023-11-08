package problemsolving.hashing

//https://www.codingninjas.com/studio/problems/count-frequency-in-a-range_8365446?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf

fun freqOfElements(arr: IntArray, n: Int): IntArray {
    val output = IntArray(n + 1)

    for (element in arr) {
        output[element]++
    }
    return output
}


fun main() {

    val arr = intArrayOf(2, 4, 3, 6, 4, 2, 1, 8) //total 8 elements and last element is 8

    val res = freqOfElements(arr, arr.size)
    println(res.contentToString())
}

