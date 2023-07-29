package datastructures.queue


// Edge Cases:
// add:- check isEmpty and if empty then increase front also
// remove: shift elements back after deleting front
class QueueUsingAL {

    private val arr = mutableListOf<Int>()

    private var front = -1
    private var rear = -1

    // If front is separate variable then in add method, we need to consider isEmpty case, else not (as in Queue using array)
    fun add(data: Int): Boolean {
        if (isEmpty()) front++
        arr.add(data)
        rear++
        return true
    }

    fun offer(data: Int): Boolean {
        if (isEmpty()) front++
        arr.add(data)
        rear++
        return true
    }

    fun remove(): Int {
        check(!isEmpty()) {
            "Queue is already empty"
        }
        val element = arr[front]
        arr.remove(element) //it will shift the elements to left, so increasing front wil skip some elements then,
        // If we want to use remove here, then dont increase front, but decrease rear instead due to shifting of elements to left
        rear--
        return element
    }

    /**
     *  alternative way2, dont delete elements, but just increase front, but not good from memory perspective,
     *  And here we need to check front > rear as well for empty case,
     *  becoz increasing front will make it go beyond rear when we traversed all elements
     */
    fun removeAlt(): Int {
        check(!isEmpty()) {
            "Queue is already empty"
        }
        val element = arr[front]
        front++ // If we dont want to use remove here, then increase front
        return element
    }

    fun poll(): Int {
        if (isEmpty()) {
            return -1
        }
        val element = arr[front]
        arr.remove(element)
        rear--
        return element
    }

    fun peek(): Int {
        if (isEmpty()) {
            return -1
        }
        return arr[front]
    }

    fun isEmpty(): Boolean {
        return rear == -1
    }

    //alternative for removeAlt method
//    fun isEmpty(): Boolean {
//        return rear == -1
//    }

}

fun main() {
    val queue = QueueUsingAL()
    queue.add(1)
    queue.add(2)
    queue.add(3)
    queue.offer(4)
    queue.add(5)

    while (!queue.isEmpty()) {
        println(queue.remove())
        //queue.remove()
    }
    println("is Queue empty now: " + queue.isEmpty())

}


/**
 * Time Complexity:
 * <p>
 * Add -> O(1),
 * O(n) -> in worst case when size increases, new array need to be created and need to copy all elements to that array, so it become O(n)
 * <p>
 * remove -> O(n) // requires shifting of elements if removing element from not last index, But if removing from last index, it does not require shifting
 * <p>
 */