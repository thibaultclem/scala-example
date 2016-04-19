package week4

/**
  * Created by thibaultclement on 18/04/16.
  */

//Provide an implementation of the abstract class Nat that represents non-negative integers

abstract class Nat {
  def isZero: Boolean
  def predecessor: Nat
  def successor: Nat  = new Succ(this)
  def + (that: Nat): Nat
  def - (that: Nat): Nat
}


//Do not use standard numerical classes in this impl but implement a sub-object and a sub-class:

//One for the number zero
object Zero extends Nat {
  override def isZero: Boolean = true
  override def predecessor: Nat = throw new Exception("Not a natural number")
  override def +(that: Nat): Nat = that
  override def -(that: Nat): Nat = if (that.isZero) this else throw new Exception("Not a natural number")
}

//The other for strictly positive numbers
class Succ(n: Nat) extends Nat {
  override def isZero: Boolean = false
  override def predecessor: Nat = n
  override def +(that: Nat): Nat = new Succ((n + that))
  override def -(that: Nat): Nat = if (that.isZero) this else n-that.predecessor
}