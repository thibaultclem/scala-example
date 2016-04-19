/*

List

The list is a fundamental data structure in functional programming

Example:

 */

val fruit = List("apples", "oranges", "pears")
val nums = List(1, 2, 3, 4)
val diag3 = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
val empty = List()

/*

There are two important difference between lists and arrays
- Lists are immutable - the elements of a list cannot be changed
- Lists are recursive, while arrays are flat

 */

/*

All lists are constructed from:
- the empty list Nil, and
- the construction operation :: (pronounced cons)

for example:

 */

val fruit2 = "apples" :: ("oranges" :: ("pears" :: Nil))
val nums2 = 1 :: (2 :: (3 :: (4 :: Nil)))
val empty2 = Nil

/*

We can trust omit the parenthesis as the conventions says that Operators ending
in ":" associate to the right

Examples:
 */

val nums3 = 1 :: 2 :: 3 :: 4 :: Nil
//also equivalent to
val nums4 = Nil.::(4).::(3).::(2).::(1)

