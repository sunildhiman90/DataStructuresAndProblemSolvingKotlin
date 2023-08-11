package problemsolving.binarytree

import datastructures.tree.BinaryTree


// It will calculate depth of tree it means depth of deepest node with data
// So it means it will be equal to height of tree, Use height of tree method

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
    val depth = heightOfTree(node = binaryTree.root)
    println("Depth of tree : $depth")

}