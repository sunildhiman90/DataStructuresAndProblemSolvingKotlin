package datastructures.queue

import java.util.*

class QueueUsing2Stacks {
}

// Using top as front, this is remove efficient queue
class Queue {

    private val stack1: Stack<Int> = Stack()
    private val stack2: Stack<Int> = Stack()

    // O(n)
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

    // O(1)
    fun remove(): Int {
        if (stack1.isEmpty()) return -1
        return stack1.pop()
    }

    fun peek(): Int {
        if (stack1.isEmpty()) return -1
        return stack1.peek()
    }

    fun isEmpty(): Boolean {
        return stack1.isEmpty()
    }

    fun isNotEmpty(): Boolean {
        return !stack1.isEmpty()
    }

}

// Use top as rear, add efficient O(1) queue
class Queue2 {

    private val stack1: Stack<Int> = Stack()
    private val stack2: Stack<Int> = Stack()

    // O(n)
    fun add(value: Int) {
        stack1.push(value) //push new value
    }

    // O(1)
    fun remove(): Int {
        if (stack1.isEmpty()) return -1

        // remove using 2 stacks
        //copy all elements of stack 1 into stack2
        while (stack1.isNotEmpty()) {
            stack2.push(stack1.pop())
        }

        // remove stack 2 top, this will be the first element of stack1, it will make FIFO
        val element = stack2.pop()

        //copy back all remaining elements of stack 2 into stack1,. this will reverse the order from LIFO to FIFO
        while (stack2.isNotEmpty()) {
            stack1.push(stack2.pop())
        }

        return element
    }

    fun peek(): Int {
        if (stack1.isEmpty()) return -1

        //copy all elements of stack 1 into stack2
        while (stack1.isNotEmpty()) {
            stack2.push(stack1.pop())
        }

        // peek stack 2 top, this will be the first element of stack1, it will make FIFO
        val element = stack2.peek()

        //copy back all remaining elements of stack 2 into stack1,. this will reverse the order from LIFO to FIFO
        while (stack2.isNotEmpty()) {
            stack1.push(stack2.pop())
        }

        return element
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

    println("Is queue2 empty: ${queue.isEmpty()}")

    val queue2 = Queue2()
    queue2.add(4)
    queue2.add(5)
    queue2.add(6)

    println("Is queue empty: ${queue2.isEmpty()}")

    while (!queue2.isEmpty()) {
        println(queue2.peek())
        queue2.remove()
    }

    println("Is queue empty: ${queue2.isEmpty()}")

}


/**
 * Time Complexity Queue:
 * <p>
 * Add -> O(n) //require pushing of all elements from stack1 to stack2
 * remove -> O(1) //
 * peek -> O(1) //
 * <p>
 * <p>
 * Space Complexity:
 * O(n) //require 2 extra stacks of size n
 */

/**
 * Time Complexity Queue2:
 * <p>
 * Add -> O(1)
 * remove -> O(n) // require pushing of all elements from stack1 to stack2
 * peek -> O(n) // require pushing of all elements from stack1 to stack2
 * <p>
 * <p>
 * Space Complexity:
 * O(n) //require 2 extra stacks of size n
 */