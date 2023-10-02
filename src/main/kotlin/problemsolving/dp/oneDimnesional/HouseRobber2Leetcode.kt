package problemsolving.dp.oneDimnesional

//https://leetcode.com/problems/house-robber-ii/

fun rob(nums: IntArray): Int {

    val n = nums.size

    if (n == 1) return nums[0]

    // either use 0 .. n-2 => skip last
    val numsWithoutFirst = IntArray(n - 1)

    // Or use 1 .. n-1 => skip first
    val numsWithoutLast = IntArray(n - 1)


    var index1 = 0
    var index2 = 0
    for (i in 0 until n) {
        if (i != 0) numsWithoutFirst[index1++] = nums[i]
        if (i != n - 1) numsWithoutLast[index2++] = nums[i]
    }

    return Math.max(robHelper(numsWithoutFirst), robHelper(numsWithoutLast))

}

fun robHelper(nums: IntArray): Int {

    val n = nums.size
    var prev = nums[0]
    var prev2 = 0 //for negative index case, i-2 will be 0

    for (i in 1 until n) {

        val pick = nums[i] + prev2 //pick ind-2

        val nonPick = 0 + prev //here we can pick ind-1, becoz this is case of not picking ind-2

        val curr = Math.max(pick, nonPick)
        prev2 = prev
        prev = curr
    }

    return prev
}


fun main() {

    val intArray = intArrayOf(1, 2, 3, 1)

    println(rob(intArray))
}