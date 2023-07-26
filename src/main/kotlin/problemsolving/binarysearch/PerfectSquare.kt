package problemsolving.binarysearch

// https://leetcode.com/problems/valid-perfect-square

class PerfectSquare {
}

// Edge Case: need to check decimal value for perfect square after converting to double, Example for num 5
fun isPerfectSquare(num: Int): Boolean {
    if (num == 0 || num == 1) return true
    var start = 1
    var end = num
    while (start <= end) {
        val mid = start + (end - start) / 2
        // due to overflow, instead of checking mid * mid <= num, check mid <= num/mid, meaning will be remained same
        // need to use toDouble here, becoz for num 5 when mid will become 2, then 5/2 will also round to 2, so it will return true(wrong answer), so we need to check here decimal value using toDouble
        if (mid.toDouble() == (num / mid.toDouble())) {
            return true
        } else if (mid > num / mid) {
            end = mid - 1
        } else {
            start = mid + 1
        }
    }
    return false
}

fun main() {
    println(isPerfectSquare(5))
}