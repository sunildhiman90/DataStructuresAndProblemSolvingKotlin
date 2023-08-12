package problemsolving.binarytree

import datastructures.tree.BinaryTree


/**
 * Two Steps approach:
 * 1. Search the node with given data and return the node if found else null
 * 2. Simply find height for tree with that found node as root: just call  heightOfTree method for that node
 */

fun heightOfNode(root: BinaryTree.Node?, nodeToFindHeight: Int): Int {

    //search node
    val node = searchNode(root, nodeToFindHeight)

    // return height of this subtree starting from node of which we are finding height
    return heightOfTree(node)
}

// O(n) , we are traversing in almost all nodes, But in case of BST, it will be logn
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
    val height = heightOfNode(root = binaryTree.root, nodeToFindHeight)
    println("Height of node with data $nodeToFindHeight : $height")


    // Single node case
//    val arr2 = intArrayOf(1, -1, -1)
//    val binaryTree2 = BinaryTree()
//    binaryTree2.buildTreeFromPreorderArray(arr2)
//    binaryTree2.prettyDisplay()
//
//    println()
//    val nodeToFindHeight2 = 1
//    val height2 = heightOfNode(root = binaryTree2.root, nodeToFindHeight2)
//    println("Height of node with data $nodeToFindHeight2 : $height2")

}