package problemsolving.linkedlist


// https://leetcode.com/problems/merge-two-sorted-lists/

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

// Use merge sort approach
fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    var list1 = list1
    var list2 = list2
    var mergedListHead: ListNode? = null
    var mergedListPointer: ListNode? = null

    //empty list
    if (list1 == null && list2 == null) return null

    //list1 single element, and list2 empty
    if (list1?.next == null && list2 == null) return list1


    //list2 single element, and list1 empty
    if (list2?.next == null && list1 == null) return list2

    while (list1 != null && list2 != null) {
        if (list1.`val` < list2.`val`) {
            if (mergedListHead == null) {
                mergedListHead = list1
                mergedListPointer = mergedListHead
            } else {
                mergedListPointer?.next = list1
                mergedListPointer = mergedListPointer?.next
            }
            list1 = list1?.next
        } else {
            if (mergedListHead == null) {
                mergedListHead = list2
                mergedListPointer = mergedListHead
            } else {
                mergedListPointer?.next = list2
                mergedListPointer = mergedListPointer?.next
            }
            list2 = list2?.next
        }

    }

    while (list1 != null) {
        if (mergedListHead == null) {
            mergedListHead = list1
            mergedListPointer = mergedListHead
        } else {
            mergedListPointer?.next = list1
            mergedListPointer = mergedListPointer?.next
        }
        list1 = list1?.next
    }

    while (list2 != null) {
        if (mergedListHead == null) {
            mergedListHead = list2
            mergedListPointer = mergedListHead
        } else {
            mergedListPointer?.next = list2
            mergedListPointer = mergedListPointer?.next
        }
        list2 = list2?.next
    }

    return mergedListHead
}
