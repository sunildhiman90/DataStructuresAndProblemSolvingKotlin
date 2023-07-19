package problemsolving.monotonicstack

import java.util.*

/**
 Find next smaller element of each element of array after that element
 */
 // Related Problem links :
 // https://practice.geeksforgeeks.org/problems/fab3dbbdce746976ba35c7b9b24afde40eae5a04/1
class NextSmallerElement {
}

fun main() {

    val arr = arrayOf(11,13,4,21,3)
    println(nextSmallerElement(arr).contentToString())

}

fun nextSmallerElement(nums: Array<Int>): Array<Int> {
    val hashMap = mutableMapOf<Int, Int>()
    val stack = Stack<Int>()
    var element = nums[0]
    var next = -1

    stack.push(element)

    for(i in 1 until nums.size) {

        // next num
        next = nums[i]

        if(!stack.isEmpty()) {
            //pop element for checking
            element = stack.pop()

            while(element > next) {
                hashMap[element] = next

                // We are not using this stack is not empty condition in while loop: while(!stack.isEmpty() && element < next)
                // becoz we are already popping one element before loop, so then this logic will not work for single element in stack
                if(stack.isEmpty()) break
                //pop and check for popped element next time
                element = stack.pop()
            }

            //element is smaller than next, then push it back, It wil make sure elements in stack will be in increasing order
            if (element < next)
                stack.push(element)

        }

        //push next for finding its next greater
        stack.push(next)

    }

    //print -1 for all remaining
    while(!stack.isEmpty()) {
        element = stack.pop()
        hashMap[element] = -1
    }

    val finalArr = Array(nums.size) {
        0
    }
    for((index, num) in nums.withIndex()) {
        if(hashMap.contains(num)) {
            hashMap.get(num)?.let {
                finalArr[index] = it
            }
        }
    }

    return finalArr
}