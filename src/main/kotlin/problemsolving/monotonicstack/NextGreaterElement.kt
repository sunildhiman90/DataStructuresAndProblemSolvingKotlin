package problemsolving.monotonicstack

import java.util.*

// Find next greater element of each element of array after that element
// https://leetcode.com/problems/next-greater-element-i/
class NextGreaterElement {
}

fun main() {

    val arr = arrayOf(11, 13, 21, 3, 5, 3)
    println(nextGreaterElementMain(arr).contentToString())
    println(nextGreaterElement(arr).contentToString())

}

fun nextGreaterElementMain(nums: Array<Int>): Array<Int> {
    val finalArray = Array(nums.size) { 0 }
    val stack = Stack<Int>()
    var next: Int

    for ((index, element) in nums.withIndex()) {

        // next num
        next = element

        //use decreasing monotonic stack logic for popping, becoz if current(next) element is smaller than prev(stack top),
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


//this apparoach wil not work with repeating elements at non consecutive(not continuously but with some other elements in between) indexes , then for last index repeating element,
// if no greater element found, then it will replace greater element of prev repeating element
// For example check this case: 11, 13, 21, 3, 5, 3
// Here 3 is repeated at not consecutive indexes(3rd and 5th indexes), So for first 3 at 3rd index, it will put next greater as 5,
// but for last 3 at 5th index in 2nd while loop it will replace it with -1 becoz after that we dont have greater element, Becoz hashmap cannot have multiple same keys with different values
fun nextGreaterElement(nums: Array<Int>): Array<Int> {
    val hashMap = mutableMapOf<Int, Int>()
    val stack = Stack<Int>()
    var element = nums[0]
    var next = -1

    stack.push(element)

    for (i in 1 until nums.size) {

        // next num
        next = nums[i]

        if (!stack.isEmpty()) {
            //pop element for checking
            element = stack.pop()

            while (element < next) {
                hashMap[element] = next

                // We are not using this stack is not empty condition in while loop: while(!stack.isEmpty() && element < next)
                // becoz we are already popping one element before loop, so then this logic will not work for single element in stack
                if (stack.isEmpty()) break
                //pop and check for popped element next time
                element = stack.pop()
            }

            //element is greater than next, then push it back, It wil make sure elements in stack will be in decreasing order
            if (element > next)
                stack.push(element)

        }

        //push next for finding its next greater
        stack.push(next)

    }

    //print -1 for all remaining
    while (!stack.isEmpty()) {
        element = stack.pop()
        hashMap[element] = -1
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