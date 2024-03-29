package problemsolving.binarysearchtree

import datastructures.tree.BinarySearchTree

//Print numbers in range between x and y

fun printInRange(root: BinarySearchTree.Node?, x: Int, y: Int) {
    if (root == null) return

    //if in range or equal to any of x and y, check in left and right as well
    //if (root.data >= x && root.data <= y) {
    if (root.data in x..y) {
        printInRange(
            root.left,
            x,
            y
        ) //for all other nodes between root.data till down to x, Suppose  x=1, and y =7 and current root=5, so we need to find in left subtree of root 5 for 4, 3, 2, 1 etc..
        print("${root.data}\t") //for root.data == x || root.data == y
        printInRange(root.right, x, y) //for all other nodes between root.data and till y
    } else if (root.data > y) {
        printInRange(root.left, x, y)
    } else {
        printInRange(root.right, x, y)
    }
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
    binarySearchTree.prettyDisplay() //it will display 90 degree left rotated visual of tree

    val min = 3
    val max = 6
    println("Numbers in the range $min - $max")
    printInRange(root = binarySearchTree.root, min, max)

}