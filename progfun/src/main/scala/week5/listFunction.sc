/*

  Utilities methods for List

 */
val fruit = List("apples", "oranges", "pears")
val legumes = List("choux","carrottes","poireaux")

//Sublists and element access
fruit.length
fruit.last
fruit.init
fruit take 1
fruit drop 2
fruit(2)

//Creating new lists
fruit ++ legumes
fruit.reverse
fruit.updated(1,"mandarines")

//Finding elements
fruit indexOf "pears"
fruit indexOf "notExistFruits"
fruit contains "pears"
fruit contains "notExistFruits"


/*

Some exercise to implement List methods

 */
def init[T](xs: List[T]): List[T] = xs match {
  case List() => throw new Error("init of empty list")
  case List(x) => List()
  case y :: ys => y :: init(ys)
}

def reverse[T](xs: List[T]): List[T] = xs match {
  case List() => List()
  case y :: ys => reverse(ys) ++ List(y)
}

def removeAt[T](n: Int, xs: List[T]) = {
  (xs take n) ::: (xs drop n+1)
}
removeAt(1, List('a', 'b', 'c', 'd'))

/*

Map

Applying a function to each element in the list.

 */
val xs = List(1, -3, 7, 9)
val factor = 2
xs map (x => x * factor)

/*

Filter

Loops over the List/Seq you supply, tests each element of the List with the
function you supply. Your function must return true or false, and filter
returns the list elements where your function returns true

 */

xs filter (x => x > 0)

/*

Filter and Partition list

 */

//filterNot
xs filterNot (x => x > 0)

//partition => return tuples
xs partition (x => x > 0)

//takeWhile => return first elements until the condition is true
xs takeWhile  (x => x > 0)

//dropWhile => return last elements from the condition is true
xs dropWhile (x => x > 0)

//Span => Returns the longest prefix of the list whose elements all satisfy
// the given predicate, and the rest of the list.
xs span (x => x > 0)


/*

Reduction of Lists

reduceLeft inserts a given binary operator between adjacent elements of a list:

Using reduceLeft we can simplify sum or product of List

 */

def sum(xs: List[Int]) = (0 :: xs) reduceLeft ((x, y) => x + y)
def product(xs: List[Int]) = (1 :: xs) reduceLeft ((x, y) => x * y)

// can be even shorter as Scala allow to replace ((x, y) => x + y) by (_ * _) :
def sum2(xs: List[Int]) = (0 :: xs) reduceLeft (_ + _)
def product2(xs: List[Int]) = (1 :: xs) reduceLeft (_ * _)

/*

FoldLeft

Like reduceLeft but take an accumalator z as an additional parameter
This parameter is returned when foldLeft is called on an empty List

 */
def sum3(xs: List[Int]) = (xs foldLeft 0) (_ + _)
def product3(xs: List[Int]) = (xs foldLeft 1) (_ * _)