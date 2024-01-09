package datastructures.queue


// Edge Cases:
// add:- NO edge case
// remove: shift elements back after deleting front
class QueueUsingA(val capacity: Int) {

    private val arr = IntArray(capacity)

    private var rear = -1

    fun add(data: Int): Boolean {
        check(!isFull()) {
            "Queue is already full"
        }
        arr[++rear] = data
        return true
    }

    fun offer(data: Int): Boolean {
        if (isFull()) {
            return false
        }
        arr[++rear] = data
        return true
    }

    fun remove(): Int {
        check(!isEmpty()) {
            "Queue is empty!"
        }
        val element = arr[0] // first element is the front,no need to talke extra variable
        // only need to traverse till rear
        for (i in 0 until rear) {
            arr[i] = arr[i + 1]
        }
        rear--
        return element
    }

    fun poll(): Int {
        if (isEmpty()) {
            return -1
        }
        val element = arr[0]
        for (i in 0 until rear) {
            arr[i] = arr[i + 1]
        }
        rear--
        return element
    }

    fun peek(): Int {
        if (isEmpty()) {
            return -1
        }
        return arr[0]
    }

    fun isEmpty(): Boolean {
        return rear == -1
    }

    fun isFull(): Boolean {
        return rear == capacity - 1
    }

}

fun main() {
    val queue = QueueUsingA(5)
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
 * Time Complexity:
 * Add -> O(1)
 * remove -> O(n) //requires shifting of elements
 */