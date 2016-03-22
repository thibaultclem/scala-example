//Named imports of one class
import week3.Rational

//Named Import of any classs in the package with '_'
import week3._

//Wildcard Import of two classes
import week3.{Rational,Hello}

new Rational()

//You can import from either a package or an object

/*

Some entities are automatically imported in any Scala program.

These are:
▶ All members of package scala
▶ All members of package java.lang
▶ All members of the singleton object scala.Predef.

 */