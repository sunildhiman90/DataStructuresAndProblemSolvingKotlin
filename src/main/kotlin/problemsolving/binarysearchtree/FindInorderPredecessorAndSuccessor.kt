package problemsolving.binarysearchtree

import datastructures.tree.BinarySearchTree

class FindInorderPredecessorAndSuccessor {
}


fun findInorderPredecessorSuccessor(root: BinarySearchTree.Node?, key: Int) {

    helper(root, key)

}

var pre: BinarySearchTree.Node? = null
var suc: BinarySearchTree.Node? = null
fun helper(root: BinarySearchTree.Node?, key: Int) {

    if (root == null) return

    if (key == root.data) {
        pre = findInorderPredecessor(root.left)
        suc = findInorderSuccessor(root.right)
        return //no need to check further
    }

    if (key < root.data) {
        //set suc as this node, in case if its not found in left subtree
        suc = root
        //also find in left subtree
        helper(root.left, key)
    } else {
        //set pre as this node, in case if its not found in right subtree
        pre = root
        //also find in right subtree
        helper(root.right, key)
    }


}

// Rightmost node in left subtree
fun findInorderPredecessor(root: BinarySearchTree.Node?): BinarySearchTree.Node? {
    if (root == null) return null
    var node = root
    while (node?.right != null) {
        node = node.right
    }
    return node
}

// leftmost node in right subtree
fun findInorderSuccessor(root: BinarySearchTree.Node?): BinarySearchTree.Node? {
    if (root == null) return null
    var node = root
    while (node?.left != null) {
        node = node.left
    }
    return node
}


fun main() {

    // build BST From below given array
    //              4
    //         2        6
    //      1    3    5    7
    //                        9
    val arr = intArrayOf(4, 2, 1, 6, 3, 5, 7, 9) //first element will become root
    val binarySearchTree = BinarySearchTree()
    binarySearchTree.populate(arr)
    binarySearchTree.prettyDisplay()

    val key = 8
    findInorderPredecessorSuccessor(root = binarySearchTree.root, key)
    println("Inorder Predecessor of $key is : ${pre?.data ?: "Not Found"}")
    println("Inorder Successor of $key is : ${suc?.data ?: "Not Found"}")

}