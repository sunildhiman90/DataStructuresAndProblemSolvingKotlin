package datastructures.linkedlist


class GenericLinkedList<T : Any> {

    private var head: Node<T>? = null

    class Node<T>(var data: T, var next: Node<T>? = null)

    fun getHead(): Node<T>? {
        return head
    }

    fun setHead(newHead: Node<T>) {
        head = newHead
    }

    fun insertAtStart(data: T) {
        val newNode = Node(data)
        if (head == null) {
            head = newNode
            return
        }
        newNode.next = head
        head = newNode
    }

    fun insertAtEnd(data: T) {
        val newNode = Node(data)
        if (head == null) {
            head = newNode
            return
        }
        var temp = head
        while (temp?.next != null) {
            temp = temp.next
        }
        temp?.next = newNode
    }

    fun insertAfterTarget(data: T, after: T) {
        val newNode = Node(data)
        if (head == null) {
            return
        }
        var temp = head
        //Why we need to apply temp.next != null?
        //1st -> For handling the case if there is single element in list,
        //2nd -> For the case if target is not found in entire list, in both cases it will give error when temp will become null after last element is traversed
        while (temp?.data != after && temp?.next != null) {
            temp = temp.next
        }
        //only insert if target(after) is found
        if (temp?.data == after) {
            newNode.next = temp.next
            temp.next = newNode
        } else {
            println("Target not found")
        }
    }

    fun deleteFromEnd() {
        if (head == null) {
            return
        }
        var temp = head
        //get prev to last element and set its next null
        while (temp?.next?.next != null) {
            temp = temp.next
        }
        temp?.next = null
    }

    fun deleteFromStart() {
        if (head == null) {
            return
        }
        head = head?.next
    }

    fun deleteAtTarget(targetData: T) {
        var prev = head
        var temp = head
        while (temp?.data != targetData && temp?.next != null) {
            prev = temp
            temp = temp.next
        }
        if (temp?.data == targetData) {
            prev?.next = temp.next
        } else {
            println("Target not found")
        }
    }

    fun removeNthFromEnd(head: Node<T>?, n: Int): Node<T>? {
        if (head == null) return null

        //get size
        var size = 0
        var curr: Node<T>? = head
        while (curr != null) {
            curr = curr.next
            size++
        }

        //because in this case,we are deleting (size - n + 1) => 1, 1st node => head, so return head.next here
        if (size == n) {
            this.head = head.next
            return head
        }
        var prev: Node<T>? = head
        var index = 1 //take it as 1, because we already have head in prev
        val indexToSearch = size - n // prev node to n'th node from last, n'th node form last is -> size - n + 1
        while (index < indexToSearch) {
            prev = prev?.next
            index++
        }
        prev?.next = prev?.next?.next
        return head
    }


    fun display() {
        var temp = head
        if (head == null) return
        while (temp != null) {
            println(temp.data)
            temp = temp.next
        }
    }

    //3 pointer approach
    fun reverse() {
        if (head?.next == null) return
        var prevNode: Node<T>? =
            null //set it to null, because we are reversing so next of head will become null, because head wil become last
        var currNode = head
        while (currNode != null) {
            val nextNode = currNode.next
            currNode.next = prevNode

            //update prev and curr
            prevNode = currNode
            currNode = nextNode
        }
        head = prevNode //and prevNode will become start head
    }

    fun reverseRecursively(head: Node<T>?): Node<T>? {
        //empty node || last node or only one node
        if (head?.next == null) return head //it will return newHead as last element when last element will be passed in this call

        //recursive call
        val newHead = reverseRecursively(head.next)

        //work done
        head.next?.next = head //reverse point here,
        head.next = null //finally set next of head null
        return newHead //it will be last element, it will remain same in all calls
    }
}

fun main() {
    val linkedList = GenericLinkedList<Int>()
    linkedList.insertAtStart(6)
    linkedList.insertAtStart(5)
    linkedList.insertAtStart(4)
    linkedList.insertAtStart(3)
    linkedList.insertAtStart(2)
    linkedList.insertAtStart(1)
    linkedList.display()
    println("-----After insertion at end----")
    linkedList.insertAtEnd(5)
    linkedList.display()
    val target = 2
    println("-----After insertion after target $target----")
    linkedList.insertAfterTarget(11, 2)
    linkedList.display()
    println("-----After deletion at target----")
    linkedList.deleteAtTarget(11)
    linkedList.display()
    println("-----After deletion from end----")
    linkedList.deleteFromEnd()
    linkedList.display()
    println("-----After deletion from start----")
    linkedList.deleteFromStart()
    linkedList.display()
    println("-----After reverse ----")
    linkedList.reverseRecursively(linkedList.getHead())?.let { linkedList.setHead(it) }
    linkedList.display()

    val n = 2
    println("-----After deleting nth node from end where n is $n ----")
    linkedList.removeNthFromEnd(linkedList.getHead(), n)?.let { linkedList.setHead(it) }
    linkedList.display()

    // linkedList for String
    val linkedListString = GenericLinkedList<String>()
    linkedListString.insertAtStart("kotlin")
    linkedListString.insertAtStart("in")
    linkedListString.insertAtStart("fun")
    linkedListString.insertAtStart("is")
    linkedListString.insertAtStart("List")
    linkedListString.insertAtStart("Linked")
    linkedListString.display()
    println("-----String Type Linked List----")
    linkedListString.insertAtEnd("programming")
    linkedListString.display()
}




