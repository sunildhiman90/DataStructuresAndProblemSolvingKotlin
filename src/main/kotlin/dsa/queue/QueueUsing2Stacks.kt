package dsa.queue

import java.util.*

class QueueUsing2Stacks {
}


class Queue {

    private val stack1: Stack<Int> = Stack()
    private val stack2: Stack<Int> = Stack()

    fun add(value: Int) {
        //copy all elements of stack 1 into stack2
        while (stack1.isNotEmpty()) {
            stack2.push(stack1.pop())
        }
        stack1.push(value) //push new value

        //copy back all elements of stack 2 into stack1,. this will reverse the order from LIFO to FIFO
        while (stack2.isNotEmpty()) {
            stack1.push(stack2.pop())
        }
    }

    fun remove(): Int {
        if(stack1.isEmpty()) return -1
        return stack1.pop()
    }

    fun peek(): Int {
        if(stack1.isEmpty()) return -1
        return stack1.peek()
    }

    fun isEmpty(): Boolean {
        return stack1.isEmpty()
    }

    fun isNotEmpty(): Boolean {
        return !stack1.isEmpty()
    }

}

fun main() {
    val queue = Queue()
    queue.add(1)
    queue.add(2)
    queue.add(3)

    println("Is queue empty: ${queue.isEmpty()}")

    while (!queue.isEmpty()) {
        println(queue.peek())
        queue.remove()
    }

    println("Is queue empty: ${queue.isEmpty()}")

}



/**
 * Time Complexity:
 * <p>
 * Add -> O(n) //require pushing of all elements from stack1 to stack2
 * remove -> O(1) //
 * <p>
 * <p>
 * Space Complexity:
 * O(n) //require 2 extra stacks of size n
 */