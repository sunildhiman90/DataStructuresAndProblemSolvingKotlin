package dsa.arrays

import java.util.*

class SortArrayOfZerosOnes2s {
}

fun main() {
    var nums = arrayOf(2,2,1,0,1,0)
    dsa.arrays.sortArray(nums)
    println(nums.contentToString())
}

fun sortArray(nums: Array<Int>) {
    var cnt0 = 0
    var cnt1 = 0
    for (num in nums) {
        when (num) {
            0 -> cnt0++
            1 -> cnt1++
        }
    }

    for (index in nums.indices) {
        if(index < cnt0) {
            nums[index] = 0
        } else if(index < cnt0 + cnt1) {
            nums[index] = 1
        } else nums[index] = 2
    }
}