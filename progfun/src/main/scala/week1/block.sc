/*

BLOCK IN SCALA

It’s good functional programming style to split up a task into many
small functions.
But the names of functions like sqrtIter, improve, and isGoodEnough
matter only for the implementation of sqrt, not for its usage.
Normally we would not like users to access these functions directly.

We can achieve this by using blocks that are delimited by braces {...}
and putting the auxiliary functions inside this block

 */
def abs(x: Double) = if (x >= 0) x else -x

// The block sqrt
def sqrt(x: Double) = {

  def sqrIter(guess: Double, x: Double): Double =
    if (isGoodEnough(guess, x)) guess
    else sqrIter(improve(guess, x), x)

  def isGoodEnough(guess: Double, x: Double): Boolean =
    abs(guess * guess - x) / x < 0.001

  def improve(guess: Double, x: Double): Double =
    (guess + x / guess) / 2

  sqrIter(1.0, x)
}

// test sqrt() function:
println("sqrt(2) = "+sqrt(2))
println("sqrt(4) = "+sqrt(4))
println("sqrt(1e-7) = "+sqrt(1e-7))
println("sqrt(1e23) = "+sqrt(1e23))

/*

What is a block:

- A block is delimited by braces { ... }:
  { val x = f(3)
  x * x
  }
- It contains a sequence of definitions or expressions.
- The last element of a block is an expression that defines its
value.
- This return expression can be preceded by auxiliary definitions.
- Blocks are themselves expressions; a block may appear
everywhere an expression can.

Visibility of a block:

- The definitions inside a block are only visible from within the
block.
- The definitions inside a block shadow definitions of the same
names outside the block.

 */

val x = 0
def f(y: Int) = y + 1
val result = {
  val x = f(3)
  x * x
}
println("result equal "+result)
println("x still equal"+x)