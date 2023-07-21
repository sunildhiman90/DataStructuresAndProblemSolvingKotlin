package problemsolving.stack


import java.util.Stack


fun pushAtBottomIterative(stack: Stack<Int>, data: Int) {
    val temp: Stack<Int> = Stack()

    while (!stack.isEmpty()) {
        temp.push(stack.pop())
    }

    stack.push(data)

    while (!temp.isEmpty()) {
        stack.push(temp.pop())
    }

}

//O(n*n)
fun reverseStackIterative(stack: Stack<Int>) {
    val temp: Stack<Int> = Stack()

    while (!stack.isEmpty()) {
        temp.push(stack.pop())
    }

    while (!temp.isEmpty()) { //O(n)
        pushAtBottomIterative(stack, temp.pop()); //O(n)
    }

}

fun main() {

    val stack: Stack<Int> = Stack()
    stack.push(1)
    stack.push(2)
    stack.push(3)
    stack.push(4)

    println("Stack Before reverse")
    while (!stack.isEmpty()) {
        println(stack.peek())
        stack.pop()
    }

    stack.push(1)
    stack.push(2)
    stack.push(3)
    stack.push(4)

    reverseStackIterative(stack)

    println("Stack After reverse")

    while (!stack.isEmpty()) {
        println(stack.peek())
        stack.pop()
    }
}
