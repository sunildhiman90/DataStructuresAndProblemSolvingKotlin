package problemsolving.recursion_backtracking.recursion

// Check Palindrome String

fun checkPalindrome(str: String, n: Int): Boolean {
    return checkPalindromeHelper(str, 0, n - 1)
}

fun checkPalindromeHelper(str: String, l: Int, r: Int): Boolean {

    if (l >= r) {
        return true
    }

    if (str[l] != str[r]) return false

    return checkPalindromeHelper(str, l + 1, r - 1)

}


//Using single variable
fun checkPalindrome2(str: String, n: Int): Boolean {
    return checkPalindromeHelper2(str, n, 0)
}

fun checkPalindromeHelper2(str: String, n: Int, i: Int): Boolean {

    if (i >= n / 2) {
        return true
    }

    if (str[i] != str[n - i - 1]) return false

    return checkPalindromeHelper2(str, n, i + 1)

}

fun main() {
    val str = "12321"
    println(checkPalindrome(str, str.length))

    println("-----")
    val str2 = "12325"
    println(checkPalindrome2(str2, str2.length))
}