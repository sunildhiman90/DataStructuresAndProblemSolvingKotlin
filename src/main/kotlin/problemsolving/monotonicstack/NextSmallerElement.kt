package problemsolving.monotonicstack

import java.util.*

/**
Find next smaller element of each element of array after that element
 */
// Related Problem links :
// https://practice.geeksforgeeks.org/problems/fab3dbbdce746976ba35c7b9b24afde40eae5a04/1

fun main() {

    //val arr = arrayOf(11,13,4,21,3)
    //val arr = arrayOf(10,1,1,6)
    val arr = arrayOf(10, 1, 1, 6, 5, 3, 7, 9, 1, 3, 4)
    println(nextSmallerElementMainCompacted(arr).contentToString())
    println(nextSmallerElementMain(arr).contentToString())
    println(nextSmallerElement2(arr).contentToString())


}

// More compacted code as compared to other solutions here
fun nextSmallerElementMainCompacted(nums: Array<Int>): Array<Int> {
    val finalArray = Array(nums.size) { -1 }
    val stack = Stack<Int>()
    var next: Int

    for ((index, element) in nums.withIndex()) {

        // next num
        next = element

        //use increasing monotonic stack logic for popping, becoz if current(next) element is larger than prev(stack top),
        // then it does not satisfy our criteria, so push to stack for later adding -1, otherwise pop
        while (!stack.isEmpty() && nums[stack.peek()] > next) {
            finalArray[stack.peek()] = next
            stack.pop()
        }

        //push index for finding its next smaller, becoz we need index for creating new array
        stack.push(index)

    }

    return finalArray
}


fun nextSmallerElementMain(nums: Array<Int>): Array<Int> {
    val finalArray = Array(nums.size) { 0 }
    val stack = Stack<Int>()
    var next: Int

    for ((index, element) in nums.withIndex()) {

        // next num
        next = element

        //use increasing monotonic stack logic for popping, becoz if current(next) element is larger than prev(stack top),
        // then it does not satisfy our criteria, so push to stack for later adding -1, otherwise pop
        while (!stack.isEmpty() && nums[stack.peek()] > next) {
            finalArray[stack.peek()] = next
            stack.pop()
        }

        //push index for finding its next smaller, becoz we need index for creating new array
        stack.push(index)

    }

    //print -1 for all remaining
    while (!stack.isEmpty()) {
        finalArray[stack.peek()] = -1
        stack.pop()
    }

    return finalArray
}

//this apparoach wil not work with repeating elements, then for last index repeating element,
// if no smaller element found, then it will replace smaller element of prev repeating element
// For example check this case: 10, 1, 1, 6, 5, 3, 7, 9, 1, 3, 4
// Here 3 is repeated at not consecutive indexes, So for first 3, it will put next smaller as 1,
// but for last 3 it will replace it with -1 becoz after that we dont have smaller element
fun nextSmallerElement2(nums: Array<Int>): Array<Int> {
    val hashMap = mutableMapOf<Int, Int>()
    val stack = Stack<Int>()
    var next: Int

    for (element in nums) {

        // next num
        next = element

        //use increasing monotonic stack logic for popping, becoz if current(next) element is larger than prev(stack top),
        // then it does not satisfy our criteria, so push to stack for later adding -1, otherwise pop
        while (!stack.isEmpty() && stack.peek() > next) {
            hashMap[stack.pop()] = next
        }

        //push next for finding its next smaller
        stack.push(next)

    }

    //print -1 for all remaining
    while (!stack.isEmpty()) {
        hashMap[stack.pop()] = -1
    }

    val finalArr = Array(nums.size) {
        0
    }
    for ((index, num) in nums.withIndex()) {
        if (hashMap.contains(num)) {
            hashMap.get(num)?.let {
                finalArr[index] = it
            }
        }
    }

    return finalArr
}