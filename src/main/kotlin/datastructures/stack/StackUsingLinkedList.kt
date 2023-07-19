package datastructures.stack

class StackUsingLinkedList {
}

class Node(var data: Int, var next: Node? = null)

class Stack {

    private var head: Node? = null

    fun empty(): Boolean {
        return head == null
    }

    fun push(data: Int) {
        var newNode = Node(data)
        if(empty()) {
            head = newNode
            return
        }
        newNode.next = head
        head = newNode
    }

    fun pop(): Int? {
        if(empty()) {
            return -1
        }
        val pop = head?.data
        head = head?.next
        return pop
    }

    fun peek(): Int? {
        if(empty()) {
            return -1
        }
        return head?.data
    }

}

fun main() {

    val stack = Stack()
    stack.push(1)
    stack.push(2)
    stack.push(3)

    println("is Stack Empty:" + stack.empty())

    while (!stack.empty()) {
        println("pop is:" + stack.peek())
        stack.pop()
    }

    stack.pop()
    stack.pop()
    stack.pop()

    println("is Stack Empty Now:" + stack.empty())
}


/**
 * Approach:
 * push: insert before head -> To make it top, Head will be top
 * pop: delete from head -> To delete from top, Head is top
 *
 */