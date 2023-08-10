package problemsolving.binarytree

import datastructures.tree.BinaryTree

// Binary Tree Problem 4 with Efficient Approach: O(n)
// calculate and save height along with diameter at each node and reuse that height for node parents,
// But in Prev approach we were not reusing the height for each subtree, every time we were calculating it again,
// thats why that was O(n*n)
fun diameterOfTree2(node: BinaryTree.Node?): NodeInfo {
    if (node == null) {
        return NodeInfo(0, 0)
    }

    val nodeLeftSubtreeInfo = diameterOfTree2(node.left)
    val nodeRightSubtreeInfo = diameterOfTree2(node.right)

    // calculations
    val treeHeight = Math.max(nodeLeftSubtreeInfo.height, nodeRightSubtreeInfo.height) + 1

    val nodeLeftSubtreeDiam = nodeLeftSubtreeInfo.diameter
    val nodeRightSubtreeDiam = nodeRightSubtreeInfo.diameter
    val nodeMainTreeDiam =
        nodeLeftSubtreeInfo.height + nodeRightSubtreeInfo.height // dont add +1 for current node, if we want to calculate path (no of edges) Becoz edges = nodes-1
    //val nodeMainTreeDiam = nodeLeftSubtreeInfo.height + nodeRightSubtreeInfo.height + 1 //add 1 if we need to count no of nodes

    val treeDiam = Math.max(nodeMainTreeDiam, Math.max(nodeLeftSubtreeDiam, nodeRightSubtreeDiam))
    return NodeInfo(treeHeight, treeDiam)
}

class NodeInfo(val height: Int, val diameter: Int)

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
    val diam = diameterOfTree2(node = binaryTree.root)
    println("Diameter of tree : ${diam.diameter}")
}


/**
 * Logic Explained:-
 * 4 Steps approach which will apply to each subtree as well:-
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

/**
 *
 * If we need path size(no of edges), it will be one less than this:
 * IN that case, We can calculate diameter as (leftSubtree.height + rightSubtree.height) without adding 1
 * Else we can calculate as leftSubtree.height + rightSubtree.height + 1 if we want to count no of nodes in longest path
 * BEcoz we know: Number of edges = Number of nodes - 1
 *
 */