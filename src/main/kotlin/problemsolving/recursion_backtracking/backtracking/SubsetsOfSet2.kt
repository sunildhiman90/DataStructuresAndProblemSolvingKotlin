package problemsolving.recursion_backtracking.backtracking

//https://leetcode.com/problems/subsets-ii/
fun subsetsWithDup(nums: IntArray): List<List<Int>> {
    val output = mutableListOf<List<Int>>()
    val currentList = mutableListOf<Int>()
    nums.sort()
    helper2(nums, currentList, output, 0)
    return output.toList()
}

fun helper2(nums: IntArray, currentList: MutableList<Int>, output: MutableList<List<Int>>, curr: Int) {
    output.add(currentList.toList())
    for (i in curr until nums.size) {

        //skip duplicate
        if (i > curr && nums[i] == nums[i - 1]) continue

        currentList.add(nums[i])
        helper(nums, currentList, output, i + 1)
        currentList.removeAt(currentList.size - 1)
    }
}