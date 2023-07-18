package dsa.linkedlist

class RemoveNthNodeFromEnd

//Remove (size - n + 1)th node from start, it will be nth from end
fun removeNthFromEnd(head: LinkedList.Node?, n: Int): LinkedList.Node? {
    if (head?.next == null) return null

    //get size
    var size = 0
    var curr: LinkedList.Node? = head
    while (curr != null) {
        curr = curr.next //this can be null so don't use curr.next?.let { curr = it }, otherwise while condition will not be true, because it will not be null ever
        size++
    }

    //because in this case,we are deleting (size - n + 1) => 1, 1st node => head, so return head.next here
    if (size == n) return head.next
    var prev: LinkedList.Node? = head
    var index = 1 //take it as 1, because we already have head in prev
    val indexToSearch = size - n // prev node to n'th node from last, n'th node form last is -> size - n + 1
    while (index < indexToSearch) {
        prev = prev?.next
        index++
    }
    prev?.next = prev?.next?.next
    return head
}
fun main() {
    val linkedList = LinkedList()
    linkedList.insertAtStart(5)
    linkedList.insertAtStart(4)
    linkedList.insertAtStart(3)
    linkedList.insertAtStart(2)
    linkedList.insertAtStart(1)

    println("Before deletion of nth node from end")
    println(linkedList.getHead()?.data)
    linkedList.display()

    val n = 2
    removeNthFromEnd(linkedList.getHead(), n)
    println("AFer deletion of nth node from end, n is $n")
    linkedList.display()
}