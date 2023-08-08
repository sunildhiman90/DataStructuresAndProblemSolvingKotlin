package datastructures.linkedlist


class RemoveCycleFromLinkedList {
}

fun removeCycleNew(node: LinkedList.Node?) {
    // If list is empty or has only one node without loop
    if (node?.next == null) return

    // incrementing them here, it will cover if 2 elements are in the list and no cycle,
    // otherwise it will return loop even if that is not there in next if condition
    var fast: LinkedList.Node? = node.next?.next
    var slow: LinkedList.Node? = node.next

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
            //move both of them single steps, and loop until they both meet again, scientific proof is given at bottom of the file
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
    var fast: LinkedList.Node? = node.next?.next
    var slow: LinkedList.Node? = node.next

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


/**
 *
 * We do not need to count the number of nodes in Loop.
 * After detecting the loop, if we start the slow pointer from the head
 * and move both slow and fast pointers at the same speed until fast don’t meet,
 * they would meet at the beginning of the loop.
 *
 * How does this work?
 * Let slow and fast meet at some point after Floyd’s Cycle finding algorithm.
 * The below diagram shows the situation when the cycle is found.
 *
 * [src/main/kotlin/datastructures/linkedlist/linked_list_loop_start_node_logic_proof_diagram.jpg]
 *
 * Situation when cycle is found
 *
 * We can conclude below from the above diagram
 *
 * Distance traveled by fast pointer = 2 * (Distance traveled
 *                                          by slow pointer)
 *
 * (m + n*x + k) = 2*(m + n*y + k)
 *
 * Note that before meeting the point shown above, fast
 * was moving at twice speed.
 *
 * x -->  Number of complete cyclic rounds made by
 *        fast pointer before they meet first time
 *
 * y -->  Number of complete cyclic rounds made by
 *        slow pointer before they meet first time
 * From the above equation, we can conclude below
 *
 *     m + k = (x-2y)*n
 *
 * Which means m+k is a multiple of n.
 * Thus we can write, m + k = i*n or m = i*n - k.
 * Hence, distance moved by slow pointer: m, is equal to distance moved by fast pointer:
 * i*n - k or (i-1)*n + n - k (cover the loop completely i-1 times and start from n-k).
 * So if we start moving both pointers again at same speed such that one pointer (say slow) begins from head node of linked list and other pointer (say fast) begins from meeting point.
 * When the slow pointer reaches the beginning of the loop (has made m steps),
 * the fast pointer would have made also moved m steps as they are now moving at the same pace.
 * Since m+k is a multiple of n and fast starts from k, they would meet at the beginning.
 * Can they meet before also? No because slow pointer enters the cycle first time after m steps.
 */