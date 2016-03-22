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