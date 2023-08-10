package problemsolving.binarytree

import datastructures.tree.BinaryTree


// Count no of nodes in binary tree.
// Time Complexity: O(n)
fun countNodes(node: BinaryTree.Node?): Int {
    if (node == null) {
        return 0
    }

    val leftSubtreeCount = countNodes(node.left)
    val rightSubtreeCount = countNodes(node.right)

    // we are adding one for current node, becoz each subtree has 3 nodes:  root, left & right
    return leftSubtreeCount + rightSubtreeCount + 1
}

fun main() {

    // We will build From Preorder Traversal, we will use -1 for null, as it is int array we cant directly add null in array
    //              1
    //         2        3
    //      4        5    6

    // Preorder traversal of above tree
    val arr = intArrayOf(1, 2, 4, -1, -1, -1, 3, 5, -1, -1, 6, -1, -1)
    val binaryTree = BinaryTree()
    binaryTree.buildTreeFromPreorderArray(arr)
    binaryTree.prettyDisplay()

    println()
    val count = countNodes(node = binaryTree.root)
    println("Total no of nodes in tree : $count")

}