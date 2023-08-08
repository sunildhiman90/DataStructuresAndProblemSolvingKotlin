package datastructures.tree

class BinaryTree {

    class Node(val data: Int, var left: Node? = null, var right: Node? = null)

    private var root: Node? = null

    fun buildTree() {
        println("Enter the root Node: ")
        val data = readln().toInt()
        root = Node(data)
        this.buildTree(root)
    }

    private fun buildTree(node: Node?) {

        println("Do you want to enter the left of ${node?.data}: [true/false] ")
        val left = readln().toBoolean()
        if (left) {
            println("Enter the value of left of ${node?.data}: [true/false] ")
            val data = readln().toInt()
            node?.left = Node(data)
            this.buildTree(node?.left)
        }

        println("Do you want to enter the right of ${node?.data}: [true/false] ")
        val right = readln().toBoolean()
        if (right) {
            println("Enter the value of right of ${node?.data}: [true/false] ")
            val data = readln().toInt()
            node?.right = Node(data)
            this.buildTree(node?.right)
        }

    }


    var index = -1

    //This method will build tree from Preorder traversal NLR ->(NODE LEFT RIGHT)
    fun buildTreeFromArray(nodes: IntArray): Node? {
        index++
        if (nodes[index] == -1) {
            return null
        }

        val node = Node(nodes[index])

        //repeat same for left and right subtree
        node.left = buildTreeFromArray(nodes)
        node.right = buildTreeFromArray(nodes)
        root = node
        return root

    }


    fun preorderTraversal() {
        println("---PreOrder Traversal---")
        preorderTraversal(root)
    }

    // NLR
    private fun preorderTraversal(node: Node?) {
        if (node == null) {
            return
        }
        print("${node.data}\t")
        preorderTraversal(node.left)
        preorderTraversal(node.right)
    }

    fun inorderTraversal() {
        println("---InOrder Traversal---")
        inorderTraversal(root)
    }

    // LNR
    private fun inorderTraversal(node: Node?) {
        if (node == null) {
            return
        }
        inorderTraversal(node.left)
        print("${node.data}\t")
        inorderTraversal(node.right)
    }

    fun postorderTraversal() {
        println("---PostOrder Traversal---")
        postorderTraversal(root)
    }

    // LNR
    private fun postorderTraversal(node: Node?) {
        if (node == null) {
            return
        }
        postorderTraversal(node.left)
        postorderTraversal(node.right)
        print("${node.data}\t")
    }

    fun display() {
        println(root?.data)
        displayChildren(root?.left)
        displayChildren(root?.right)
    }

    private fun displayChildren(node: Node?) {
        if (node == null) {
            return
        }
        print("${node.data}\t")
        displayChildren(node.left)
        displayChildren(node.right)
    }

    fun prettyDisplay() {
        val level = 1
        prettyDisplayChildren(level, root)
    }

    private fun prettyDisplayChildren(level: Int, node: Node?) {
        if (node == null) {
            return
        }

        // traverse till rightmost node
        prettyDisplayChildren(level + 1, node.right)

        // print space according to level and finally node data
        if (level > 1) {
            for (i in 1 until level - 1) {
                print("\t")
            }
            println("\t---->${node.data}")
        } else {
            //print root and no space
            println(node.data)
        }
        prettyDisplayChildren(level + 1, node.left)
    }


}

fun main() {
    // Uncomment this to test this way of building tree with prompting user
    /*val binaryTree = BinaryTree()
    binaryTree.buildTree()
    binaryTree.display()*/

    // build From Preorder Traversal, we will use -1 for null, as it is int array we cant directly add null in array
    //              1
    //         2        3
    //      4        5    6

    // Preorder traversal of above tree
    val arr = intArrayOf(1, 2, 4, -1, -1, -1, 3, 5, -1, -1, 6, -1, -1)
    val binaryTree2 = BinaryTree()
    binaryTree2.buildTreeFromArray(arr)
    binaryTree2.prettyDisplay() //it will display 45 degree left rotated visual of tree
    println()
    binaryTree2.preorderTraversal()
    println()
    binaryTree2.inorderTraversal()
    println()
    binaryTree2.postorderTraversal()

}