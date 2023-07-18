package recursion

class CalculateXPowerNEfficient {
}

fun myPow(x: Double, n: Int): Double {
    if (n == 0) {
        return 1.0
    }
    if (x == 0.0) {
        return 0.0
    }

    //For negative power, use Math.abs(n / 2) for calculating power of n/2 as positive, but while returning just return 1/result
    val xPowNBy2 = myPow(x, Math.abs(n / 2))

    //due to Math.abs, this case will triggered for calculation of number in all returned recursive calls
    // except last which will return to first call, then it will go in else part and then it will return 1/result depending on odd or even
    // if we dont use Math.abs, then it wil calculate result with power in negative, and finally result will become opposite 1/(1/result) => result, which is wrong
    return if (n > 0) {
        if (n % 2 == 0) {
            xPowNBy2 * xPowNBy2
        } else {
            xPowNBy2 * xPowNBy2 * x
        }
    } else {
        if (n % 2 == 0) {
            1 / (xPowNBy2 * xPowNBy2)
        } else {
            1 / (xPowNBy2 * xPowNBy2 * x)
        }
    }
}

fun main() {
    println(myPow(2.0,-4))
}