/*

Streams are similar to lists, but their tail is evaluated only on
demand:

"Avoid computing the tail of a sequence until it is needed
for the evaluation result (which might be never)"

The other elements are only computed when they are needed,
where “needed” means that someone calls tail on the stream.

 */
// Streams are defined from a constant Stream.empty and a constructor Stream.cons. :
val xs = Stream.cons(1, Stream.cons(2, Stream.empty))

//They can also be defined like the other collections by using the object Stream
// as a factory:
Stream(1, 2, 3)
//The toStream method on a collection will turn the collection into a stream:
(1 to 1000).toStream

/*

Stream Ranges

Let’s try to write a function that returns (lo until hi).toStream
directly:

*/

def streamRange(lo: Int, hi: Int): Stream[Int] =
if (lo >= hi) Stream.empty
else Stream.cons(lo, streamRange(lo + 1, hi))

//Compare to the same function that produces a list:
def listRange(lo: Int, hi: Int): List[Int] =
if (lo >= hi) Nil
else lo :: listRange(lo + 1, hi)


/*

Stream supports almost all methods of List.

The one major exception is ::.

x :: xs always produces a list, never a stream.

There is however an alternative operator #:: which produces a stream:

x #:: xs == Stream.cons(x, xs)

#:: can be used in expressions as well as patterns.

 */


/*

Infinite streams

we saw that all elements of a stream except the first one are
computed only when they are needed to produce a result.

This opens up the possibility to define infinite streams!

For instance, here is the stream of all integers starting from a given
number:
 */
def from(n: Int): Stream[Int] = n #:: from(n+1)
//The stream of all natural numbers:
val nats = from(0)

//The stream of all multipes of 4:
val m4s = nats map (_ * 4)

(m4s take 1000).toList