/*

Collection Hierarchy

A common base class of List and Vector is Seq, the class of all
sequences.

Seq itself is a subclass of Iterable.

 */

/*

Arrays and Strings

Arrays and Strings support the same operations as Seq and can
implicitly be converted to sequences where needed.

(They cannot be subclasses of Seq because they come from Java)
 */
val xs: Array[Int] = Array(1, 2, 3)
xs map (x => 2 * x)
val ys: String = "Hello world!"
ys filter (_.isUpper)


/*

Ranges

Another simple kind of sequence is the range.

It represents a sequence of evenly spaced integers.

Three operators:
to (inclusive), until (exclusive), by (to determine step value):
*/
val r: Range = 1 until 5
val s: Range = 1 to 5
1 to 10 by 3
6 to 1 by -2
//Ranges a represented as single objects with three fields: lower
//bound, upper bound, step value.

/*

Some more Sequence Operations:

*/

// exists: true if there is an element x of xs such that p(x) holds, false otherwise.
ys exists (c => c.isUpper)

// forall: true if p(x) holds for all elements x of xs, false otherwise.
ys forall (c => c.isUpper)

// zip: A sequence of pairs drawn from corresponding elements of sequences xs and ys.
val pair = List(1,2,3) zip ys

// unzip: Splits a sequence of pairs xs into two sequences consisting
//of the first, respectively second halves of all pairs.
pair unzip

// flatMap : Applies collection-valued function f to all elements of
//xs and concatenates the results
ys flatMap (c => List('.', c))


// sum : The sum of all elements of this numeric collection.
xs.sum

// product : The product of all elements of this numeric collection
xs.product

// max : The maximum of all elements of this collection (an Ordering must exist)
xs.max

// min : The minimum of all elements of this collection
xs.min

/*

Sorted and GroupBy

 */
val fruit = List("apples", "oranges", "pears", "banana", "pineapple")

//Sorted
fruit.sorted
//Sorted by length of words
fruit sortWith (_.length < _.length)

//GroupBy
fruit groupBy (_.head)