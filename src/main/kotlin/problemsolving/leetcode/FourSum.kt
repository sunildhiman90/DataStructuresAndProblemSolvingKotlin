package problemsolving.leetcode

// https://leetcode.com/problems/4sum
fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
    val output = mutableListOf<List<Int>>()
    val n = nums.size

    nums.sort()

    for (i in 0 until n) {

        //if duplicate, just skip
        if (i > 0 && nums[i] == nums[i - 1]) continue

        for (j in i + 1 until n) {

            //if duplicate, just skip
            if (j > i + 1 && nums[j] == nums[j - 1]) continue

            //2 pointers
            var left = j + 1
            var right = n - 1
            while (left < right) {
                val sum = nums[i] + nums[j] + nums[left] + nums[right]
                if (sum < target) {
                    left++
                } else if (sum > target) {
                    right--
                } else {
                    val quad = listOf(nums[i], nums[j], nums[left], nums[right])
                    output.add(quad)

                    //move left and right
                    left++
                    right--

                    //if new index element is same, keep moving until we find different element, until they dont cross each other
                    while (left < right && nums[left] == nums[left - 1]) left++
                    while (left < right && nums[right] == nums[right + 1]) right--
                }
            }
        }
    }
    return output.toList()
}

fun main() {
    val arr = intArrayOf(2, 2, 2, 2, 2)

    println(fourSum(arr, target = 8))
}