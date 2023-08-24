package problemsolving.leetcode


// https://leetcode.com/problems/subtree-of-another-tree/
class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun areIdentical(root: TreeNode?, subRoot: TreeNode?): Boolean {

    // base condition
    if (root == null && subRoot == null) return true

    if (root?.`val` == subRoot?.`val`) {
        //check for left subtree and right subtree for both trees
        return areIdentical(root?.left, subRoot?.left) && areIdentical(root?.right, subRoot?.right)
    }
    return false
}

//if root is same, check if whole trees are same, return true if yes, else check if any of the left or right subtrees matches with subTree to match with
fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
    if (root == null) return false //It means nothing to check, main tree is empty
    if (subRoot == null) return true //null is subTree of every tree

    if (root.`val` == subRoot.`val`) {
        //if root are same, check for whole tree
        if (areIdentical(root, subRoot)) {
            return true
        }
    }

    // if root are not same, check if any of either left subtree or right subtree is same as subTree to match
    return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot)

}