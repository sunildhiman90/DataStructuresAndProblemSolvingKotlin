package datastructures.stack


import java.util.Stack


fun pushAtTheBottom(stack: Stack<Int>, data: Int) {
    if (stack.isEmpty()) {
        stack.push(data)
        return
    }
    val top = stack.pop()
    pushAtTheBottom(stack, data)
    stack.push(top)
}

fun main() {

    val stack: Stack<Int> = Stack()
    stack.push(1)
    stack.push(2)
    stack.push(3)
    stack.push(4)

    pushAtTheBottom(stack, data = 0)

    println("Stack after pushing at the bottom")
    while (!stack.isEmpty()) {
        println(stack.peek())
        stack.pop()
    }
}
