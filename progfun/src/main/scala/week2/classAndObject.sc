/*
Class and Object

How functions create and encapsulate data structures ?

Suppose we want to implement the addition of two rational
numbers (a rational number is x/y):

def addRationalNumerator(n1: Int, d1: Int, n2: Int, d2: Int): Int
def addRationalDenominator(n1: Int, d1: Int, n2: Int, d2: Int): Int

It would be difficult to manage all these numerators and
denominators.

A better choice is to combine the numerator and denominator of a
rational number in a data structure.

In Scala, we do this by defining a class:

 */
class Rational(x: Int, y: Int) {

  //Represented Rational in their simplest form
  private def gcd(x: Int, y: Int): Int = if (y == 0) x else gcd(y, x % y)
  private val g = gcd(x, y)

  // # requirement
  require(y != 0, "Denominator must not be zero")
  // See assert paragraph at the end of this sheet

  // # constructor
  def this(x: Int) = this(x, 1)

  // # members
  def numer = x / g
  def denom = y / g
  // if the functions numer and denom are called often, it's better to declare
  // them as val instead of def

  // # methods
  def add(r: Rational) =
    new Rational(
      numer * r.denom + r.numer * denom,
      denom * r.denom
    )

  def neg =
    new Rational(-numer, denom)

  def sub(r: Rational) =
    add(r.neg)

  //We can override methods
  override def toString = numer + "/" + denom

  def less(r: Rational) = numer * r.denom < r.numer * denom

  def max(r: Rational) = if(this.less(r)) r else this

  def min(r: Rational) = if (this.less(r)) this else r
}


/*

This definition introduces two entities:
▶ A new type, named Rational.
▶ A constructor Rational to create elements of this type

We call the elements of a class type objects:
 */
val x = new Rational(1,3)

//tests
val y = new Rational(5,7)
val z = new Rational(3,2)
x.numer
x.denom
x.add(new Rational(2,3))
x.sub(new Rational(2,3))

// With the values x, y, z as given (Rational(1,3), Rational(5,7), Rational(3,2)),
// what is the result of x - y- z ?
println(x.sub(y).sub(z))

//less, min, max functions
x.less(y) //true
x.max(y) //y
x.min(y) //x

val strange = new Rational(1, 0)
strange.add(strange) //ArithmeticException: / by zero if no requirement in the class

// Use the second constructor with only one parameter
val w = new Rational(5)


/*

Assertions

Besides require, there is also assert.

Assert also takes a condition and an optional message string as
parameters. E.g.

val x = sqrt(y)
assert(x >= 0)

Like require, a failing assert will also throw an exception, but it’s a
different one: AssertionError for assert, IllegalArgumentException
for require.

This reflects a difference in intent:
▶ require is used to enforce a precondition on the caller of a
function.
▶ assert is used as to check the code of the function itself.

 */


/*

Infix Notification

Any method with a parameter can be used like an infix operator.
It is therefore possible to write

 */
x add y // same as a.add(b)
x less y // same as a.less(b)
x max y // same as a.max(b)