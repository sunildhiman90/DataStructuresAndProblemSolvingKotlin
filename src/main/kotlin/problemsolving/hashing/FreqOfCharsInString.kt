package problemsolving.hashing


fun freqOfChars(arr: String, n: Int): IntArray {
    val output = IntArray(256) //0 to 255 al character's ascii values

    for (element in arr) {
        output[element.code]++
    }
    return output
}


fun main() {

    val str = "codinginkotlin"

    val res = freqOfChars(str, str.length)
    str.forEach {
        println("$it : ${res[it.code]}")
    }
}

