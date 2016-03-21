import math.abs
import scala.annotation.tailrec

object fixedPointExercice {

  /*

  Finding a fixed point of a function

  This exercise attempt to demonstrate the expressive power of a
  language is greatly increased if we can pass function arguments.

  A number x is called a fixed point of a function f if
  f(x) = x

  For some functions f we can locate the fixed points by starting with
  an initial estimate and then by applying f in a repetitive way.
  x, f(x), f(f(x)), f(f(f(x))), ...
  until the value does not vary anymore (or the change is sufficiently
  small).

   */

  val tolerance = 0.0001

  def isCloseEnough(x: Double, y: Double) =
    abs((x - y) / x) / x < tolerance

  def fixedPoint(f: Double => Double)(firstGuess: Double) = {

    @tailrec
    def iterate(guess: Double): Double = {
      val next = f(guess)
      if (isCloseEnough(guess, next)) next
      else iterate(next)
    }

    iterate(firstGuess)
  }

  //test
  fixedPoint(x => 1 + x/2)(1)

  // Use fixedPoint to define sqrt function
  // first attempt:
  def sqrt(x: Double) =
    fixedPoint(y => x / y)(1.0)
  //sqrt(2) -> Infinite loop: 1,2,1,2,1,...

  // second attempt: Average Damping
  // One way to control such oscillations is to prevent the estimation
  // from varying too much. This is done by averaging successive values
  // of the original sequence:
  def sqrt2(x: Double) =
    fixedPoint(y => (y + x / y) / 2)(1.0)
  //test
  sqrt2(2)

  /*

  Write a square root function using fixedPoint and averageDamp.

  This exercise attempt to demonstrate that functions that return functions
  can also be very useful.

  Consider again iteration towards a fixed point.
  We begin by observing that √
  x is a fixed point of the function y =>
  x / y.

  Then, the iteration converges by averaging successive values.

  This technique of stabilizing by averaging is general enough to merit
  being abstracted into its own function.

 */

  def averageDamp(f: Double => Double)(x: Double) = (x + f(x)) / 2

  //Write a square root function using `fixedPoint` and `averageDamp`.
  def sqrt3(x: Double) =
    fixedPoint(averageDamp(y => x / y))(1)
  //test
  sqrt3(2)
}