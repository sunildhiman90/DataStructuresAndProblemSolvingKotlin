package datastructures.linkedlist

class DetectCycle {
}

//Floyd's Cycle Finding Algo
fun hasCycle(head: LinkedList.Node?): Boolean {
    if (head == null) return false
    var hare = head //fast pointer
    var turtle = head //slow pointer
    while (hare?.next != null && hare.next?.next != null) {
        hare = hare.next?.next
        turtle = turtle?.next
        if (hare === turtle) return true
    }
    return false
}

