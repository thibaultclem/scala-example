/*

In Scala, type parameters and abstract types may be constrained by a type bound.

Such type bounds limit the concrete values of the type variables and possibly
reveal more information about the members of such types.

An upper type bound T <: A declares that type variable T refers to a subtype of
type A.

 lower type bounds declare a type to be a supertype of another type.
 The term T >: A expresses that the type parameter T or the abstract type T
 refer to a supertype of type A.

It is also possible to mix a lower bound with an upper bound.

For instance,

[S >: NonEmpty <: IntSet]

would restrict S any type on the interval between NonEmpty and
IntSet.

 */

/*

The Liskov Substitution Principle

The following principle, stated by Barbara Liskov, tells us when a
type can be a subtype of another.

If A <: B, then everything one can to do with a value of
type B one should also be able to do with a value of type A.

 */


/*

Covariance

There’s another interaction between subtyping and type parameters
we need to consider. Given:

NonEmpty <: IntSet

is

List[NonEmpty] <: List[IntSet] ?

Intuitively, this makes sense: A list of non-empty sets is a special
case of a list of arbitrary sets.

We call types for which this relationship holds covariant because
their subtyping relationship varies with the type parameter.

For instance covariance make sense for List but not for Arrays.

 */