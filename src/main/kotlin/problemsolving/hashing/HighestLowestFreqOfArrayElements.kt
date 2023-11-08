package problemsolving.hashing

//https://www.codingninjas.com/studio/problems/k-most-occurrent-numbers_625382?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
fun highestLowestFreq(arr: IntArray, n: Int): IntArray {
    val output = IntArray(2)
    val freq = HashMap<Int, Int>()

    for (element in arr) {
        freq.put(element, freq.getOrDefault(element, 0) + 1)
    }

    val list = freq.toList().sortedBy { it.second }

    output[0] = list.last().first //highest
    output[1] = list.first().first //lowest

    return output
}


fun main() {

    val arr = intArrayOf(2, 4, 3, 6, 4, 3, 3, 2, 1, 8)

    val res = highestLowestFreq(arr, arr.size)
    println(res.contentToString())
}

