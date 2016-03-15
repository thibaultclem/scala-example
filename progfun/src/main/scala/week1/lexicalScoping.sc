/*

BLOCK IN SCALA

Definitions of outer blocks are visible inside a block unless they are
shadowed.

Therefore, we can simplify sqrt by eliminating redundant
occurrences of the x parameter, which means everywhere the same
thing:

 */
def abs(x: Double) = if (x >= 0) x else -x

// The block sqrt
def sqrt(x: Double) = {

  def sqrIter(guess: Double): Double =
    if (isGoodEnough(guess)) guess
    else sqrIter(improve(guess))

  def isGoodEnough(guess: Double): Boolean =
    abs(guess * guess - x) / x < 0.001

  def improve(guess: Double): Double =
    (guess + x / guess) / 2

  sqrIter(1.0)
}
// test sqrt() function:
println("sqrt(2) = "+sqrt(2))
println("sqrt(4) = "+sqrt(4))
println("sqrt(1e-7) = "+sqrt(1e-7))
println("sqrt(1e23) = "+sqrt(1e23))