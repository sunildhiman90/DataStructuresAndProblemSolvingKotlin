package problemsolving.binarytree

import datastructures.tree.BinaryTree


// This is based on if height of single root node is considered 1, if we are counting no of nodes in height, but not edges
fun heightOfTree(node: BinaryTree.Node?): Int {
    if (node == null) {
        return 0
    }

    val leftSubtreeHeight = heightOfTree(node.left)
    val rightSubtreeHeight = heightOfTree(node.right)

    // we are adding one for current node, becoz each subtree has 3 nodes:  root, left & right and at each level change , height increases by 1
    return Math.max(leftSubtreeHeight, rightSubtreeHeight) + 1
    //return leftSubtreeHeight.coerceAtLeast(rightSubtreeHeight) + 1 //alternative
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
    val height = heightOfTree(node = binaryTree.root)
    println("Total height of tree : $height")

}