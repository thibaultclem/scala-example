/*

Lazy evaluation

Roughly Lazy means do this as later as possible and never do it twice


The proposed implementation (see streams) suffers from a serious potential
performance problem: If tail is called several times, the
corresponding stream will be recomputed each time.

This problem can be avoided by storing the result of the first
evaluation of tail and re-using the stored result instead of
recomputing tail.

This optimization is sound, since in a purely functional language an
expression produces the same result each time it is evaluated.

We call this scheme lazy evaluation (as opposed to by-name
evaluation in the case where everything is recomputed, and strict
evaluation for normal parameters and val definitions).

Haskell is a functional programming language that uses lazy
evaluation by default.

Scala uses strict evaluation by default, but allows lazy evaluation of
value definitions with the lazy val form:

lazy val x = expr

 */

//Difference between val, lazy val and def
def expr = {
  val x = { print("x"); 1 }
  lazy val y = { print("y"); 2 }
  def z = { print("z"); 3 }
  z + y + x + z + y + x
}
expr //should write "xzyz"