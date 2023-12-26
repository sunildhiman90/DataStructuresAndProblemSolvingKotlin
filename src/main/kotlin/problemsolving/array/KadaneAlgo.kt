package problemsolving.array

//Largest Sum Contiguous Subarray
class KadaneAlgo {
}

fun kadaneAlgo(arr: List<Int>): Int {
    var sumGlobal = Int.MIN_VALUE
    var sumSoFar = 0

    for (i in arr) {
        sumSoFar += i
        if (sumSoFar > sumGlobal) {
            sumGlobal = sumSoFar
        }
        if (sumSoFar < 0) {
            sumSoFar = 0 //reset
        }
    }
    return sumGlobal
}

fun main() {
    val arr = intArrayOf(1, 1, -1, -2, 6, 1, -2, 3)
    println(kadaneAlgo(arr.toList()))
}