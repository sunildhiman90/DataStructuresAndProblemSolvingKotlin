package jetbrains

class NumberOfTensUsingString

fun main() {
    //1st way-> W way when first time solved it
    val number = readLine()!!.toString()
    if (number.length >= 2) {
        println(number[number.lastIndex - 1])
    } else {
        println(0)
    }

    //2nd way
    println(readLine()!!.reversed()[1])

    //3rd way
    println(readLine()!!.dropLast(1).last())

}