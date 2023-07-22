package algorithms.sorting

import java.util.*


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