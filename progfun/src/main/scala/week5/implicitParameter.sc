/*

Making Sort more General

Problem: How to parameterize msort so that it can also be used for
lists with elements other than Int?

def msort[T](xs: List[T]): List[T] = ...

does not work, because the comparison < in merge is not defined for
arbitrary types T.

 */

/*

Parameterization of Sort

The most flexible design is to make the function sort polymorphic
and to pass the comparison operation as an additional parameter:

def msort[T](xs: List[T])(lt: (T, T) => Boolean) = {
  ...
  merge(msort(fst)(lt), msort(snd)(lt))
}

Merge then needs to be adapted as follows:

def merge(xs: List[T], ys: List[T]) = (xs, ys) match {
  ...
  case (x :: xs1, y :: ys1) =>
    if (lt(x, y)) ...
    else ...
}

 */

def msort[T](xs: List[T])(lt: (T, T) => Boolean): List[T] = {
  val n = xs.length/2
  if (n == 0) xs
  else {
    def merge(xs: List[T], ys: List[T]): List[T] =
      (xs, ys) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs1 , y :: ys1) => {
          if (lt(x,y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
        }
      }
    val (fst, snd) = xs splitAt n
    merge(msort(fst)(lt), msort(snd)(lt))
  }
}

//Sort Int list
val nums = List(1, -4, 7, 13, 5)
msort(nums)((x: Int, y: Int) => x < y)

//Sort String list
val fruit = List("apples", "oranges", "pears", "banana")
msort(fruit)((x: String, y: String) => x.compareTo(y) < 0)

//We can remove type parameter:
msort(nums)((x, y) => x < y)
msort(fruit)((x, y) => x.compareTo(y) < 0)

/*

There is already a class in the standard library that represents
orderings.

scala.math.Ordering[T]

provides ways to compare elements of type T. So instead of
parameterizing with the lt operation directly, we could parameterize
with Ordering instead:

def msort[T](xs: List[T])(ord: Ordering) =
def merge(xs: List[T], ys: List[T]) =
... if (ord.lt(x, y)) ...
... merge(msort(fst)(ord), msort(snd)(ord)) ...

 */

def msort2[T](xs: List[T])(ord: Ordering[T]): List[T] = {
  val n = xs.length/2
  if (n == 0) xs
  else {
    def merge(xs: List[T], ys: List[T]): List[T] =
      (xs, ys) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs1 , y :: ys1) => {
          if (ord.lt(x,y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
        }
      }
    val (fst, snd) = xs splitAt n
    merge(msort2(fst)(ord), msort2(snd)(ord))
  }
}

msort2(nums)(Ordering.Int)
msort2(fruit)(Ordering.String)


/*

Implicit Parameters

Problem: Passing around lt or ord values is cumbersome.

We can avoid this by making ord an implicit parameter.

 */

def msort3[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
  val n = xs.length/2
  if (n == 0) xs
  else {
    def merge(xs: List[T], ys: List[T]): List[T] =
      (xs, ys) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs1 , y :: ys1) => {
          if (ord.lt(x,y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
        }
      }
    val (fst, snd) = xs splitAt n
    merge(msort3(fst), msort3(snd))
  }
}

//Then calls to msort can avoid the ordering parameters:
msort3(nums)
msort3(fruit)
//The compiler will figure out the right implicit to pass based on the demanded type.