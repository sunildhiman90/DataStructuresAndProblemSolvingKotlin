package problemsolving.stack

import java.util.Stack

class InfixToPostfix {
}

fun precedence(c: Char): Int {
    return when (c) {
        '+', '-' -> 1
        '*', '/' -> 2
        '^' -> 3
        else -> -1
    }
}

fun infixToPostfix(input: String): String {
    var output = ""
    val stack = Stack<Char>()

    for (c in input) {

        //if operand, add it to output
        if (c.isLetterOrDigit()) {
            output += c
        } else if (c == '(') {
            stack.push(c)
        } else if (c == ')') {
            //pop all from stack and add to output until ( is found
            while (!stack.isEmpty() && stack.peek() != '(') {
                output += stack.peek()
                stack.pop()
            }

            //pop ( also
            if (!stack.isEmpty()) stack.pop()

        } else {
            //operator
            //pop from stack and add to output if precedence of current char <= stack top
            while (!stack.isEmpty() && stack.peek() !='(' && precedence(c) <= precedence(stack.peek())) {
                output += stack.peek()
                stack.pop()
            }

            //and then push current char to stack
            stack.push(c)
        }
    }

    //pop all remaining data
    while (!stack.isEmpty()) {
        output += stack.peek()
        stack.pop()
    }

    return output

}

//will not work in case of unary operator : 1-(-2)
fun evaluateExpression(s: String): Int {
    val postfix = infixToPostfix(s)
    val stack = Stack<Int>()

    for(c in postfix) {
        //if operand(digit) , use digitToInt to convert into int and then push to stack
        if(c.isDigit()) {
            stack.push(c.toInt() - '0'.toInt())
        } else {
            //if operator, take 2 operands and evaluate them, and push result to stack
            if(stack.size >= 2) {
                val value1 = stack.pop()
                val value2 = stack.pop()
                when(c) {
                    '+' -> stack.push(value2 + value1)
                    '-' -> stack.push(value2 - value1)
                    '*' -> stack.push(value2 * value1)
                    '/' -> stack.push(value2 / value1)
                }
            }

        }
    }
    return stack.pop()
}

fun main() {
    //val input = "a+b*(c^d-e)^(f+g*h)-i"
    //val input = "1+1"
    val input = "1-(-2)" //will not work in case of unary operator : 1-(-2), wrong output
    println(infixToPostfix(input))
    val result = evaluateExpression(infixToPostfix(input))
    println(result)

}