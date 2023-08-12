package problemsolving.binarytree

import datastructures.tree.BinaryTree

// Diameter of tree is the total no of nodes between longest path between any 2 nodes
// Time Complexity : O(n*n)
fun diameterOfTree(node: BinaryTree.Node?): Int {
    if (node == null) {
        return 0
    }

    val leftSubtreeDiameter = diameterOfTree(node.left)
    val rightSubtreeDiameter = diameterOfTree(node.right)
    val rootTreeDiameter =
        heightOfTree(node.left) + heightOfTree(node.right) // dont add +1 for current node, if we want to calculate path (no of edges) Becoz edges = nodes-1
    //val rootDiameter = heightOfTree(node.left) + heightOfTree(node.right) + 1 //add 1 if we need to count no of nodes

    return Math.max(rootTreeDiameter, Math.max(leftSubtreeDiameter, rightSubtreeDiameter))
    //return rootDiameter.coerceAtLeast(leftSubtreeDiameter.coerceAtLeast(rightSubtreeDiameter))

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
    val diam = diameterOfTree(node = binaryTree.root)
    println("Diameter of tree : $diam")

}


/**
 * Logic Explained:-
 * 4 Steps approach which will apply to each subtree:-
 * 1. Calculate diameter of left subtree:- (leftSubtree.height +  rightSubtree.height)
 * 2. Calculate diameter of right subtree
 * 3. Calculate diameter of complete tree
 * 4. Return max out of all 3 diameters
 *
 * How to calculate height of root tree: Take maximum out of (leftSubtree.height and rightSubtree.height) + 1
 * How to calculate diameter of root tree: leftSubtree.height +  rightSubtree.height
 * How to calculate diameter of tree:  Take maximum out of (rootTree.diameter , leftSubtree.diameter,  rightSubtree.diameter)
 *
 * NOTE: If we calculate the height of left subtree and right subtree and add them and also add 1 to that, it will return num of nodes of longest path
 *
 */