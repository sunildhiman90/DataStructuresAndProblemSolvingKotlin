package datastructures.tree

import problemsolving.binarytree.heightOfTree
import java.util.*

/**
 * Tips for Most of Tree Problems:
 * 1. Mostly we will use recursion, Solve the problem for left subtree by going to null of last left and then it will go to base condition.
 * 3. Repeat  Step 1 for right subtree as well, by going to null of last right and then it will go to base condition
 * 2. And finally Add base condition if node == null , then either perform some calculations there, or apply your terminating condition
 *
 */

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

    // DFS -> LRN
    private fun postorderTraversal(node: Node?) {
        if (node == null) {
            return
        }
        postorderTraversal(node.left)
        postorderTraversal(node.right)
        print("${node.data}\t")
    }


    ///BFS
    fun levelOrderTraversal(start: Node? = root) {
        println("---Level Order Traversal 3---")
        var level = 0
        val queue: Queue<Node> = LinkedList()
        queue.add(start)

        while (!queue.isEmpty()) {
            print("Level: $level --> \t")

            val n = queue.size
            //Pick all elements at currently in the queue, at level : level, BEcoz everytime we are printing node, we are adding its left and right as, that is actually next level
            for (i in 0 until n) {
                val currentNode = queue.poll()
                print("${currentNode.data}  ")
                if (currentNode.left != null) {
                    queue.add(currentNode.left) //for next level
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right)  //for next level
                }
            }
            println()
            level++
        }
    }

    fun isBalanced(node: Node?): Boolean {
        val balanceFactor = heightOfTree(node?.left) - heightOfTree(node?.right)
        return balanceFactor == -1 || balanceFactor == 0 || balanceFactor == 1
    }

    fun display() {
        displayChildren(root)
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
        val level = 0
        prettyDisplayChildren(level, root)
    }

    // it will display 90 degree left rotated(from root) visual of tree
    private fun prettyDisplayChildren(level: Int, node: Node?) {
        if (node == null) {
            return
        }

        // traverse till rightmost node
        prettyDisplayChildren(level + 1, node.right)

        // print space according to level and finally node data
        if (level > 0) {
            for (i in 0 until level - 1) {
                print("\t\t")
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
    binaryTree2.display()
    println()
    binaryTree2.prettyDisplay() //it will display 90 degree left rotated visual of tree
    println()
    binaryTree2.preorderTraversal()
    println()
    binaryTree2.inorderTraversal()
    println()
    binaryTree2.postorderTraversal()
    println()
    binaryTree2.levelOrderTraversal()

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