package datastructures.queue


class PriorityQueueUsingA(val capacity: Int) {


    // Priority Queue Item
    private class PQItem(var value: Int, var priority: Int)

    private val arr = Array(capacity) { PQItem(0, 0) }

    private var rear = -1

    fun add(data: Int, priority: Int): Boolean {
        check(!isFull()) {
            "Queue is already full"
        }

        arr[++rear] = PQItem(data, priority)
        return true
    }

    fun offer(data: Int, priority: Int): Boolean {
        if (isFull()) {
            return false
        }
        arr[++rear] = PQItem(data, priority)
        return true
    }

    fun remove(): Int {
        check(!isEmpty()) {
            "Queue is empty!"
        }

        val index = getHighestPriorityIndex()
        val element = arr[index].value
        // traverse from highest priority index till rear and shift elements back
        for (i in index until rear) {
            arr[i] = arr[i + 1]
        }
        rear--
        return element
    }

    fun poll(): Int {
        if (isEmpty()) {
            return -1
        }

        val index = getHighestPriorityIndex()
        val element = arr[index].value
        // traverse from highest priority index till rear and shift elements back
        for (i in index until rear) {
            arr[i] = arr[i + 1]
        }
        rear--
        return element
    }

    //it will return
    fun getHighestPriorityIndex(): Int {

        var highestPriority = Int.MIN_VALUE //for holding priority value for highestPriorityIdx
        var highestPriorityIdx = -1 //it will hold the index of some other prev equal priority element

        // return highest priority element
        for (i in 0 .. rear) {

            //if priority is high, use current item
            if (arr[i].priority > highestPriority) {
                highestPriority = arr[i].priority
                highestPriorityIdx = i
            } else if (arr[i].priority == highestPriority &&
                highestPriorityIdx > -1 && arr[i].value > arr[highestPriorityIdx].value
            ) {
                //if priority is equal, we are using item with highest value
                highestPriority = arr[i].priority
                highestPriorityIdx = i
            }
        }

        return highestPriorityIdx

    }

    fun peek(): Int {
        if (isEmpty()) {
            return -1
        }
        return arr[getHighestPriorityIndex()].value
    }

    fun isEmpty(): Boolean {
        return rear == -1
    }

    fun isFull(): Boolean {
        return rear == capacity - 1
    }

}

fun main() {
    val queue = PriorityQueueUsingA(5)
    queue.add(1, 7)
    queue.add(2, 6)
    queue.add(3, 6)
    queue.offer(4, 2)
    queue.add(5, 5)

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

/**
 * getHighestPriorityIndex explanation
 *
 * // if priority is high, use current item
 *   else we need to use item with highest value
 *   and for that we need to compare both item's values so thats why we are using
 *   highestPriority and highestPriorityIdx variables,
 *
 *  CONDITION Exaplained
 *  highestPriorityIdx > -1 && arr[i].value > arr[highestPriorityIdx].value => this condition is checking 2 points given below
 *  1. 1if priority is equal it means highestPriorityIdx will be set and greater than -1
 *  2. and if yes highestPriorityIdx > -1, then check their values if current indexed item has greater value then use that -> arr[i].value > arr[highestPriorityIdx].value
 *
 */