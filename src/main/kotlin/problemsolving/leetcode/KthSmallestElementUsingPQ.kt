package problemsolving.leetcode

import java.util.*

class KthSmallestElementUsingPQ {
}

// Approach 1: O(n*log(n))
// FOr Explanation , Read at the bottom.
// If we want to find largest kth element, use decreasing queue,
// then its root will be largest(means kth smallest) out of k elements in queue,
// becoz we will keep removing smaller elements if size of queue exceeds k,
// Eventually in the end,  1st element will be 1st largest element(means kth smallest) out of last k elements
fun kthSmallestElement(nums: IntArray, k: Int) {
    val priorityQueue = PriorityQueue<Int>(Collections.reverseOrder())

    //O(n)
    for (i in nums.indices) {
        priorityQueue.add(nums[i]) //O(logn)

        // In decreasing queue from front to rear, of size k, 1st element is always the kth smallest(means 1st largest) element from those
        if (priorityQueue.size > k) priorityQueue.remove()
    }

    println(priorityQueue.peek())

}

// Approach 2: Simple approach
// Using same ordering, if we want kth smallest element ,
// we will use increasing queue(root will be smallest) or Min PQ, becoz its root will be 1st smallest and then traversing and removing(k-1) elements before k,
// we will get kth smallest
fun kthSmallestElement2(nums: IntArray, k: Int) {
    val priorityQueue = PriorityQueue<Int>() //increasing queue, means smallest will be at head or root

    for (i in nums.indices) {
        priorityQueue.add(nums[i])
    }

    for (i in 0 until k - 1) {
        priorityQueue.remove() // pop k-1 elements from heap
    }

    println(priorityQueue.peek()) //print 1st element now which is kth smallest element

}

fun main() {

    kthSmallestElement(intArrayOf(1, 4, 2, 6, 3, 7, 9, 5, 8), 3)
    kthSmallestElement2(intArrayOf(1, 4, 2, 6, 3, 7, 9, 5, 8), 3)

}


/**
 * Explanation of Approach 1:
 * // If we want to find largest kth element, use increasing queue,
 * // then its root will be smallest out of k elements in queue,
 * // becoz we will keep only k elements in queue by removing elements if size of queue exceeds k,
 * // Eventually in the end,
 * // 1st element will be 1st smallest element(means kth largest) out of last k elements
 *
 *  Example: 1,2,3,4,5,6,7,8,9 and k = 3
 *  After the first k = 3 iterations: queue: [1,2,3]
 *  Here if we see, from 1,2,3  Which is largest?
 *  3 is largest and as total elements in queue are k,
 *  3 is 1st largest it means 2 is 2nd largest, and 1 is kth largest becoz k are total elements
 *
 *  Similarily, aftere teh last iterations, queue will be [7,8,9]
 *
 *  Hence 7 is kth(3rd) largest
 *
 *
 */