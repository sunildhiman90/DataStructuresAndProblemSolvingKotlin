package datastructures.linkedlist

class PalindromeLinkedList {
}

fun isPalindrome(head: LinkedList.Node?): Boolean {
    if (head?.next == null) return true
    val middle = findMid(head) //end of 1st half
    var secondHalfStart = reverse(middle?.next) //second list will start from mid+1, so mid.next is used here
    var firstHalfStart = head

    // use secondHalfStart for null checking becoz only it will go until last node but not firstHalfStart
    while (secondHalfStart != null) {
        if (secondHalfStart.data != firstHalfStart?.data) return false
        secondHalfStart = secondHalfStart.next
        firstHalfStart = firstHalfStart.next
    }
    return true
}

//hare turtle approach
fun findMid(head: LinkedList.Node?): LinkedList.Node? {
    var hare = head
    var turtle = head
    while (hare?.next != null && hare.next?.next != null) {
        hare = hare.next?.next
        turtle = turtle?.next
    }
    return turtle
}

fun reverse(head: LinkedList.Node?): LinkedList.Node? {
    var prev: LinkedList.Node? = null
    var curr = head
    while (curr != null) {
        val nextNode = curr.next
        curr.next = prev
        prev = curr
        curr = nextNode
    }
    return prev //prev will become head
}

fun main() {
    val linkedList = LinkedList()
    linkedList.insertAtStart(1)
    linkedList.insertAtStart(2)
    linkedList.insertAtStart(3)
    linkedList.insertAtStart(2)
    linkedList.insertAtStart(1)
    println("Is linked List a palindrome: " + isPalindrome(linkedList.getHead()))
}