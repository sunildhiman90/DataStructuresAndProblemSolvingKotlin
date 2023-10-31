package problemsolving.dp.subsequenceOrSubsets

//https://leetcode.com/problems/coin-change-ii/description/


// Space optimized Tabulation
fun change(amount: Int, coins: IntArray): Int {
    val n = coins.size
    var prev = IntArray(amount + 1) {
        0
    }

    for (T in 0..amount) {
        prev[T] = if (T % coins[0] == 0) 1 else 0
    }

    for (ind in 1 until n) {
        val curr = IntArray(amount + 1) {
            0
        }
        for (T in 0..amount) {
            val notTake = prev[T]
            var take = 0
            if (coins[ind] <= T)
                take = curr[T - coins[ind]] //take curr here(same ind), becoz single element can be repeated

            curr[T] = take + notTake
        }

        prev = curr

    }

    return prev[amount]

}

fun main() {

    val arr = intArrayOf(1, 2, 5)
    val amount = 5

    println(change(amount = amount, arr))
}