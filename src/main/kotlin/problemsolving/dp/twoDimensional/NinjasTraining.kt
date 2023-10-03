package problemsolving.dp.twoDimensional

//https://www.codingninjas.com/studio/problems/ninja%E2%80%99s-training_3621003?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf


//Using Recursion
fun maxMerit(day: Int, points: Array<IntArray>): Int {
    //pass last as 3, out of range(0..2), means we can perform all tasks for this last day as we are stating from last day
    return maxMeritHelper(day - 1, last = 3, points)
}

fun maxMeritHelper(day: Int, last: Int, points: Array<IntArray>): Int {

    //base case
    if (day == 0) {
        var max = 0
        for (i in 0 until 3) {
            if (i != last) {
                //except i, get max from other tasks
                max = Math.max(points[0][i], max)
            }
        }
        return max
    }


    var max = 0
    for (i in 0 until 3) {
        if (i != last) {
            //current day task + same stuff for all other days from day-1 to 0
            val point = points[day][i] + maxMeritHelper(day - 1, i, points)
            max = Math.max(point, max)
        }
    }

    return max

}

// Using Memo
fun maxMeritMemo(day: Int, points: Array<IntArray>): Int {

    //dp array of [n][4], becoz we need to take into count 3 as well other than 0,1,2
    val dp = Array(day) {
        IntArray(4) {
            -1
        }
    }
    //pass last as 3, out of range(0..2), means we can perform all tasks for this last day as we are stating from last day
    return maxMeritHelperMemo(day - 1, last = 3, points, dp)
}

fun maxMeritHelperMemo(day: Int, last: Int, points: Array<IntArray>, dp: Array<IntArray>): Int {

    //base case
    if (day == 0) {
        var max = 0
        for (i in 0 until 3) {
            if (i != last) {
                //except i, get max from other tasks
                max = Math.max(points[0][i], max)
            }
        }
        return max
    }


    if (dp[day][last] != -1) return dp[day][last]

    var max = 0
    for (i in 0 until 3) {
        if (i != last) {
            //current day task + same stuff for all other days from day-1 to 0
            val point = points[day][i] + maxMeritHelperMemo(day - 1, i, points, dp)
            max = Math.max(point, max)
        }
    }

    dp[day][last] = max
    return dp[day][last]

}

// TC: O(n * 4 * 3)
// SC: O(n * 4)
fun maxMeritTabulation(days: Int, tasks: Int, points: Array<IntArray>): Int {

    //dp array of [n][4], becoz we need to take into count 3 as well other than 0,1,2
    val dp = Array(days) {
        IntArray(tasks + 1) {
            -1
        }
    }

    //base case conversion, for day 0
    dp[0][0] = Math.max(points[0][1], points[0][2])
    dp[0][1] = Math.max(points[0][0], points[0][2])
    dp[0][2] = Math.max(points[0][1], points[0][0])
    dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]))


    //in recursive calls, we have all combinations of day and last task(so now convert them into 2 loops) and for each combination we have loop of tasks from 0 to 3,
    for (day in 1 until days) {

        // last logic loop
        for (last in 0 until tasks + 1) {

            dp[day][last] = 0
            for (i in 0 until tasks) {
                if (i != last) {
                    //current day task + same stuff for all other days from day-1 to 0
                    val point = points[day][i] + dp[day - 1][i]
                    dp[day][last] = Math.max(point, dp[day][last])
                }
            }

        }

    }

    return dp[days - 1][tasks] //from where we started recursion function, that will be last case here

}


// TC: O(n * 4 * 3)
// SC: O(4)
fun maxMeritTabuSpaceOptimization(days: Int, tasks: Int, points: Array<IntArray>): Int {

    //dp array of [n][4], becoz we need to take into count 3 as well other than 0,1,2
    //now, just store tasks for prev day, for all tasks, so use one dimensional array, (dp[day index] => day) can be replaced by index of tasks array prev, so that dp[day-1][task] becomes => prev[task]
    // so store only tasks result
    var prev = Array(tasks + 1) {
        -1
    }

    //base case conversion, for day 0
    prev[0] = Math.max(points[0][1], points[0][2])
    prev[1] = Math.max(points[0][0], points[0][2])
    prev[2] = Math.max(points[0][1], points[0][0])
    prev[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]))


    //in recursive calls, we have all combinations of day and last task(so now convert them into 2 loops) and for each combination we have loop of tasks from 0 to 3,
    for (day in 1 until days) {

        //current day temp array
        val temp = Array(tasks + 1) {
            -1
        }

        // last logic loop
        for (last in 0 until tasks + 1) {

            temp[last] = 0
            for (i in 0 until tasks) {
                if (i != last) {
                    //current day task + same stuff for all other days from day-1 to 0
                    temp[last] = Math.max(points[day][i] + prev[i], temp[last])
                }
            }

        }

        //update prev day with current day temp array
        prev = temp

    }

    return prev[tasks] //from where we started recursion function, that will be last case here

}


fun main() {

    val n = 3
    val arr = arrayOf(
        intArrayOf(1, 2, 5),
        intArrayOf(3, 1, 1),
        intArrayOf(3, 3, 3)
    )


//    val arr = arrayOf(
//        intArrayOf(10, 40, 70),
//        intArrayOf(20, 50, 80),
//        intArrayOf(30, 60, 90)
//    )

    println(maxMerit(day = n, points = arr))
    println(maxMeritMemo(day = n, points = arr))
    println(maxMeritTabulation(days = n, tasks = 3, points = arr))
    println(maxMeritTabuSpaceOptimization(days = n, tasks = 3, points = arr))

}