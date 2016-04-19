/*
Decomposition

We are trying to solve is find a general and convenient way
to access objects in a extensible class hierarchy.

▶ Classification and access methods: quadratic explosion
▶ Type tests and casts: unsafe, low-level
▶ Object-oriented decomposition: does not always work, need to
touch all classes to add a new method.

Non-Solution: Type Tests and Type Casts

A “hacky” solution could use type tests and type casts but their use in Scala is discouraged,
 because there are better alternatives:

Pattern Matching

 */

/*

Functional Decomposition with Pattern Matching

A case class definition is similar to a normal class definition, except
that it is preceded by the modifier case. For example:

 */
trait Expr
case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr

/*

It also implicitly defines companion objects with apply methods.

object Number {
  def apply(n: Int) = new Number(n)
}

object Sum {
  def apply(e1: Expr, e2: Expr) = new Sum(e1, e2)
}

so you can write Number(1) instead of new Number(1).

Pattern matching is a generalization of switch from C/Java to class
hierarchies.

It’s expressed in Scala using the keyword match:

 */
def eval(e: Expr): Int = e match {
  case Number(n) => n
  case Sum(e1, e2) => eval(e1) + eval(e2)
}

/*

Rules:
▶ match is followed by a sequence of cases, pat => expr.
▶ Each case associates an expression expr with a pattern pat.
▶ A MatchError exception is thrown if no pattern matches the
value of the selector.

Patterns are constructed from:
▶ constructors, e.g. Number, Sum,
▶ variables, e.g. n, e1, e2,
▶ wildcard patterns _,
▶ constants, e.g. 1, true.

Variables always begin with a lowercase letter.

The same variable name can only appear once in a pattern. So,
Sum(x, x) is not a legal pattern.

Names of constants begin with a capital let

 */

/*

Of course, it’s also possible to define the evaluation function as a
method of the base trait.

Example:

trait Expr2 {
  def eval: Int = this match {
    case Number(n) => n
    case Sum(e1: Expr2, e2: Expr2) => e1.eval + e2.eval
  }
}

Pattern matching is better when you add more and more function to a class
but decomposition is better when you add more and more subtype of the class

*/

/*

Exercise

Write a function show that uses pattern matching to return the
representation of a given expressions as a string.

 */

def show(e: Expr): String = e match {
  case Number(n) => n.toString
  case Sum(e1, e2) => show(e1) + " + " + show(e2)
}

//test
println(show(Sum(Number(1),Number(5))))