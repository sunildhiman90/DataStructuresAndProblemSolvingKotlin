package datastructures.linkedlist

class LinkedList {

    private var head: Node? = null

    class Node(var data: Int, var next: Node? = null)

    fun getHead(): Node? {
        return head
    }

    fun setHead(newHead: Node) {
        head = newHead
    }

    fun insertAtStart(data: Int) {
        val newNode = Node(data)
        if (head == null) {
            head = newNode
            return
        }
        newNode.next = head
        head = newNode
    }

    fun insertAtEnd(data: Int) {
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

    fun insertAfterTarget(data: Int, after: Int) {
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

    fun deleteAtTarget(targetData: Int) {
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

    fun removeNthFromEnd(head: Node?, n: Int): Node? {
        if (head == null) return null

        //get size
        var size = 0
        var curr: Node? = head
        while (curr != null) {
            curr = curr.next
            size++
        }

        //because in this case,we are deleting (size - n + 1) => 1, 1st node => head, so return head.next here
        if (size == n) {
            this.head = head.next
            return this.head
        }
        var prev: Node? = head
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
        var prevNode: Node? =
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

    fun reverseRecursively(head: Node?): Node? {
        //empty node || last node or only one node
        if (head?.next == null) return head //it will return newHead as last element when last element will be passed in this call

        //recursive call
        val newHead = reverseRecursively(head.next)

        //work done
        head.next?.next =
            head //reverse point here, instead of using newHead.next here, use head.next.next, otherwise if we use newHead.next, then newHEad will update in every call, and logic will not work
        head.next = null //finally set next of head null
        return newHead //it will be last element, it will remain same in all calls
    }
}

fun main() {
    val linkedList = LinkedList()
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
}


/**
 * reverseRecursively Explanation for example linked list => 1->2->3->4->null
 * 1st call from calling method -> head is 1 -> it will again call with head as head.next => 2
 * 2nd call -> head is 2 -> it will again call with head as head.next => 3
 * 3rd call -> head is 3 -> it will again call with head as head.next => 4 and this call will return with new head as 4,as next of 4 is null
 *
 * Reverse return calls now -> 3rd call will return in 3rd call remaining code where head was 3 && newHead will be 4
 * 3rd call :-  head is 3 -> it will apply -> head.next.next = head, it means => 3 -> 4 -> 3
 *             and it will apply 2nd part also -> head.next = null => 3 -> null, so new list will become now 4 -> 3 -> null
 *             and finally it will return to 2nd call where head is 2
 *
 * 2nd call :-  head is 2 -> it will apply -> head.next.next = head, it means => 2 -> 3 -> 2
 *             and it will apply 2nd part also -> head.next = null => 2 -> null, so new list will become now 4 -> 3 -> 2 -> null
 *             and finally it will return to 1st call where head is 1 && newHead will be 4
 *
 * 1st call :-  head is 1 -> it will apply -> head.next.next = head, it means => 1 -> 2 -> 1
 *              and it will apply 2nd part also -> head.next = null => 1 -> null, so new list will become now 4 -> 3 -> 2 -> 1 -> null
 *              and finally it will return to calling method with new head as 4 and linked list as => [4 -> 3 -> 2 -> 1 -> null]
 *
 *
 */


/**
 * Need Of Linked List
 *
 * Have you ever wandered through Instagram feeds? Yeah, one image after another it keeps on scrolling, it’s infinite, it’s almost never-ending!
 *
 * But have you ever wondered how it all works? Imagine, if you were the engineer who was asked to develop this feature, how would you have approached this problem?
 *
 * How would have you maintained the huge collection of images?
 *
 * The first thing that would have crossed your mind is maintaining an array of all images, but what if the number of images is ever increasing?
 * Soon you’ll realize that arrays are not suited for this job, they don’t go very well with dynamic size data.
 *
 * We need something better, we need a data structure that can store a collection of objects just like an array,
 * but whose size can be manipulated in real-time. This is where the Linked List comes in.
 */