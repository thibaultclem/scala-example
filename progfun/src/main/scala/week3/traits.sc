/*

In Java, as well as in Scala, a class can only have one superclass.

But what if a class has several natural supertypes to which it conforms or
from which it wants to inherit code?

Here, you could use traits instead of abstract class:

 */

trait Planar {
  def height: Int
  def width: Int
  def surface = height * width
}

/*

Classes, objects and traits can inherit from at most one class but
arbitrary many traits.

Example:
class Square extends Shape with Planar with Movable ...

Traits resemble interfaces in Java, but are more powerful because
they can contains fields and concrete methods.

On the other hand, traits cannot have (value) parameters, only
classes can.

 */


/*

Scala’s exception handling is similar to Java’s.

The expression:
throw Exc
aborts evaluation with the exception Exc.

The type of this expression is Nothing.

 */

def error(msg: String) = throw new Error(msg)

error("test")

