/**
  * Created by thibaultclement on 25/04/16.
  */

/*

We have seen that lists are linear: Access to the first element is
much faster than access to the middle or end of a list.

The Scala library also defines an alternative sequence
implementation, Vector.

This one has more evenly balanced access patterns than List.

Vectors are created analogously to lists:

 */

val nums = Vector(1, 2, 3, -88)
val people = Vector("Bob", "James", "Peter")

/*

They support the same operations as lists, with the exception of ::
  Instead of x :: xs, there is

  x +: xs Create a new vector with leading element x, followed
by all elements of xs.

  xs :+ x Create a new vector with trailing element x, preceded
by all elements of xs.

(Note that the : always points to the sequence.)

 */

23 +: nums
people :+ "Thib"

/*

Vector or List ?

If you want to access mainly the head (recursive function for instance) use List
Otherwise use Vector as it more efficient than List

 */