package datastructures.tree

import java.util.*

class BinaryTree {

    class Node(val data: Int, var left: Node? = null, var right: Node? = null)

    var root: Node? = null
        private set

    fun buildTree() {
        println("Enter the root Node: ")
        val data = readln().toInt()
        root = Node(data)
        this.buildTree(root)
    }

    private fun buildTree(node: Node?) {

        println("Do you want to enter the left of ${node?.data}: [true/false] ")
        val left = readln().toBoolean()
        if (left) {
            println("Enter the value of left of ${node?.data}: [true/false] ")
            val data = readln().toInt()
            node?.left = Node(data)
            this.buildTree(node?.left)
        }

        println("Do you want to enter the right of ${node?.data}: [true/false] ")
        val right = readln().toBoolean()
        if (right) {
            println("Enter the value of right of ${node?.data}: [true/false] ")
            val data = readln().toInt()
            node?.right = Node(data)
            this.buildTree(node?.right)
        }

    }

    var index = -1

    //This method will build tree from Preorder traversal NLR ->(NODE LEFT RIGHT)
    fun buildTreeFromPreorderArray(nodes: IntArray): Node? {
        index++
        if (nodes[index] == -1) {
            return null
        }

        val node = Node(nodes[index])

        //repeat same for left and right subtree
        node.left = buildTreeFromPreorderArray(nodes)
        node.right = buildTreeFromPreorderArray(nodes)
        root = node
        return root

    }

    fun preorderTraversal() {
        println("---PreOrder Traversal---")
        preorderTraversal(root)
    }

    // DFS -> NLR
    private fun preorderTraversal(node: Node?) {
        if (node == null) {
            return
        }
        print("${node.data}\t")
        preorderTraversal(node.left)
        preorderTraversal(node.right)
    }

    fun inorderTraversal() {
        println("---InOrder Traversal---")
        inorderTraversal(root)
    }

    // DFS -> LNR
    private fun inorderTraversal(node: Node?) {
        if (node == null) {
            return
        }
        inorderTraversal(node.left)
        print("${node.data}\t")
        inorderTraversal(node.right)
    }

    fun postorderTraversal() {
        println("---PostOrder Traversal---")
        postorderTraversal(root)
    }

    // DFS -> LNR
    private fun postorderTraversal(node: Node?) {
        if (node == null) {
            return
        }
        postorderTraversal(node.left)
        postorderTraversal(node.right)
        print("${node.data}\t")
    }


    // BFS
    fun levelOrderTraversal(start: Node? = root) {
        println("---Level Order Traversal---")
        if (root == null) return
        val queue: Queue<Node?> = LinkedList()

        // Mark visited and add to queue for printing and then checking its left and right child
        queue.add(start)
        queue.add(null) // for next level

        while (!queue.isEmpty()) {

            //poll and print
            val currentNode = queue.poll()
            //If case is just for printing new line for next level
            if (currentNode == null) {
                println()
                // just print new line and add null for next level if queue is not empty
                if (queue.isEmpty()) {
                    break
                } else queue.add(null) // for next level
            } else {
                print("${currentNode.data}\t")
                //check for its neighbours
                if (currentNode.left != null) {
                    queue.add(currentNode.left)
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right)
                }
            }
        }

    }

    fun countNodes(node: Node? = root): Int {
        if (node == null) {
            return 0
        }

        val leftSubtreeCount = countNodes(node.left)
        val rightSubtreeCount = countNodes(node.right)

        // we are adding one for current node, becoz each subtree has 3 nodes:  root, left & right
        return leftSubtreeCount + rightSubtreeCount + 1
    }

    fun sumOfNodes(node: Node? = root): Int {
        if (node == null) {
            return 0
        }

        val leftSubtreeSum = sumOfNodes(node.left)
        val rightSubtreeSum = sumOfNodes(node.right)

        // Add data of current node as well, becoz each subtree has 3 nodes:  root, left & right
        return leftSubtreeSum + rightSubtreeSum + node.data
    }

    fun display() {
        println(root?.data)
        displayChildren(root?.left)
        displayChildren(root?.right)
    }

    private fun displayChildren(node: Node?) {
        if (node == null) {
            return
        }
        print("${node.data}\t")
        displayChildren(node.left)
        displayChildren(node.right)
    }

    fun prettyDisplay() {
        val level = 1
        prettyDisplayChildren(level, root)
    }

    // it will display 45 degree left rotated(from root) visual of tree
    private fun prettyDisplayChildren(level: Int, node: Node?) {
        if (node == null) {
            return
        }

        // traverse till rightmost node
        prettyDisplayChildren(level + 1, node.right)

        // print space according to level and finally node data
        if (level > 1) {
            for (i in 1 until level - 1) {
                print("\t")
            }
            println("\t---->${node.data}")
        } else {
            //print root and no space
            println(node.data)
        }

        //repeat same for left
        prettyDisplayChildren(level + 1, node.left)
    }

}

fun main() {
    // Uncomment this to test this way of building tree with prompting user
    /*val binaryTree = BinaryTree()
    binaryTree.buildTree()
    binaryTree.display()*/

    // build From Preorder Traversal, we will use -1 for null, as it is int array we cant directly add null in array
    //              1
    //         2        3
    //      4        5    6

    // Preorder traversal of above tree
    val arr = intArrayOf(1, 2, 4, -1, -1, -1, 3, 5, -1, -1, 6, -1, -1)
    val binaryTree2 = BinaryTree()
    binaryTree2.buildTreeFromPreorderArray(arr)
    binaryTree2.prettyDisplay() //it will display 45 degree left rotated visual of tree
    println()
    binaryTree2.preorderTraversal()
    println()
    binaryTree2.inorderTraversal()
    println()
    binaryTree2.postorderTraversal()
    println()
    binaryTree2.levelOrderTraversal()
    println()
    val count = binaryTree2.countNodes()
    println("Total no of nodes in tree : $count")
    println()
    val sum = binaryTree2.sumOfNodes()
    println("Total sum of data of all nodes in tree : $sum")

    // inorder traversal
    val arr2 = intArrayOf(-1, 4, -1, 2, -1, 1, -1, 5, -1, 3, -1, 6, -1)

    // Random array
    val arr3 = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)


}

/**
 * DFS -> Depth First Traversals include PreOrder, InOrder and PostOrder Traversals
 * BFS -> Level Order Traversal
 *
 */