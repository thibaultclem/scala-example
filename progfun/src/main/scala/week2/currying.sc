/*

Currying

Methods may define multiple parameter lists. When a method is called with a fewer
number of parameter lists, then this will yield a function taking the missing
parameter lists as its arguments.

See step by step example below:

 */

//Function returning function
def sum(f: Int => Int): (Int, Int) => Int = {
  def sumF(a: Int, b: Int): Int =
    if (a > b) 0
    else f(a) + sumF(a + 1, b)
  sumF
}

/*
sum is now a function that returns another function.

The returned function sumF applies the given function parameter f
and sums the results.

We can then define:

 */
def sumInts = sum(x => x)
def sumCubes = sum(x => x * x * x)

//Tests
println("sumInts(3,7) = "+sumInts(3,7))
println("sumCubes(3,7) = "+sumCubes(3,7))

/*

We can also avoid the sumInts, sumCubes...

 */


println("sumInts(3,7) = "+sum(x => x)(3,7))
println("sumCubes(3,7) = "+sum(x => x * x * x)(3,7))


/*
Multiple Parameter Lists

The definition of functions that return functions is so useful in
functional programming that there is a special syntax for it in Scala.

For example, the following definition of sum is equivalent to the one
with the nested sumF function, but shorter:

 */

def sumShorter(f: Int => Int)(a: Int, b: Int): Int =
  if (a > b) 0 else f(a) + sum(f)(a + 1, b)

println("sumShorter(x => x)(3,7) = "+sumShorter(x => x)(3,7))
println("sumShorter(x => x * x * x)(3,7) = "+sumShorter(x => x * x * x)(3,7))

/*
This style of definition a function with multiple parameter lists and function
application is called currying

def f(args1)...(argsn−1)(argsn) = E
is shown to be equivalent to:
def f = (args1 ⇒ (args2 ⇒ ...(argsn ⇒ E)...))

Example:

def sum(f: Int => Int)(a: Int, b: Int): Int = ...
is type of:
(Int => Int) => (Int, Int) => Int
that is equivalent to:
Int => (Int => Int)

 */


/*

Exercises

 */

// 1.
// Write a product function that calculates the product of the values of a function
// for the points on a given interval.
def product(f: Int => Int)(a: Int, b: Int): Int =
  if (a > b) 1 else f(a) * product(f)(a + 1, b)
//test
println("product(x => x)(3, 7) = "+product(x => x)(3, 7))
// 2.
// Write factorial in terms of product.
def fact(x: Int): Int = product(x => x)(1, x)
//test
println("fact(5) = "+fact(5))

// 3.
// Can you write a more general function, which generalizes both sum and product?
def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int =
  if (a > b) zero else  combine(f(a), mapReduce(f, combine, zero)(a + 1, b))

//test
mapReduce(x => x, (a, b) => a * b, 1)(3, 7)
mapReduce(x => x, (a, b) => a + b, 0)(3, 7)

//Redefine product with mapReduce
def product2(f: Int => Int)(a: Int, b: Int): Int =
  mapReduce(f, (a, b) => a * b, 1)(a, b)

println("product2(x => x)(3, 7) = "+product2(x => x)(3, 7))

//Another way to define mapReduce
def mapReduce2(combine: (Int, Int) => Int, zero: Int)(f: Int => Int)(a: Int, b: Int): Int =
  if (a > b) zero else combine(f(a), mapReduce2(combine, zero)(f)(a + 1, b))

mapReduce2((a, b) => a * b, 1)(x => x)(3, 7)
mapReduce2((a, b) => a + b, 0)(x => x)(3, 7)

//Redefine product with mapReduce2
def product3(f: Int => Int)(a: Int, b: Int): Int =
  mapReduce2((a, b) => a * b, 1)(f)(a, b)
println("product3(x => x)(3, 7) = "+product3(x => x)(3, 7))