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

// Its not correct implementation, but its Just for understanding nullability in kotlin
fun isPalindrome2(start: DoublyLinkedList.Node?): Boolean {
    var head = start
    if (head?.next == null) return true

    // due to this if condition kotlin compiler
    // will assume that head is not null


    //find tail
    var tail = head
    while (tail != null) {
        tail = tail.next
    }

    // due to above while loop condition,
    // Kotlin compiler wil detect that tail will be null, loop will break when it becomes null
    // THen in while loop 2, It know tail is null,
    // so thats why its showing warning there that: Condition 'head != tail' is always true
    // Becoz due to top if condition, compiler detected that head is not null
    // Similarily in if condition inside while loop2, its showing this warning:
    // Condition 'head?.data != tail?.data' is always true, due to same reason, tail is null ,and head is not null

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

