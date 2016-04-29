/*

For expressions

Higher-order functions such as map, flatMap or filter provide powerful
constructs for manipulating lists.

but sometimes the level of abstraction required by these functions make the
program difficult to understand

In this case, Scala's "for" expression notation can help

Syntax of for: for (s) yield e

Where s is a sequence of generator and filter and e is an expression whose
value is returned by an iteration

 */

//Create the class Person
case class Person(name: String, age: Int)

//Let persons be a list of elements of class Person
val persons = List(new Person("thib", 27), new Person("beboy", 15))

//To obtain the names of persons over 18 years old:
for ( p <- persons if p.age > 20 ) yield p.name

//which is equivalent to
persons filter (p => p.age > 20) map (p => p.name)

/*

The for-expression is similar to loops in imperative languages, except that it
builds a list of the results of all iterations

 */

