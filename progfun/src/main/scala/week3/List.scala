package week3

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