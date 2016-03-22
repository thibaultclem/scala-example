/*

Polymorphism

Polymorphism means that a function type comes "in many forms"

In programming it means that:
- the function can be applied to arguments of many types
- the type can have instances of many types

Two principals forms of polymorphism:
- subtyping: instances of a subclass can be passed to a base class:
  You can pass Nil or Cons to a method waiting List class as parameter
- generics: instances of a function or class are created by type parameterization:
  See List[T] in List.scala where we can create list of Int, Boolean, ...

*/

/*

Classes

Example with the Cons-List (Linked List):

Here is a list only working for Int (not scalable to Double, ...):
trait IntList ...
class Cons(val head: Int, val tail: IntList) extends IntList ...
class Nil extends IntList ...

Instead of having another class hierarchy for Double lists and so on,
we can generalize the definition using a type parameter:

Type parameter are written in square brackets, e. g. [T]

trait List[T]
class Cons[T](val head: T, val tail: List[T]) extends List[T]
class Nil[T] extends List[T]

See List.scala

*/

/*

Functions

Like classes, functions can have type parameters

For instance, here is a function that creates a list consisting of a single element:

 */

def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])

//We can write
singleton[Int](2)
singleton[Boolean](true)

//Type parameters can be left out (Scala compiler can usually deduce the correct type):
singleton(2)
singleton(true)

/*

Exercise

Write a function nth that takes an integer n and a list and selects the
n'th element of the list.

Elements are numbered from 0.

If index is outside the range from 0 up to the length of the list minus one,
a IndexOutOfBoundsException should be thrown.

 */

//test


def nth[T](n: Int, list: List[T]): T = {
  if (list.isEmpty) throw new IndexOutOfBoundsException
  else if (n == 0) list.head
  else nth(n - 1, list.tail)
}

//test
val list = new Cons(1, new Cons(2, new Cons(3, new Nil)))
println(nth(2, list))
nth(-1, list)



/*

Copy of List.scala (issue when importing in the sheet with IntelliJ) :

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