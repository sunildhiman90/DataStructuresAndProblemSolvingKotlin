package problemsolving.linkedlist


// https://leetcode.com/problems/intersection-of-two-linked-lists/
fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {

    var headAPointer = headA //just for passing in detectCycle, becoz headA will get updated in while loop

    var headA = headA
    while (headA?.next != null) {
        headA = headA.next
    }

    //connect tail of list A with head of list B to create the loop and detect cycle first node that will be intersection point
    headA?.next = headB

    val intersectionPoint = detectCycle(headAPointer)

    //revert headA.next to null
    headA?.next = null

    return intersectionPoint

}


fun detectCycle(head: ListNode?): ListNode? {

    if (head == null) return head

    var slow = head?.next
    var fast = head?.next?.next

    while (fast?.next?.next != null) {
        if (fast == slow) break
        fast = fast?.next?.next
        slow = slow?.next
    }

    if (fast == slow) {
        slow = head
        if (slow != fast) {
            while (slow != fast) {
                slow = slow?.next
                fast = fast?.next
            }
            return fast
        } else {
            return fast
        }
    }

    return null

}

/**
 *
 * Idea is to traverse till tail of list A and then create loop by setting tail.next of list A to head of list B
 * ANd this will create loop, so finally detect loop start node and then reset tail of list A to null
 */