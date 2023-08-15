package problemsolving.monotonicstack

import java.util.*

// Find next greater element of each element of array after that element
// https://leetcode.com/problems/next-greater-element-i/
class NextGreaterElement

fun main() {

    val arr = arrayOf(11, 13, 21, 3, 5, 3)
    println(nextGreaterElementMainCompacted(arr).contentToString()) // 13, 21, -1, 5, -1, -1
    println(nextGreaterElementMain(arr).contentToString())
    println(nextGreaterElementUsingHashmap(arr).contentToString())

}


// compacted code as compared to other solutions here
fun nextGreaterElementMainCompacted(nums: Array<Int>): Array<Int> {
    // directly initialize array to -1 here for all elements, and later for which we will find greater only that will be updated
    // and we dont need 2nd while loop for putting -1 for remaining
    val finalArray = Array(nums.size) { -1 }
    val stack = Stack<Int>()
    var next: Int

    for ((index, element) in nums.withIndex()) {

        // next num
        next = element

        // Find all elements for which next is greater. Means we will remove from stack all elements for which next is greater
        // and then set in finalArray
        // thats why use decreasing monotonic stack logic for popping, becoz if current(next) element is smaller than prev(stack top),
        // then it does not satisfy our criteria, so push to stack for later adding -1 so stack will contain elements which does not satisfy our criteria ,
        // otherwise pop from stack becoz our criteria is satisfied
        while (!stack.isEmpty() && nums[stack.peek()] < next) {
            finalArray[stack.peek()] = next
            stack.pop()
        }

        //push index for finding its next greater, becoz we need index for creating new array
        stack.push(index)

    }

    return finalArray
}


fun nextGreaterElementMain(nums: Array<Int>): Array<Int> {
    val finalArray = Array(nums.size) { 0 }
    val stack = Stack<Int>()
    var next: Int

    for ((index, element) in nums.withIndex()) {

        // next num
        next = element

        // Find all elements for which next is greater..
        // thats why use decreasing monotonic stack logic for popping, becoz if current(next) element is smaller than prev(stack top),
        // then it does not satisfy our criteria, so push to stack for later adding -1 so stack will contain elements which does not satisfy our criteria ,
        // otherwise pop from stack becoz our criteria is satisfied
        while (!stack.isEmpty() && nums[stack.peek()] < next) {
            finalArray[stack.peek()] = next
            stack.pop()
        }

        //push index for finding its next greater, becoz we need index for creating new array
        stack.push(index)

    }

    //print -1 for all remaining
    while (!stack.isEmpty()) {
        finalArray[stack.peek()] = -1
        stack.pop()
    }

    return finalArray
}


// this approach wil not work with repeating elements at non consecutive(not continuously but with some other elements in between) indexes ,
// then for last index repeating element, if no greater element found, then it will replace greater element of prev repeating element
// For example check this case: 11, 13, 21, 3, 5, 3
// Here 3 is repeated at not consecutive indexes(3rd and 5th indexes), So for first 3 at 3rd index, it will put next greater as 5,
// but for last 3 at 5th index in 2nd while loop it will replace it with -1 becoz after that we dont have greater element,
// Becoz hashmap cannot have multiple same keys with different values
fun nextGreaterElementUsingHashmap(nums: Array<Int>): Array<Int> {
    val hashMap = mutableMapOf<Int, Int>()
    val stack = Stack<Int>()
    var next: Int

    for (element in nums) {

        // next num
        next = element

        //use decreasing monotonic stack logic for popping, becoz if current(next) element is smaller than prev(stack top),
        // then it does not satisfy our criteria, so push to stack for later adding -1, otherwise pop
        while (!stack.isEmpty() && stack.peek() < next) {
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