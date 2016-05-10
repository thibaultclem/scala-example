/*

Map

Map[key,value] is a data structure that associates keys with values

Examples:

 */

val romanNumerals = Map("I" -> 1, "V" -> 5, "X" -> 10)
val capitalOfCountry = Map("US" -> "Washington", "Switzerland" -> "Bern")

// Map extends the collection type Iterable[(Key,Value)]
// So Map support the same collection operations as others iterables:
val countryOfCapital = capitalOfCountry map {
  case(x, y) => (y, x)
}

// Map also extends the function type key => value
capitalOfCountry("US")

// Get method: Query without knowing if the map contains the given key
capitalOfCountry get "US"
capitalOfCountry get "France"
//Get return an Option Type

//Options are as classes so they can be decomposed using pattern matching:
def showCapital(country: String) = capitalOfCountry.get(country) match {
  case Some(capital) => capital
  case None => "missing data"
}
showCapital("US")
showCapital("Andorra")

//Default Value
//Map can be turn to a total function thanks to default value
//Avoid to lead to an exception if the key was not stored in the map
val cap1 = capitalOfCountry withDefaultValue "<Unknown>"
cap1("Andorra")

/*

Exercise with polynomial

(Notice the constructor with arbitrary numbers of arguments)

 */

class Poly(terms0: Map[Int, Double]) {

  def this(bindings: (Int, Double)*) = this(bindings.toMap)

  val terms = terms0 withDefaultValue 0.0

  def + (other: Poly) = new Poly(terms ++ (other.terms map adjust))

  def adjust(term: (Int, Double)): (Int, Double) = {
    val (exp, coeff) = term
    exp -> (coeff + terms(exp))
  }

  override def toString =
    (for ((exp, coeff) <- terms.toList.sorted.reverse)
      yield coeff+"x^"+exp) mkString " + "
}

val p1 = new Poly(1 -> 2.0, 3 -> 4.0, 5 -> 6.2)
val p2 = new Poly(0 -> 3.0, 3 -> 7.0)
p1 + p2
p1. terms(2)