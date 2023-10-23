package problemsolving.dp.subsequenceOrSubsets

//https://leetcode.com/problems/partition-equal-subset-sum/

fun canPartition(nums: IntArray): Boolean {
    val total = nums.sum()

    //if odd, then return false, means we cannot get 2 equal sum subsets
    if (total % 2 != 0) return false

    //else just divide total in 2 parts, and check if we can get subset with half sum as target, it second subset will also be available
    val target = total / 2

    return subsetSumTabuSpaceOpt2(target, nums)

}

fun subsetSumTabuSpaceOpt2(k: Int, array: IntArray): Boolean {

    val n = array.size

    //use array indexes for index and values just for target match
    var prev = BooleanArray(k + 1) {
        false
    }

    //when we reached target
    prev[0] = true

    // for first element matching target
    if (array[0] <= k) prev[array[0]] = true


    for (ind in 1 until n) {

        val curr = BooleanArray(k + 1) {
            false
        }
        curr[0] = true //for some value target has to be true

        for (target in 1..k) {

            //not take
            val notTake = prev[target]

            //take
            var take = false
            if (array[ind] <= target)
                take = prev[target - array[ind]]

            curr[target] = take || notTake
        }

        prev = curr
    }

    return prev[k] // becoz we started recursion from n-1, k
}

fun main() {

    //val arr = intArrayOf(1, 5, 11, 5)
    val arr = intArrayOf(1, 2, 3, 5)

    println(canPartition(arr))
}