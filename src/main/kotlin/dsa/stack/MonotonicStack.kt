package dsa.stack

import java.util.Stack

fun main() {

    //val arr = arrayOf(11,13,21,3)
    val arr = arrayOf(1, 4, 5, 3, 12, 10)
    println(increasingMonotonicStack(arr))

    println(decreasingMonotonicStack(arr))

}

//Elements will be in increasing order from bottom to top
fun increasingMonotonicStack(arr: Array<Int>): Stack<Int> {
    val stack = Stack<Int>()

    for (num in arr) {

        // As elements wil be in increasing order as we are pushing them,
        // SO we need to pop all elements for pushing current element which are greater than this on stack and then push
        while (!stack.isEmpty() && stack.peek() > num) {
            stack.pop()
        }
        stack.push(num)
    }

    return stack
}

//Elements will be in decreasing order from bottom to top
fun decreasingMonotonicStack(arr: Array<Int>): Stack<Int> {
    val stack = Stack<Int>()

    for (num in arr) {

        // As elements wil be in decreasing order as we are pushing them,
        // SO we need to pop all elements for pushing current element which are smaller than this on stack and then push
        while (!stack.isEmpty() && stack.peek() < num) {
            stack.pop()
        }
        stack.push(num)
    }

    return stack
}

