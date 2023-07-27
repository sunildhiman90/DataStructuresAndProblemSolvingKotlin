package problemsolving.doublylinkedlist

import datastructures.linkedlist.DoublyLinkedList

class PalindromeDoublyLinkedList {
}

fun isPalindrome(start: DoublyLinkedList.Node?): Boolean {
    var head = start
    if (head?.next == null) return true

    //find tail
    var tail = head
    while (tail?.next != null) {
        tail = tail.next
    }

    // 2 pointers
    while (head != tail) {
        if (head?.data != tail?.data) return false
        head = head?.next
        tail = tail?.prev
    }
    return true
}

fun main() {
    val linkedList = DoublyLinkedList()
    linkedList.insertAtEnd(1)
    linkedList.insertAtEnd(2)
    linkedList.insertAtEnd(3)
    linkedList.insertAtEnd(2)
    linkedList.insertAtEnd(1)

    println("is Panlindrome = ${isPalindrome(linkedList.getHead())}")
}

