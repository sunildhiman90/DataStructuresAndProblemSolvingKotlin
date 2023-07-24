package algorithms.sorting

import java.util.*

/**
It divides the unsorted array into separate groups and calls them buckets.
Sort the individual buckets, and then gather them all together to form the final sorted array.
*/
fun bucketSortFloats(arr: FloatArray, n: Int, numOfBuckets: Int) {

    //Step1: create n buckets with empty vector list
    val buckets: Array<Vector<Float>> = Array(numOfBuckets) {
        Vector<Float>()
    }


    //Step2: Put array elements in buckets
    for (i in 0 until n) {
        val index = (arr[i] * n).toInt()
        buckets[index].add(arr[i])
    }

    //Step3: Sort all buckets
    for (i in 0 until numOfBuckets) {
        buckets[i].sort()
    }

    //Step4: Concatenate buckets back to array
    var index = 0
    for (i in 0 until numOfBuckets) {
        for (j in buckets[i].indices) {
            arr[index++] = buckets[i][j]
        }
    }
}

fun bucketSortInteger(arr: DoubleArray, n: Int, numOfBuckets: Int) {
    val minElement = arr.min()
    val maxElement = arr.max()
    val range = (maxElement - minElement) / numOfBuckets

    //Step1: create numOfBuckets buckets with empty vector list
    val buckets: Array<Vector<Double>> = Array(numOfBuckets) {
        Vector()
    }
    println("range=$range")

    //Step2: Put array elements in buckets
    for (i in 0 until n) {
        //This part is just for checking the index out of bounds case for when arr[i] is maxElement , then index of array == numOfBucket => index oout of bound, so we need to decrement 1 from that
        //WE NEED TO CHECK FOR IN WHICH CASE WE ARE GETTING COMPELTE NUMBER HERE, THATS WHY WE ARE CHECKING DOUBLE PART - INTEGER PART
        //AND WHEN NUMBER WILL BE COMPLETE, THEN DIFFERENCE BETWEEN DOUBLE VALUE - INTEGER VALUE WILL BE ZERO(AND THAT WILL BE THE CASE WE ARE CHECKING TO AVOID INDEX OUT OF BOUNDS), OTHERWISE IT WILL BE IN DECIMAL
        val diff = (arr[i] - minElement) / range - ((arr[i] - minElement) / range).toInt()
        //this will happen only when arr[i] is maxElement , This part explanation is given below: in EXP
        //if(arr[i] == maxElement && diff == 0) { //this if can also be written as like this
        if (arr[i] != minElement && diff == 0.0) {
            val index = ((arr[i] - minElement) / range).toInt() - 1
            println("index1=$index")
            buckets[index].add(arr[i])
        } else {
            val index = ((arr[i] - minElement) / range).toInt()
            println("index2=$index")
            buckets[index].add(arr[i])
        }
    }

    //Step3: Sort all buckets
    for (i in 0 until numOfBuckets) {
        buckets[i].sort()
    }

    //Step4: Concatenate buckets back to array
    var index = 0
    for (i in 0 until numOfBuckets) {
        for (j in buckets[i].indices) {
            arr[index++] = buckets[i][j]
        }
    }
}

fun main() {

    /* float arr[] = {(float) 0.865, (float) 0.466,
                (float) 0.356, (float) 0.134,
                (float) 0.575, (float) 0.64};
        int n = arr.length;
        int noOfBuckets = n;
        bucketSortFloats(arr, n, noOfBuckets);*/

    //For Integer part
    val arr = doubleArrayOf(9.8, 0.6, 10.1, 1.9, 3.07, 3.04, 5.0, 8.0, 4.8, 7.68)
    val noOfBuckets = 5
    val n = arr.size
    bucketSortInteger(arr, n, noOfBuckets)
    println("Sorted array is :" + arr.contentToString())
}



/**
 * EXP:
 * This if(arr[i] != minElement && diff == 0) -> is for the case when arr[i] is max(10.1),
 * <p>
 * Point 1 :-
 * becoz we are calculating range as ->  double range = (maxElement - minElement) / numOfBuckets => it means numOfBuckets = (maxElement - minElement) / range
 * <p>
 * Point 2:-
 * for example -> our range = (10.1 - 0.6) / 5 = 1.9
 * and now come to that case, there arr[i] is max element, and minElement is minElement,
 * so index will become -> (maxElement - minElement) / range which will be equal to numOfBuckets by checking above Point 1
 * so it will become 5, as index of buckets array will go from 0 to 4, so thats why we are using -1 in that case
 * <p>
 * <p>
 * Time Complexity:
 * The time complexity of bucket sort is O(n + k) for average and best case, where n is the number of elements and k is the number of buckets.
 * <p>
 * Auxiliary Space :
 * The Auxiliary Space of bucket sort is O(n + k). This is because we need to create a new array of size k to store the buckets and another array of size n to store the sorted elements.
 */


/**
 *
 * Best Case Complexity :-
 *
 * The algorithm shows the best-case complexity when all the elements are distributed uniformly in the buckets,
 * i.e. when there is almost the same number of elements in each bucket.
 * If the buckets are already sorted, then the time complexity may increase
 * Let there be k buckets in total, if we use insertion sort to sort the elements in the buckets,
 * the time complexity will become O(N+k), this is because every time we will push the element into the bucket,
 * it has to travel to the very extreme point to reach its original position.
 * The time complexity of creating the buckets will be O(N) and the complexity for sorting the buckets will be O(k)
 * becoz each bucket will take constant time O(1) as they have same number of elements,
 * so the overall time complexity of bucket sorting in the best case will be O(N+k).
 *
 * Average Case Complexity :-
 *
 * The algorithm shows the average case complexity when all the elements are distributed randomly in the buckets.
 * It will run in linear time complexity i.e. O(N), even if the elements are not distributed uniformly in the array.
 *
 * Worst Case Complexity O(n*n) -> Not clear, Doubt
 *
 * The algorithm shows the worst-case complexity when all the elements are placed in the same bucket.
 * As a result, only one bucket will be formed, containing all the elements
 * So now if we sort this bucket using insertion or selection sort, it will take O(N) worst case time complexity for each element,
 * so the total time complexity comes out to be O(N^2).
 *
 * Reference Link: https://www.scaler.com/topics/worst-time-complexity-of-bucket-sort/
 */