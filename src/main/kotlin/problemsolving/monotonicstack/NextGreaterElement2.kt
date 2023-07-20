package problemsolving.monotonicstack

import java.util.*

// Find next greater element of each element of array after that element, we can search in circular array as well
// https://leetcode.com/problems/next-greater-element-ii/

fun main() {

    val arr = arrayOf(1,2,3,4,3)
    println(nextGreaterElement2MainCompact(arr).contentToString())
    println(nextGreaterElement2Main(arr).contentToString())

}

// More compacted code as compared to solution 2 given below
fun nextGreaterElement2MainCompact(nums: Array<Int>): IntArray {
    //directly initialize array to -1 here for all elements, and later for which we will find greater only that will be updated
    // and we dont need 2nd while loop for putting -1 for remaining
    val finalArray = IntArray(nums.size) { -1 }
    val stack = Stack<Int>()
    var next: Int

    //loop twice for making circular array
    for (i in 0 until 2 * nums.size) {

        val circularIndex = i % nums.size

        // next num
        next = nums[circularIndex]

        // use decreasing monotonic stack logic for popping, becoz if current(next) element is smaller than prev(stack top),
        // then it does not satisfy our criteria, so push to stack for later adding -1
        // so stack will contain elements which does not satisfy our criteria ,
        // otherwise pop from stack becoz our criteria is satisfied
        while (!stack.isEmpty() && nums[stack.peek()] < next) {
            finalArray[stack.peek()] = next
            stack.pop()
        }

        // becoz we are looping in circular array, same index will come twice,
        // so push circularIndex only if its not found yet(if finalArray[circularIndex] == -1) for finding its next greater
        // this index will be used for creating new array,
        if(finalArray[circularIndex] == -1)
            stack.push(circularIndex)

    }

    return finalArray
}


// Solution 2
fun nextGreaterElement2Main(nums: Array<Int>): Array<Int> {
    val finalArray = Array(nums.size) { 0 }
    val stack = Stack<Int>()
    var next: Int

    //loop twice for making circular array
    for (i in 0 until 2 * nums.size) {

        val circularIndex = i % nums.size
        // next num
        next = nums[circularIndex]

        //use decreasing monotonic stack logic for popping, becoz if current(next) element is smaller than prev(stack top),
        // then it does not satisfy our criteria, so push to stack for later adding -1 so stack will contain elements which does not satisfy our criteria ,
        // otherwise pop from stack becoz our criteria is satisfied
        while (!stack.isEmpty() && nums[stack.peek()] < next) {
            finalArray[stack.peek()] = next
            stack.pop()
        }

        //push circularIndex only if its not found yet(if finalArray[circularIndex] == 0) for finding its next greater, becoz we need index for creating new array, use circular condition for index so that index will be correct only from 0 to size-1
        if(finalArray[circularIndex] == 0)
            stack.push(circularIndex)

    }

    //print -1 for all remaining
    while (!stack.isEmpty()) {
        finalArray[stack.peek()] = -1
        stack.pop()
    }

    return finalArray
}
