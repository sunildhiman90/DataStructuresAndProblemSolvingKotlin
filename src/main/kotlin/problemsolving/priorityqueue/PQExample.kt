package problemsolving.priorityqueue

import java.util.*

class PQExample {
}

/**
 * The time complexity of inserting an element into a Priority Queue is O(log n),
 * while removing an element takes O(1) time.
 */
fun main() {

    println("---Min PQ----") // natural increasing order
    val priorityQueue = PriorityQueue<Int>()
    priorityQueue.add(5)
    priorityQueue.add(3)
    priorityQueue.add(8)
    priorityQueue.add(1)

    //By Default PriorityQueue maintains natural ordering, means Min PriorityQueue,
    // Becoz head or first element is minimum out of all elements of list

    while (!priorityQueue.isEmpty()) {
        println(priorityQueue.poll())
    }


    //Reverse Order, means decreasing order, means Max PriorityQueue, head is max

    println("---Max PQ----")
    val priorityQueue2 = PriorityQueue<Int>(Collections.reverseOrder())
    priorityQueue2.add(5)
    priorityQueue2.add(3)
    priorityQueue2.add(8)
    priorityQueue2.add(1)

    while (!priorityQueue2.isEmpty()) {
        println(priorityQueue2.poll())
    }


    println("---String PQ----")

    val priorityQueue3 = PriorityQueue<String>() // it compares lexicographically, char by char
    priorityQueue3.add("5")
    priorityQueue3.add("3")
    priorityQueue3.add("116")
    priorityQueue3.add("113")
    priorityQueue3.add("12")
    // this will print 113  116  12  3  5, Becoz 113 and 116 and 12 have first char as 1, so it compared them with 5, 8 and it treated them as smaller than them
    // then out of 113 and 116 and 12, it compared second char and decided 12 > both 113 and 116
    // and finally out of 113 and 116, then it compared 3rd char, so 113 < 116.


    while (!priorityQueue3.isEmpty()) {
        println(priorityQueue3.poll())
    } //113 116 3 5

    println("---String With Custom Comparators PQ----")

    val priorityQueue4 = PriorityQueue<String> { o1, o2 ->
        if (o1.length == o2.length) { // If the same length then compare by their string
            return@PriorityQueue o1.compareTo(o2)
        }
        o1.length.compareTo(o2.length) // Compare by their length
    }
    priorityQueue4.add("5")
    priorityQueue4.add("3")
    priorityQueue4.add("116")
    priorityQueue4.add("113")
    priorityQueue4.add("12")
    // this will print  3  5 12 113  116


    while (!priorityQueue4.isEmpty()) {
        println(priorityQueue4.poll())
    }
}