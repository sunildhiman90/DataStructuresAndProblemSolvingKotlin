package problemsolving.dp

class ClimbingStairsWithCost {}


fun minCostClimbingStairs(cost: IntArray): Int {
    val n = cost.size
    val dp = IntArray(n) {
        -1
    }

    // If n = 0, then for going to top floor, he have one option only that is cost of 0 and he will directly go to top
    // If n = 1 , then he can EITHER start from 0 and go to 1, and then from 1 to top OR he directly start from 1 and go to top,
    // this will be minimum becoz cost[1] < cost[0] + cost[1]
    dp[0] = cost[0]
    dp[1] = cost[1]

    for (i in 2 until n) {

        val left = dp[i - 1] + cost[i]

        var right = Int.MAX_VALUE
        if (i > 1) right = dp[i - 2] + cost[i]

        dp[i] = Math.min(left, right)
    }
    return Math.min(dp[n - 1], dp[n - 2])
    // this point is clear now, as if we consider Frog Jumps Coding Studio problem, in that case we were asked to reach lastindex(n-1) of array(that was the top of floor), but not (lastindex+1) means nth index, but
    // here we have costs array to take at each index, and we have to reach top which is not last index of array(n-1), which is (lastindex+1) => (n), so for lastindex case(for n), we need to consider
    // min (n-1, n-2), becoz array will traverse till last index n-1, right? so at that point we will have min cost upto lastindex (n)-1, but not min of lastindex+1 => n,
    // thats why, we  need to take min of last 2 indexes,
    // But in case of frog jump coding studio problem , we needed to calculate for n-1, thats why we took n-1 from array itslef, but here we need to calculate for lastindex+1 => n,
    // thats why we need to apply that min case in the end.

}


fun main() {
    //val cost = intArrayOf(1, 100, 1, 1, 1, 100, 1, 1, 100, 1)
    val cost = intArrayOf(10, 15, 20)
    print(minCostClimbingStairs(cost))
}