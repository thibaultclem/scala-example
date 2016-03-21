package recfun
import common._

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
    * Exercise 1: Pascal's Triangle
    *
    * The following pattern of numbers is called Pascal’s triangle.

          1
         1 1
        1 2 1
       1 3 3 1
      1 4 6 4 1
     ...

    * The numbers at the edge of the triangle are all 1, and each number inside the triangle is the sum of the two
    * numbers above it. Write a function that computes the elements of Pascal’s triangle by means of a recursive process.
   */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || r == 0 || c == r) 1 else pascal(c-1, r-1) + pascal(c, r-1)
  }

  /**
    * Exercise 2: Parentheses Balancing
    *
    * Write a recursive function which verifies the balancing of parentheses in a string, which we represent as a
    * List[Char] not a String
    *
   */
  def balance(chars: List[Char]): Boolean = {

    @tailrec
    def countBalance(remainingChars: List[Char], countOpenBracket: Int): Boolean = {
      //Check if still no ended parenthesis at the end of the string
      if (remainingChars.isEmpty && countOpenBracket <= 0) true
      //Check if ended bracket without a previous open parenthesis
      else if (remainingChars.isEmpty || (remainingChars.head == ')' && countOpenBracket <= 0)) false
      else countBalance(remainingChars.tail, updateCounter(remainingChars.head, countOpenBracket))
    }

    def updateCounter(char: Char, countOpenBracket: Int): Int =
      if (char == '(') countOpenBracket + 1 else if (char == ')') countOpenBracket - 1 else countOpenBracket

    countBalance(chars, 0)
  }

  /**
    * Exercise 3: Counting Change
    *
    * Write a recursive function that counts how many different ways you can make change for an amount, given a list
    * of coin denominations.
    *
   */
  def countChange(money: Int, coins: List[Int]): Int = {

    def countChangeFirstInList(m: Int, cs: List[Int]): Int = {
      if (m == 0) 1
      else if (cs.isEmpty || m < 0) 0
      else countChangeFirstInList(m - cs.head, cs) + countChangeFirstInList(m, cs.tail)
    }

    if (coins.isEmpty || money == 0) 0
    else countChangeFirstInList(money, coins)
  }
}
