package dsa.linkedlist

class SinglyLinkedList<T> {
    var head: Node<T>? = Node()

    class Node<T>(val data: T? = null, var next: Node<T>? = null)

    fun insertAtStart(newNode: Node<T>) {
        //assign prev first element(that is head) to newly added node's next pointer
        newNode.next = head

        //update head to point to newly added node to make that first node, actually head is the reference to first node
        head = newNode
    }

    fun insertAfterTargetNode(newNode: Node<T>, targetValue: T) {
        //start from first node
        var targetNode = head

        //Traverse till we reach target node
        while (targetNode?.data != targetValue) {
            targetNode = targetNode?.next
        }

        //assign targetNode's next pointer to newly added node's next pointer, so that new node will now point to next node of target node
        newNode.next = targetNode?.next

        //update targetNode.next to point to newly added node to insert after that node
        targetNode?.next = newNode
    }

    fun insertAtEnd(newNode: Node<T>) {
        //start from first node
        var targetNode = head

        //Traverse till we reach end node which will have null as next pointer
        while (targetNode?.next != null) {
            targetNode = targetNode.next
        }

        //Assign null directly(because last node's next pointer always points to null), so that new node will become the last node
        newNode.next = null

        //update targetNode.next to point to newly added node to insert after that node
        targetNode?.next = newNode
    }

    fun deleteAtFirst() {
        //head?.next = head?.next?.next

        //Alternative
        head = head?.next
    }

    fun deleteAfterTarget(targetValue: T) {
        var targetNode = head
        while (targetNode?.data != targetValue) {
            targetNode = targetNode?.next
        }

        //change target next node to its previous next node
        targetNode?.next = targetNode?.next?.next
    }

    fun deleteAtEnd() {
        var targetNode = head
        //Reach the second last node of the singly linked list
        while (targetNode?.next?.next != null) {
            targetNode = targetNode.next
        }
        //make next of second last node to null to make it last node
        targetNode?.next = null
    }

    fun display() {
        var targetNode = head
        while (targetNode?.next != null) {
            println("${targetNode.data}")
            targetNode = targetNode.next
        }
    }


}



