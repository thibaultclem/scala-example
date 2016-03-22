val t1 = new NonEmpty(3, new Empty, new Empty)
val t2 = t1 incl 4

/*

An abstract class representing a Set of Integers
 */
abstract class IntSet {
  def contains(x: Int): Boolean
  def incl(x: Int): IntSet
  def union(other: IntSet): IntSet
}

/*

Let’s consider implementing sets as binary trees.

There are two types of possible trees: a tree for the empty set, and
a tree consisting of an integer and two sub-trees.

 */

//Tree for empty set
class Empty extends IntSet {

  def contains(x: Int): Boolean = false

  def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)

  def union(other: IntSet): IntSet = other

  override def toString = "."
}
//Tree consisting of an integer and two sub-trees
class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {

  def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true

  def incl(x: Int): IntSet =
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this


  def union(other: IntSet): IntSet = {
    ((left union right) union other) incl elem
  }

  override def toString = "{" + left + elem + right + "}"

}

/*

In the IntSet example, one could argue that there is really only a
single empty IntSet.

So it seems overkill to have the user create many instances of it.

We can express this case better with an object definition:

This defines a singleton object named Empty.

No other Empty instances can be (or need to be) created.

Singleton objects are values, so Empty evaluates to itself.

 */

/*
object EmptySingleton extends IntSet {

  def contains(x: Int): Boolean = false

  def incl(x: Int): IntSet = new NonEmpty(x, new EmptySingleton, new EmptySingleton)

  override def toString = "."

}*/