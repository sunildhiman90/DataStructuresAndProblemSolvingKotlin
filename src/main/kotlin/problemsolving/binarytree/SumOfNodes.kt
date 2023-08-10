package problemsolving.binarytree

import datastructures.tree.BinaryTree

// Sum of data of nodes in binary tree.
// Time Complexity: O(n)
fun sumOfNodes(node: BinaryTree.Node?): Int {
    if (node == null) {
        return 0
    }

    val leftSubtreeSum = sumOfNodes(node.left)
    val rightSubtreeSum = sumOfNodes(node.right)

    // Add data of current node as well, becoz each subtree has 3 nodes:  root, left & right
    return leftSubtreeSum + rightSubtreeSum + node.data
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
    val sum = sumOfNodes(node = binaryTree.root)
    println("Total sum of nodes in tree : $sum")

}