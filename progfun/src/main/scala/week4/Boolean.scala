package idealized.scala

/**
  * Created by thibaultclement on 18/04/16.
  */
abstract class Boolean {

  def ifThenElse[T](t: => T, e: => T): T

  def && (x: => scala.Boolean): scala.Boolean = ifThenElse(x, false)

  def || (x: => scala.Boolean): scala.Boolean = ifThenElse(true, x)

  def unary_!: = ifThenElse(false, true)

  def == (x: scala.Boolean): scala.Boolean = ifThenElse(x, x.unary_!)

  def != (x: scala.Boolean): scala.Boolean = ifThenElse(x.unary_!, x)

  def < (x: scala.Boolean) = ifThenElse(false, x)
}

object true extends Boolean {
  def ifThenElse[T](t: => T, e: => T) = t
}

  object false extends Boolean {
  def ifThenElse[T](t: => T, e: => T) = e
}