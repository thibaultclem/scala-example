/*

Define a function that calculates the square root of parameter x:
def sqrt(x: Double): Double = ...

The classical way to achieve this is by successive approximations using Newton’s method:

- Start with an initial estimate y (let’s pick y = 1).
- Repeatedly improve the estimate by taking the mean of y and x/y

 */

def abs(x: Double) = if (x >= 0) x else -x

def sqrIter(guess: Double, x: Double): Double =
  if (isGoodEnough(guess, x)) guess
  else sqrIter(improve(guess, x), x)

def isGoodEnough(guess: Double, x: Double): Boolean =
  abs(guess * guess - x) / x < 0.001

def improve(guess: Double, x: Double): Double =
  (guess + x / guess) / 2

def sqrt(x: Double) = sqrIter(1.0, x)

// test sqrt() function:
println("sqrt(2) = "+sqrt(2))
println("sqrt(4) = "+sqrt(4))
println("sqrt(1e-7) = "+sqrt(1e-7))
println("sqrt(1e23) = "+sqrt(1e23))