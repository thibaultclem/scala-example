/*

The function type A => B is just an abbreviation for the class
scala.Function1[A, B], which is defined as follows.

package scala

trait Function1[A, B] {
  def apply(x: A): B
}

So functions are objects with apply methods.

There are also traits Function2, Function3, … for functions which
take more parameters (currently up to 22).

Other example:

A function call, such as f(a, b), where f is a value of some class
type, is expanded to

f.apply(a, b)

So the OO-translation of


val f = (x: Int) => x * x
f(7)

would be

val f = new Function1[Int, Int] {
  def apply(x: Int) = x * x
}
f.apply(7)

 */


/*

Note that a method such as

def f(x: Int): Boolean = ...

is not itself a function value.

But if f is used in a place where a Function type is expected, it is
converted automatically to the function value

(x: Int) => f(x)

or, expanded:

new Function1[Int, Boolean] {
  def apply(x: Int) = f(x)
}

 */


/*

Exercise

Define an:

object List {
...
}

with 3 functions in it so that users can create lists of lengths 0-2
using syntax

List() // the empty list
List(1) // the list with single element 1
List(2, 3) // the list with elements 2 and 3.

 */

package week4

/**
  * The Cons-List
  *
  * Created by thibaultclement on 22/03/16.
  */
trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
}

class Nil[T] extends List[T] {
  def isEmpty = true
  def head: Nothing = throw new NoSuchElementException("Nil.head") // 'Nothing' is a subtype of any other type
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}

object List {
  //List(2, 3) = List.apply(1, 2)
  def apply[T](x1: T, x2: T): List[T] = new Cons(x1, new Cons(x2, new Nil))
  //List() = List.apply()
  def apply[T]() = new Nil
}