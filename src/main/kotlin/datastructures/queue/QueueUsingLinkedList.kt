package datastructures.queue

class QueueUsingLL {

    class Node(val data: Int, var next: Node? = null)

    private var head: Node? = null // front
    private var tail: Node? = null // rear

    // If front is separate variable(here is head) then in add method, we need to consider isEmpty
    fun add(data: Int): Boolean {
        val node = Node(data)
        //if empty, need to increase tail and head initially
        if (isEmpty()) {
            head = node
            tail = node
            return true
        }
        tail?.next = node
        tail = node
        return true
    }

    fun offer(data: Int): Boolean {
        val node = Node(data)
        if (isEmpty()) {
            head = node
            tail = node
            return true
        }
        tail?.next = node
        tail = node
        return true
    }


    fun remove(): Int? {
        if (isEmpty()) {
            throw IllegalStateException("Queue is already empty")
        }
        val element = head?.data
        if (head === tail) tail = null //if single element, make tail also null
        head = head?.next
        return element
    }

    fun poll(): Int? {
        if (isEmpty()) {
            return -1
        }
        val element = head?.data
        if (head === tail) tail = null //if single element, make tail also null
        head = head?.next
        return element
    }

    fun peek(): Int? {
        if (isEmpty()) {
            return -1
        }
        return head?.data
    }

    fun isEmpty(): Boolean {
        return head == null && tail == null
    }
}

fun main() {
    val queue = QueueUsingLL()
    queue.add(1)
    queue.add(2)
    queue.add(3)
    queue.offer(4)
    queue.add(5)

    while (!queue.isEmpty()) {
        println(queue.peek())
        queue.remove()
    }
    println("is Queue empty now: " + queue.isEmpty())
}


/**
 * Time COMplexity:
 * <p>
 * Add -> O(1)
 * remove -> O(1) //dont requires shifting of elements
 */