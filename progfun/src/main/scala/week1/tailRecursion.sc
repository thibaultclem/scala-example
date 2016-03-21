import scala.annotation.tailrec

/*

Tail Recursion

If a function calls itself as its last
action, the function’s stack frame can be reused. This is called tail
recursion.
⇒ Tail recursive functions are iterative processes.

In Scala, only directly recursive calls to the current function are
optimized.

fonction récursionTerminale(n) :
  // ...
  retourne récursionTerminale(n - 1)

fonction récursionNonTerminale(n) :
  // ...
  retourne n + récursionNonTerminale(n - 1)


Inverse of a tail-recursive function is a linear-recursive function

 */

def tailRecursiveFunction(n: Int): Int =
  //...
  tailRecursiveFunction(n-1)

def notTailRecursiveFunction(n: Int): Int =
  //...
  n + notTailRecursiveFunction(n-1)

// Example of tail recursive function with gcd(): computes the greatest common
// divisor of two numbers (using Euclid’s algorithm).
def gcd(x: Int, y: Int): Int =
  if (y == 0) x else gcd(y, x % y)
// test:
println("gcd(28,21) = "+gcd(28,21))

// Example of a no tail recursive function with factorial()
def factorial(x: Int): Int =
  if (x == 0) 1 else x * factorial(x-1)
// test:
println("factorial(5) = "+factorial(5))


/*

In Scala, only directly recursive calls to the current function are
optimized.

One can require that a function is tail-recursive using a @tailrec
annotation.

If the annotation is given, and the implementation is not
tail recursive, an error would be issued.

 */

// Example of tail recursive version of factorial
def factorial2(x: Int): Int = {

  @tailrec
  def loop(acc: Int, n: Int): Int =
    if (n == 0) acc
    else loop(acc * n, n-1)

  loop(1,x)
}
println("factorial(5) = "+factorial2(5))