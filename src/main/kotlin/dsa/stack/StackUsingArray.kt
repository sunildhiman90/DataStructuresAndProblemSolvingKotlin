package dsa.stack

class StackUsingA(val size: Int) {

    private var top = -1

    private val arr: Array<Int> = Array(size) {
        0
    }

    fun empty(): Boolean {
        return top == -1
    }

    fun push(data: Int) {
        if(top == size-1) {
            throw RuntimeException("Oops, Stack is already full!")
        }
        arr[++top] = data
    }

    fun pop(): Int {
        if(empty()) {
            throw RuntimeException("Oops, Stack is already empty!")
        }
        return arr[top--]
    }

    fun peek(): Int {
        if(empty()) {
            throw RuntimeException("Oops, Stack is already empty!")
        }
        return arr[top]
    }

}

fun main() {

    val stack = StackUsingA(5)
    stack.push(1)
    stack.push(2)
    stack.push(3)
    stack.push(4)
    stack.push(5)
    println("Is Empty before traversal and popping = "+stack.empty())
    while(!stack.empty()) {
        println(stack.peek())
        stack.pop()
    }
    println("Is Empty after traversal and popping = "+stack.empty())

}
