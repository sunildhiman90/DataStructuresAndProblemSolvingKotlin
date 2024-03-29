package problemsolving.jetbrains

import utils.measureTimedValueCustom

class SumOfDigits {
}

fun main() {
    val num = readLine()!!

    //1st way
    //CharSequence.map{ it.toString() } returns List<String> from a single string with each char converted to string
    val (result: Unit, duration: Double) = measureTimedValueCustom {
        println(num.map{ it.toString() }.sumOf(String::toInt))
        //2nd way
        println(num.map(Char::toString).sumOf(String::toInt))
    }
    println("1st way took: $duration seconds")

    val (result2: Unit, duration2: Double) = measureTimedValueCustom {
        //2nd way
        println(num.map(Char::toString).sumOf(String::toInt))
    }
    println("2nd way took: $duration2 seconds")

    val (result3: Unit, duration3: Double) = measureTimedValueCustom {
        //3rd way
        println(sumOfDigitsCore(num.toInt()))
    }
    println("3rd way took: $duration3 seconds")

    val (result4: Unit, duration4: Double) = measureTimedValueCustom {
        //4th way
        print(readln().sumOf { it.digitToInt() })
    }
    println("4th way took: $duration4 seconds")


}

//3rd way
fun sumOfDigitsCore(number: Int): Int {
    var num = number
    var sum = 0
    while (num > 0) {
        sum += num % 10
        num /= 10
    }
    return sum
}