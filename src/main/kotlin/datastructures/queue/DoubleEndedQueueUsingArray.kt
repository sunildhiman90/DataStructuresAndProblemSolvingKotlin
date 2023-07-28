package datastructures.queue


// Using Circular Array
class DoubleEndedQueueUsingArray(private val capacity: Int) {

    private val arr = Array(capacity) { 0 }

    private var front = -1
    private var rear = -1

    // decrement front, Edge case is if queue is empty
    fun addFirst(data: Int): Boolean {
        check(!isFull()) {
            "Queue is already full"
        }

        if (isEmpty()) {
            front = 0
            rear = 0
        } else if (front == 0) {
            // if at first position, then set it to last due to circular concept
            front = capacity - 1
        } else {
            //else normally decrease by 1
            front--
        }

        arr[front] = data
        return true
    }

    // increment rear, Edge case is if queue is empty
    fun addLast(data: Int): Boolean {
        check(!isFull()) {
            "Queue is already full"
        }

        if (isEmpty()) {
            front = 0
            rear = 0
        } else if (rear == capacity - 1) {
            // if at last position, then set it to first due to circular concept
            rear = 0
        } else {
            //else normally increase by 1
            rear++
        }

        arr[rear] = data
        return true
    }

    // increment front, Edge case is if there is single element
    fun removeFirst(): Int {
        check(!isEmpty()) {
            "Queue is empty!"
        }

        val element = arr[front]

        //if single element, make queue empty
        if (front == rear) {
            front = -1
            rear = -1
        } else if (front == capacity - 1) {
            // if at last position, then set it to first due to circular concept
            front = 0
        } else {
            front++
        }

        return element
    }

    // decrement rear, Edge case is if there is single element
    fun removeLast(): Int {
        check(!isEmpty()) {
            "Queue is empty!"
        }

        val element = arr[rear]

        //if single element, make queue empty
        if (front == rear) {
            front = -1
            rear = -1
        } else if (rear == 0) {
            // if at first position, then set it to last due to circular concept
            rear = capacity - 1
        } else {
            rear--
        }

        return element
    }

    fun first(): Int {
        if (isEmpty()) throw NoSuchElementException("ArrayDeque is empty.")
        return arr[front]
    }

    fun firstOrNull(): Int? {
        if (isEmpty()) return null
        return arr[front]
    }

    fun last(): Int {
        if (isEmpty()) throw NoSuchElementException("ArrayDeque is empty.")
        return arr[rear]
    }

    fun lastOrNull(): Int? {
        if (isEmpty()) return null
        return arr[rear]
    }

    fun isEmpty(): Boolean {
        return (front == -1 && rear == -1)
    }

    //Normal case || circular queue case
    fun isFull(): Boolean {
        return (front == 0 && rear == capacity - 1) || (front == rear + 1)
    }

}

fun main() {
    val queue = DoubleEndedQueueUsingArray(6)
    queue.addFirst(1)
    queue.addFirst(2)
    queue.addLast(3)
    queue.addLast(4)
    queue.addFirst(5)
    queue.addLast(5)
    queue.removeFirst()
    queue.removeLast()

    while (!queue.isEmpty()) {
        println(queue.first())
        queue.removeFirst()
    }
    println("is Queue empty now: " + queue.isEmpty())

}

/**
 * Time Complexity:
 * addFirst -> O(1)
 * addLast -> O(1)
 * removeFirst -> O(1)
 * removeLast -> O(1)
 */