package dsa.stack

class StackUsingAL {

    private var top = -1

    private val mList = mutableListOf<Int>()

    fun empty(): Boolean {
        return top == -1
    }

    fun push(data: Int) {
        top++
        mList.add(data)
    }

    fun pop(): Int {
        if(empty()) {
            throw RuntimeException("Oops, Stack is already empty!")
        }
        val pop = mList[top--]
        mList.remove(pop)
        return pop
    }

    fun peek(): Int {
        if (empty()) {
            throw RuntimeException("Oops, Stack is already empty!")
        }
        return mList[top]
    }

}

fun main() {

    val stack = StackUsingAL()
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

/**
 * Time Complexity:
 * push -> O(1)
 * pop -> O(1)
 * peek -> O(1)
 *
 * Stack is a very useful data structure with many uses. It is an essential part of every program as all the programming
 * languages internally use stack for function calls and many more operations.
 */
