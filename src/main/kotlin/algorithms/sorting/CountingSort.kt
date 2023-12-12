package algorithms.sorting

/**
 * Definition:-
 * Counting Sort is a non-comparison-based sorting algorithm that works well when there is limited range of input values.
 * It is particularly efficient when the range of input values is small compared to the number of elements to be sorted.
 * The basic idea behind Counting Sort is to count the frequency of each distinct element in the input array and use that
 * information to place the elements in their correct sorted positions.
 */

/**
 * This is Non comparison sorting algo -> [While Selection, bubble insertion, quick sort, heap sort and merge sort are comparison
 * sorting algos]
 */

fun countingSort(a: IntArray, n: Int, k: Int) {
    val count = IntArray(k + 1) //it will be initialized to 0 automatically
    val b = IntArray(n)

    //Step 1. Storing freq of elements (element will be keys of count array and freq will be values)
    for (i in 0 until n) {
        ++count[a[i]]
    }

    //Step 2. Calculating actual(last position for repeating elements) position of elements in count array, start from 1 index, current + prev pos
    for (i in 1..k) {
        count[i] = count[i] + count[i - 1]
    }

    //Step 3. Copying sorted elements in new array, after decrementing value at index of c => a[i]-1, becoz a[i] (element) is index of count array and value is its position now, SO we need to use position-1 for storing in array, because count array is of size largest element , but indexing is based on 0, so we have to position-1
    for (i in n - 1 downTo 0) {
        val indexToUse =
            count[a[i]] - 1 //value at index a[i] in count array, where a[i] is index of a[i]th element in count array and value at that index is position+1,
        val valueToCopy = a[i]
        b[indexToUse] = valueToCopy
        --count[a[i]] //decrement that value for using this position in case of if same character is repeating,

        //alternative, decrement value at arr[i] and then use as index of b
        //b[--count[a[i]]] = a[i];
    }

    //Step4, copy new array back to original array
    for (i in 0 until n) {
        a[i] = b[i]
    }
}

fun main() {
    val a = intArrayOf(1, 4, 7, 2, 9, 3, 5, 0, 3, 5, 2, 1)
    /* int largest = a[0];
        for(int i = 1; i < a.length; i++) {
            if(a[i] > largest) {
                largest = a[i];
            }
        }*/
    val largest = a.max()
    countingSort(a, a.size, largest)
    println(a.contentToString())
}


/**
Time complexity : O(n+k) for all cases best average and worst
Space complexity : O(n+k)  for all cases best average and worst
 */


/**
 * Worst case time complexity:-
 *
 * Worst case time complexity is when the data is skewed that is the largest element is significantly large than other elements.
 * This increases the range K.
 *
 * As the time complexity of algorithm is O(n+k) then, for example, when k is of the order O(n^2), it makes the time complexity O(n+(n^2)),
 * which essentially reduces to O( n^2 ) and if k is of the order O(n^3), it makes the time complexity O(n+(n^3)),
 * which essentially reduces to O( n^3 ). Hence, in this case, the time complexity got worse making it O(k) for such larger values of k.
 * And this is not the end. It can get even worse for further larger values of k.
 * Thus the worst case time complexity of counting sort occurs when the range k of the elements is significantly larger than the other elements.
 *
 * Best case time complexity:- when difference between element is very small
 *
 * The best case time complexity occurs when all elements are of the same range that is when k is of 1 digit betweeen 0 to 9.
 * In this case, counting the occurrence of each element in the input range takes constant time and then finding the correct index value of
 * each element in the sorted output array takes n time, thus the total time complexity reduces to O(1 + n) i.e O(n) which is linear
 *
 * Average case time complexity:-
 * To compute the average case time complexity, first we fix N and take different values of k from 1 to infinity, in this case k computes to be (k+1/2) and the average case will be N+(K+1)/2. But as K tends to infinity, K is the dominant factor.
 * Similarly,now if we vary N, we see that both N and K are equally dominant and hence, we have O(N+K) as average case.
 *
 */


/**
 * Worst case space complexity:- O(k), because k is very large as compared to n and we reauired array of size k
 * Best case space complexity:- O(n+1), because k is very small, so n+k becomes -> n+1 = n
 * Average case space complexity:- O(n+k),
 *
 */


/**
 *
 * For time complexity:-
 * Reference : https://iq.opengenus.org/time-and-space-complexity-of-counting-sort/
 *
 *For space Complexity:-
 * https://www.geeksforgeeks.org/bucket-sort-2/
 *
 */