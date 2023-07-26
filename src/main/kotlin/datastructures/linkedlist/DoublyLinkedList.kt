package datastructures.linkedlist

class DoublyLinkedList {

    private var head: Node? = null

    class Node(var data: Int) {
        var next: Node? = null
        var prev: Node? = null
    }

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
        head?.prev = newNode
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
        newNode.prev = temp
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
            val tempNext = temp.next
            //set next nodes
            newNode.next = tempNext
            temp.next = newNode

            //set prev nodes
            tempNext?.prev = newNode
            newNode.prev = temp
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
        val lastNode = temp?.next
        temp?.next = null

        //you can also set prev of last node to null to detach last node completely from list, but its optional
        lastNode?.prev = null
    }

    fun deleteFromStart() {
        if (head == null) {
            return
        }
        head = head?.next
        head?.prev = null
    }

    fun deleteAtTarget(targetData: Int) {
        var prev = head
        var temp = head
        while (temp?.data != targetData && temp?.next != null) {
            prev = temp
            temp = temp.next
        }
        val tempNext = temp?.next
        if (temp?.data == targetData) {
            prev?.next = tempNext
            tempNext?.prev = prev
        } else {
            println("Target not found")
        }
    }

    fun getLastNode(): Node? {
        if (head == null) {
            return null
        }
        var temp = head
        while (temp?.next != null) {
            temp = temp.next
        }
        return temp
    }

    // traverseForward from start
    fun traverseForward() {
        var temp = head
        if (head == null) return
        while (temp != null) {
            println(temp.data)
            temp = temp.next
        }
    }

    // traverseBackward from given node
    fun traverseBackward(node: Node?) {
        if (node == null) return
        var temp: Node? = node
        while (temp != null) {
            println(temp.data)
            temp = temp.prev
        }
    }

}

fun main() {
    val linkedList = DoublyLinkedList()
    linkedList.insertAtStart(6)
    linkedList.insertAtStart(5)
    linkedList.insertAtStart(4)
    linkedList.insertAtStart(3)
    linkedList.insertAtStart(2)
    linkedList.insertAtStart(1)
    linkedList.traverseForward()
    println("-----Traverse forward After insertion at end----")
    linkedList.insertAtEnd(5)
    linkedList.traverseForward()
    val target = 2
    println("-----Traverse forward After insertion after target $target----")
    linkedList.insertAfterTarget(11, 2)
    linkedList.traverseForward()
    println("-----Traverse forward After deletion at target $target----")
    linkedList.deleteAtTarget(11)
    linkedList.traverseForward()
    println("-----Traverse forward After deletion from end----")
    linkedList.deleteFromEnd()
    linkedList.traverseForward()
    println("-----Traverse forward After deletion from start----")
    linkedList.deleteFromStart()
    linkedList.traverseForward()


    val givenNode = linkedList.getLastNode()
    println("-----Traverse backward from given node $givenNode----")
    linkedList.traverseBackward(givenNode)

}
