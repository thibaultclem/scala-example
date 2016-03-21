/*
Higher-Order Functions

Functional languages treat functions as first-class values.

This means that, like any other value, a function can be passed as a
parameter and returned as a result.

This provides a flexible way to compose programs.

Functions that take other functions as parameters or that return
functions as results are called higher order functions.

 */

/*

Normal way:

 */

//Take the sum of the integers between a and b
def sumInts(a: Int, b: Int): Int =
  if (a > b) 0 else a + sumInts(a+1, b)
//Take the sum of the cubes of all the integers between a and b
def cube(x: Int): Int = x * x * x
def sumCubes(a: Int, b: Int): Int =
  if (a > b) 0 else cube(a) + sumCubes(a+1, b)
//Take the sum of the factorials of all the integers between a and b :
def fact(x: Int): Int =
  if (x == 0) 1 else x * fact(x-1)
def sumFactorials(a: Int, b: Int): Int =
  if (a > b) 0 else fact(a) + sumFactorials(a + 1, b)

//test
println("sumInts(3,7) = "+sumInts(3,7))
println("sumCubes(3,7) = "+sumCubes(3,7))
println("sumFactorials(3,7) = "+sumFactorials(3,7))


/*

Now summing with the Higher-Order Function sum:

 */
def sum(f: Int => Int, a: Int, b: Int): Int =
  if (a > b) 0
  else f(a) + sum(f, a+1, b)

// Then rewrite sumInts, sumCubes and sumFactorials using sum
def sumIntsHigh(a: Int, b: Int): Int = sum(id, a, b)
def sumCubesHigh(a: Int, b: Int): Int = sum(cube, a, b)
def sumFactorialsHigh(a: Int, b: Int): Int = sum(fact, a, b)

def id(x: Int): Int = x

//test
println("sumIntsHigh(3,7) = "+sumIntsHigh(3,7))
println("sumCubesHigh(3,7) = "+sumCubesHigh(3,7))
println("sumFactorialsHigh(3,7) = "+sumFactorialsHigh(3,7))

/*

Anonymous Functions
Write functions without giving it a name are called anonymous functions.

 */

def sumIntsAnonym(a: Int, b: Int): Int = sum(x => x, a, b)
def sumCubesAnonym(a: Int, b: Int): Int = sum(x => x * x * x, a, b)

//test
println("sumIntsAnonym(3,7) = "+sumIntsAnonym(3,7))
println("sumCubesAnonym(3,7) = "+sumCubesAnonym(3,7))

/*

Write a tail-recursive version of sum

 */

def sumTailRec(f: Int => Int, a: Int, b: Int): Int = {
  def loop(a: Int, acc: Int): Int = {
    if (a > b) acc
    else loop (a+1, acc+f(a))
  }
  loop(a,0)
}

//test
println("sumTailRec(id, 3,7) = "+sumTailRec(x => x, 3, 7))
println("sumTailRec(cube, 3,7) = "+sumTailRec(x => x * x * x, 3, 7))