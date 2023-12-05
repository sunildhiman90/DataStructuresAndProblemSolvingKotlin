package datastructures.arrays.easy

//https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/

fun check(nums: IntArray): Boolean {
    //Main logic is to check for the case if prev elemnt is greater than current, It will happen only once if array is sorted and rotated
    val n = nums.size
    var countOfPrevGreater = 0
    for (i in 1 until n) {
        if (nums[i - 1] > nums[i]) countOfPrevGreater++
    }

    //check for the case of first elemtn and last element as well if array is rotated, it will not be true, else it will be true
    if (nums[n - 1] > nums[0]) countOfPrevGreater++

    return countOfPrevGreater <= 1 // if its only one or zero
}


fun main() {
    val arr = intArrayOf(3, 4, 5, 1, 2) //sorted and rotated by 2 places
    val arr2 = intArrayOf(2, 1, 3, 4) // not sorted and rotated
    val arr3 = intArrayOf(1, 2, 3, 4) //sorted and rotated by 0 places
    println(check(arr))
    println(check(arr2))
    println(check(arr3))
}