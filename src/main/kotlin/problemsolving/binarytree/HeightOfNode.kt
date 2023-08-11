package problemsolving.binarytree

import datastructures.tree.BinaryTree


/**
 * Two Steps approach:
 * 1. Search the node with given data and return the node if found else null
 * 2. Simply find height for tree with that found node as root: just call  heightOfTree method for that node
 */

fun heightOfNode(node: BinaryTree.Node?): Int {
    if (node == null) {
        return 0
    }

    // else return height of this subtree starting from node of which we are finding height
    return heightOfTree(node)
}

fun searchNode(root: BinaryTree.Node?, nodeDataToFind: Int): BinaryTree.Node? {

    if (root == null) return null

    if (root.data == nodeDataToFind) return root

    return searchNode(root.left, nodeDataToFind) ?: searchNode(root.right, nodeDataToFind)
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
    val nodeToFindHeight = 5
    val height = heightOfNode(node = searchNode(binaryTree.root, nodeToFindHeight))
    println("Height of node with data $nodeToFindHeight : $height")

}