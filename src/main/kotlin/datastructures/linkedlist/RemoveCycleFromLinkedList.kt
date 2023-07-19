package datastructures.linkedlist

class RemoveCycleFromLinkedList {
}

fun removeCycleNew(node: LinkedList.Node?) {
    // If list is empty or has only one node without loop
    if (node?.next == null) return

    // incrementing them here, it will cover if 2 elements are in the list and no cycle, otherwise it will return loop even if that is not there
    var slow: LinkedList.Node? = node?.next?.next
    var fast: LinkedList.Node? = node?.next

    // Search for loop using slow and fast pointers
    while (fast?.next?.next != null) {
        //and also need to check this condition before increasing pointers, because first time we already increased them before loop
        if (slow == fast) break
        slow = slow?.next
        fast = fast.next?.next
    }

    /* If loop exists */
    if (slow === fast) {
        slow = node //start slow from start
        if (slow !== fast) {
            //move both of them single steps, and loop until they both meet again,
            //IMP: we are cheking here for next of slow and fast, so that we will have fast or slow as prev node to loop starting node
            while (slow?.next !== fast?.next) {
                slow = slow?.next
                fast = fast?.next
            }
            /* becoz fast.next is same as slow,next now, so we can say fast.next or slow.next is the looping point, */
            fast?.next = null /* remove loop */
        } else {
            // This case is added if fast and slow pointer already meet at first position.
            // then we need to traverse till last node, to set next of last as null to break the loop,
            // becoz last is pointing to first it means to make the loop
            while (fast?.next !== slow) {
                fast = fast?.next
            }
            fast.next = null
        }
    }
}

fun detectCycleStartNode(node: LinkedList.Node?): LinkedList.Node? {
    // If list is empty or has only one node
    // without loop
    if (node?.next == null) return null

    // incrementing them here, it will cover if 2 elements are in the list and no cycle, otherwise it will return loop even if that is not there
    var slow: LinkedList.Node? = node?.next?.next
    var fast: LinkedList.Node? = node?.next

    // Search for loop using slow and fast pointers
    while (fast?.next?.next != null) {
        if (slow == fast) break
        slow = slow?.next
        fast = fast.next?.next
    }

    /* If loop exists */
    if (slow === fast) {
        slow = node //start slow from start
        return if (slow !== fast) {
            //move both of them single steps, and loop until they both meet again,
            //IMP: we are cheking here for next of slow and fast, so that we will have fast or slow as prev node to loop starting node
            while (slow?.next !== fast?.next) {
                slow = slow?.next
                fast = fast?.next
            }
            /* becoz fast.next is same as slow,next now, so we can say fast.next or slow.next is the looping point, */
            fast?.next
        } else {
            // it means they already met at first node by just resetting slow pointer to start, so first node is starting the loop
            fast
        }
    }
    return null
}


//ALternatives,
fun detectCycleFirstNode(head: LinkedList.Node?): LinkedList.Node? {
    if (head?.next == null) return null
    var hare = head //fast pointer
    var turtle = head //slowPointer
    var doubleMove = true //this will track if we need to move hare double step
    while (hare?.next != null && hare.next?.next != null) {
        if (doubleMove) {
            hare = hare.next?.next
            turtle = turtle?.next
            if (hare === turtle) {
                //if both meet at head already, return that, else reset
                if (hare === head) {
                    return hare
                }
                //reset turtle to head and stop doubleMove of hare
                doubleMove = false
                turtle = head
            }
        } else {
            ///move them single steps
            hare = hare.next
            turtle = turtle?.next
            if (hare === turtle) return hare //second meeting point with equal movement
        }
    }
    return null
}

fun removeCycle(head: LinkedList.Node?): LinkedList.Node? {
    if (head?.next == null) return null
    var hare = head //fast pointer
    var turtle = head //slowPointer
    var prevHare = head
    var doubleMove = true //this will track if we need to move hare double step
    while (hare?.next != null && hare.next?.next != null) {
        if (doubleMove) {
            prevHare = hare.next //save prev hare in prevHare
            hare = hare.next?.next
            turtle = turtle?.next
            if (hare === turtle) {
                //if both meet at head already, return that, else reset
                if (hare === head) {
                    prevHare?.next = null //break cycle, because cycle will start from prev hare
                    return hare
                }
                //reset turtle to head and stop doubleMove of hare
                doubleMove = false
                turtle = head
            }
        } else {
            ///move them single steps
            prevHare = hare //save prev hare in prevHare
            hare = hare.next
            turtle = turtle?.next
            if (hare === turtle) {
                prevHare.next = null //break cycle, because cycle will start from prev hare
                return hare //second meeting point with equal movement
            }
        }
    }
    return null
}

