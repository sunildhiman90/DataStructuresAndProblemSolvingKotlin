package problemsolving.leetcode

import java.util.*

//https://leetcode.com/problems/sliding-window-maximum


// MAIN APPROACH: O(n) Using Dequeue
fun maxSlidingWindow(arr: IntArray, k: Int): IntArray {
    val currentWindowQueue: Deque<Int> = LinkedList()

    //Calculate for first window
    for (i in 0 until k) {

        // Keep updating the largest value index, remove all elements indexes whose values are smaller from current index value
        while (!currentWindowQueue.isEmpty() && arr[currentWindowQueue.peekLast()] <= arr[i]) {
            currentWindowQueue.removeLast() //remove from last only, so that dequeue first element will maintain largest value index for current window
        }
        currentWindowQueue.addLast(i)
    }

    val runningList = mutableListOf<Int>()
    runningList.add(arr[currentWindowQueue.peekFirst()])
    for (i in k until arr.size) {

        //we need to maintain only indexes equal to window size in queue, so check for first index if it becomes outside k
        if (currentWindowQueue.peekFirst() + k == i) {
            currentWindowQueue.removeFirst()
        }

        // Keep updating the largest value index, remove all elements indexes whose values are smaller from current index value
        while (!currentWindowQueue.isEmpty() && arr[currentWindowQueue.peekLast()] <= arr[i]) {
            currentWindowQueue.removeLast() //remove from last only, so that dequeue first element will maintain largest value index for current window
        }

        currentWindowQueue.addLast(i)
        runningList.add(arr[currentWindowQueue.peekFirst()]) //queue
    }

    return runningList.toIntArray()
}

// O(n*log(n)) Using Priority Queue
fun maxSlidingWindow2(nums: IntArray, k: Int): IntArray {
    val priorityQueue = PriorityQueue<Int>(Collections.reverseOrder())

    val runningList = mutableListOf<Int>()

    //O(n)
    for (i in nums.indices) {
        priorityQueue.add(nums[i]) //O(logn) for each insertion upto n

        // In increasing queue, of size k, 1st element is always the kth largest(means smallest) element from those
        if (priorityQueue.size > k) priorityQueue.remove()
        runningList.add(priorityQueue.peek())

    }
    return runningList.toIntArray()
}

fun main() {
    println(maxSlidingWindow(intArrayOf(1, -1), 1).contentToString())
    println(maxSlidingWindow2(intArrayOf(1, -1), 1).contentToString())
}

/**
 * if (currentWindowQueue.size == k) {
 *         currentWindowQueue.removeFirst()
 *  }
 *
 *  This may not work in some scenarios, Becoz at some particualr time we may have some 0 or 1 index from very first window,
 *  and window size never reaches k becoz we are removing smaller elements
 *
 *  Check this example: [1,3,1,2,0,5] and k = 3
 *  after first window loop: Queue: [1,2] indexes
 *  after second loop ist iteration: [1,3] indexes
 *  after second loop 2nd iteration: [1,3], it will choose element at index 1(that is 3) as largest, but we are on index 4(that is 0), so current window is [1,2,0], so 2 should be answer here
 *
 * Thats why we need to check index difference with first element of queue,
 * it should not be more than currentIndex - k
 * NOw consider same scenario with it will check i-k => 4-3 => 1, SO index window should be [2,3,4] if 4 is current index
 * So it will remove 1st index from queue, becoz that is at first index
 *
 * TIME COMPLEXITY: APPROACH 1
 * **
 *  * Complexity Analysis
 *  * Time complexity: O(n).
 *  *
 *  * At first glance, it may look like the time complexity of this algorithm should be O(n*n),
 *  because there is a nested while loop inside the for loop.
 *  However, each element can only be added to the deque once,
 *  which means the deque is limited to n pushes.
 *  Every iteration of the while loop uses 1 pop,
 *  which means the while loop will not iterate more than n times in total,
 *  across all iterations of the for loop.
 *  An easier way to think about this is that in the worst case,
 *  every element will be pushed and popped once. This gives a time complexity of O(2⋅n)=O(n).
 *
 *  * Space complexity: O(k)
 *  * The size of the deque can grow a maximum up to a size of k.
 *  */
