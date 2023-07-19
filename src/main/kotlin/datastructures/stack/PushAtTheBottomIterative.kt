package datastructures.stack

import java.util.Stack

class PushAtTheBottomIterative {
}


fun pushAtTheBottomIterative(stack: Stack<Int>, data: Int) {
    val temp: Stack<Int> = Stack()

    while (!stack.isEmpty()) {
        temp.push(stack.pop())
    }

    stack.push(data)

    while (!temp.isEmpty()) {
        stack.push(temp.pop())
    }

}

fun main() {

    val stack: Stack<Int> = Stack()
    stack.push(1)
    stack.push(2)
    stack.push(3)
    stack.push(4)

    pushAtTheBottomIterative(stack, 0)

    println("Stack")
    while (!stack.isEmpty()) {
        println(stack.peek())
        stack.pop()
    }
}

/**
 * Time Complexity: O(N)
 * Auxiliary Space: O(N)
 */