package problemsolving.binarysearch

class SquareRootXOptimized {
}

//https://leetcode.com/problems/sqrtx/
fun mySqrt(x: Int): Int {
    if (x == 0 || x == 1) return x
    var start = 1
    var end = x
    while (start <= end) {
        val mid = start + (end - start) / 2
        // due to overflow, instead of checking mid * mid <= x, check mid <= x/mid, meaning will be remained same
        // as if in case a number does not have perfect square(48), we return its previous perfect square's(36) squareRoot(rounded value),
        // that's why this below if condition is there instead of just checking equality(mid * mid == x)
        if (mid <= x / mid && mid + 1 > x / (mid + 1)) {
            return mid
        } else if (mid > x / mid) {
            end = mid - 1
        } else {
            start = mid + 1
        }
    }
    return -1
}

fun main() {
    println(mySqrt(49))
}