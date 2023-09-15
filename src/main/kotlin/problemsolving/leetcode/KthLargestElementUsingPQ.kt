package problemsolving.leetcode

import java.util.*

// https://leetcode.com/problems/kth-largest-element-in-an-array

// Approach 1: O(n*log(n))
// FOr Explanation , Read at the bottom.
// If we want to find largest kth element, use increasing queue,
// then its root will be smallest out of k elements in queue,
// becoz we will keep removing smaller elements if size of queue exceeds k,
// Eventually in the end,  1st element will be 1st smallest element(means kth largest) out of last k elements
fun kthLargestElement(nums: IntArray, k: Int) {
    val priorityQueue = PriorityQueue<Int>()

    //O(n)
    for (i in nums.indices) {
        priorityQueue.add(nums[i]) //O(logn)

        // In increasing queue from front to rear, of size k, 1st element is always the kth largest(means 1st smallest) element from those
        if (priorityQueue.size > k) priorityQueue.remove()
    }

    println(priorityQueue.peek())

}

// Approach 2: Simple approach
// Using same ordering, if we want kth largest element ,
// we will use decreasing queue(root will be largest) or Max PQ, becoz its root will be 1st largest and then traversing till k,
// we will get kth largest
fun kthLargestElement2(nums: IntArray, k: Int) {
    val priorityQueue =
        PriorityQueue<Int>(Collections.reverseOrder()) //decreasing queue, means largest will be at head or root

    for (i in nums.indices) {
        priorityQueue.add(nums[i])
    }

    for (i in 0 until k - 1) {
        priorityQueue.remove() // pop k-1 elements from heap
    }

    println(priorityQueue.peek()) //print 1st element now which is kth largest element

}

fun main() {

    kthLargestElement(intArrayOf(1, 4, 2, 6, 3, 7, 9, 5, 8), 3)
    kthLargestElement2(intArrayOf(1, 4, 2, 6, 3, 7, 9, 5, 8), 3)
    kthLargestElement2(intArrayOf(1, 1), 2)

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