package problemsolving.binarytree

import datastructures.tree.BinaryTree


// It will calculate depth of node with data : nodeDataToFind
fun depthOfNode(node: BinaryTree.Node?, nodeDataToFind: Int): Int {
    if (node == null) {
        return -1 // just for matching in subtree using this condition: if(depthL >= 0 || depthR >= 0)
    }

    // Check if found at current root node, and return 0, becoz its found( >= 0), -1 means not found
    if (node.data == nodeDataToFind) {
        return 1 //we can return 0 if we assume 0 as depth or height of single node
    }
    // if not found at current root, check if either found in its left subtree or in its right subtree
    // and if found then return depthL + 1 || depthR + 1 according to where its found
    val depthL = depthOfNode(node.left, nodeDataToFind)
    val depthR = depthOfNode(node.right, nodeDataToFind)

    if (depthL >= 0 || depthR >= 0) {
        
        return if (depthL >= 0) {
            //if found in left, increment it for each level as going upwards,
            depthL + 1
        } else {
            //if found in right, increment it for each level as going upwards,
            depthR + 1
        }
    }

    return -1
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
    val dataToFind = 7
    val depth = depthOfNode(node = binaryTree.root, dataToFind)
    println("Depth of node with data $dataToFind : $depth")

}

/**
 * 3 Steps approach: First check at current root, if not check in its left subtree, if not check in its righ subtree
 * 1. First we need to check if the current node is the node to find with matching data, and depth of that node with respect to itself will be 1, return 1
 * 2. If 1st step is not true, Then we will check in left subtree and repeat the same process and return depthL + 1 if found
 * 3. if not found in left, Then we will check in right subtree and repeat the same process and return depthR + 1 if found
 *
 */