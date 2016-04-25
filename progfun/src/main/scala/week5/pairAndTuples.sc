/*

The pair consisting of x and y is written (x, y) in Scala.

 */

val pair = ("answer", 42)
//Pairs can also be used as patterns:
val (label, value) = pair
//The fields of a tuple can be accessed with names _1, _2, …
//So instead of the pattern binding
//val (label, value) = pair
//one could also have written:
val label2 = pair._1
val value2 = pair._2
//But the pattern matching form is generally preferred.

/*

Exercise

The merge function as given uses a nested pattern match.
This does not reflect the inherent symmetry of the merge algorithm.
Rewrite merge using a pattern matching over pairs.

 */
def merge(xs: List[Int], ys: List[Int]): List[Int] =
  (xs, ys) match {
    case (Nil, ys) => ys
    case (xs, Nil) => xs
    case (x :: xs1 , y :: ys1) => {
      if (x < y) x :: merge(xs1, ys)
      else y :: merge(xs, ys1)
    }
  }