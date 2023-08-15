package problemsolving.binarytree

import datastructures.tree.BinaryTree
import java.util.*

// Sum of nodes at Kth Level
fun sumOfNodesAtKthLevel(node: BinaryTree.Node?, k: Int): Int {
    var level = 0
    val queue: Queue<BinaryTree.Node> = LinkedList()
    queue.add(node)

    while (!queue.isEmpty()) {
        val n = queue.size
        var sumByLevel = 0
        for (i in 0 until n) {
            val currentNode = queue.poll()
            sumByLevel += currentNode.data
            if (currentNode.left != null) {
                queue.add(currentNode.left)
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right)
            }
        }
        if (k == level) return sumByLevel
        level++
    }
    return 0
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
    val levelToFindSum = 5
    val sum = sumOfNodesAtKthLevel(node = binaryTree.root, k = levelToFindSum)
    println("Sum of nodes at level ${levelToFindSum}: $sum")
}