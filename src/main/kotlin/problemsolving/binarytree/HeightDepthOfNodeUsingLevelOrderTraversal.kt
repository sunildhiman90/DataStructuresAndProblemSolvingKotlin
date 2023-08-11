package problemsolving.binarytree

import datastructures.tree.BinaryTree
import java.util.*

class HeightDepthOfNodeUsingLevelOrderTraversal

fun findHeightDepth(node: BinaryTree.Node?, nodeToFindData: Int) {
    var level = 0
    var depth = -1
    val queue: Queue<BinaryTree.Node> = LinkedList()
    queue.add(node)

    while (!queue.isEmpty()) {
        val n = queue.size
        for (i in 0 until n) {
            val currentNode = queue.poll()
            if (currentNode.data == nodeToFindData) {
                depth =
                    level + 1 // we can remove adding of 1 if depth of root is considered as 0, otherwise we can keep adding of 1 if depth of root is considered as 1
            }
            if (currentNode.left != null) {
                queue.add(currentNode.left)
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right)
            }
        }
        level++
    }
    val height =
        level - depth // need to subtract 1 if depth of root is considered as 0, otherwise no need to subtract 1 if depth of root is considered as 1

    println("depth of $nodeToFindData is: ${depth}")
    println("height of $nodeToFindData is: $height")
}

fun main() {
    // We will build From Preorder Traversal, we will use -1 for null, as it is int array we cant directly add null in array
    //              1
    //         2        3
    //      4        5    6
    //             7
    // Preorder traversal of above tree
    val arr = intArrayOf(1, 2, 4, -1, -1, -1, 3, 5, 7, -1, -1, -1, 6, -1, -1)
    val binaryTree = BinaryTree()
    binaryTree.buildTreeFromPreorderArray(arr)
    binaryTree.prettyDisplay()

    println()
    val nodeToFindHeight = 7
    findHeightDepth(node = binaryTree.root, nodeToFindHeight)
}