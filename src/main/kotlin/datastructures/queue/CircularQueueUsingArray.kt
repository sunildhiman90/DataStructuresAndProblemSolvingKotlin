package datastructures.queue

class CircularQueueUsingArray {
}

class CircularQueueA(private val capacity: Int) {

    private val arr = Array(capacity) {
        0
    }

    private var front = -1
    private var rear = -1

    fun add(data: Int) {
        //it will throw IllegalStateException with custom lazymessage if condition is false(queue isFull), we require !isFull()
        check(!isFull()) {
            "Queue is full"
        }
        if (isEmpty()) {
            front++
        }
        rear = (rear + 1) % capacity
        arr[rear] = data
    }


    fun remove(): Int {
        check(!isEmpty()) {
            "Queue is empty"
        }

        //single element case
        if(front == rear) {
            val element = arr[front]
            front = -1
            rear = -1
            return element
        }

        //no shifting here, because we will move rear to the beginning for adding new element rather than shifting elements
        val element = arr[front]
        front = (front + 1) % capacity //for deleting element
        return element
    }

    fun peek(): Int {
        check(!isEmpty()) {
            "Queue is empty"
        }
        return arr[front]
    }

    fun isEmpty(): Boolean {
        return rear == -1 && front == -1
    }

    fun isFull(): Boolean {
        return (rear + 1) % capacity == front
    }


}

fun main() {

    val q = CircularQueueA(5)
    q.add(1)
    q.add(2)
    q.add(3)
    q.add(4)

    while (!q.isEmpty()) {
        println(q.peek())
        q.remove()
    }

    println("Is Queue empty: ${q.isEmpty()}")
}

/**
 * Time Complexity:
 *
 * add :- O(1)
 * remove :- O(1) //no shifting of elements
 * peek :- O(1)
 *
 */
