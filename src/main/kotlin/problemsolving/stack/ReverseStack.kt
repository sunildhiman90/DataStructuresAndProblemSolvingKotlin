package problemsolving.stack


import java.util.Stack


fun pushAtBottom(stack: Stack<Int>, data: Int) {
    if (stack.isEmpty()) {
        stack.push(data)
        return
    }
    val top = stack.pop()
    pushAtBottom(stack, data)
    stack.push(top)
}

//same as that of pushAtBottom, just 2 changes
fun reverse(stack: Stack<Int>) {
    if (stack.isEmpty()) {
        return
    }
    val top = stack.pop()
    reverse(stack)
    pushAtBottom(stack, top)
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

    reverse(stack)

    println("Stack After reverse")

    while (!stack.isEmpty()) {
        println(stack.peek())
        stack.pop()
    }
}
