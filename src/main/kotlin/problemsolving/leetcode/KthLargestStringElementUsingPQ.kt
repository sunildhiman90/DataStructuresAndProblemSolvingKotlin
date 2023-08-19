package problemsolving.leetcode

import java.util.*

class KthLargestStringElementUsingPQ {
}

// Approach 1: O(n*log(n))
// FOr Explanation , Read at the bottom.
// If we want to find largest kth element, use increasing queue,
// then its root will be smallest out of k elements in queue,
// becoz we will keep removing smaller elements if size of queue exceeds k,
// Eventually in the end,  1st element will be 1st smallest element(means kth largest) out of last k elements
// SAME APPROACH AS that of kth largest element,
// but we need custom comparator, becoz by default String comparator will compare them by lexicographically char by char
fun kthLargestStringElement(nums: Array<String>, k: Int): String {
    val priorityQueue = PriorityQueue<String> { o1, o2 ->
        if (o1.length == o2.length) { // If the same length then compare by their string
            return@PriorityQueue o1.compareTo(o2)
        }
        o1.length.compareTo(o2.length) // Compare by their length
    }

    // val priorityQueue = PriorityQueue<String>() use this for testing the difference between custom comparator and natural comparator
    //O(n)
    for (i in nums.indices) {
        priorityQueue.add(nums[i]) //O(logn)

        // In increasing queue from front to rear, of size k, 1st element is always the kth largest(means 1st smallest) element from those
        if (priorityQueue.size > k) priorityQueue.remove()
    }

    return priorityQueue.peek()

}

fun main() {

    println(kthLargestStringElement(arrayOf("111", "4", "2", "66"), 3))

}
