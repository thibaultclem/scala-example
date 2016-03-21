/*

Evaluation and Operator

Operators can be used as identifiers.

Thus, an identifier can be:

▶ Alphanumeric: starting with a letter, followed by a sequence of
letters or numbers

▶ Symbolic: starting with an operator symbol, followed by other
operator symbols.

▶ The underscore character ’_’ counts as a letter.

▶ Alphanumeric identifiers can also end in an underscore,
followed by some operator symbols.

Examples of identifiers:
x1 * +?%& vector_++ counter_=

 */

class Rational(x: Int, y: Int) {

  def numer = x
  def denom = y

  def + (r: Rational) =
    new Rational(
      numer * r.denom + r.numer * denom,
      denom * r.denom
    )

  // unary_- is a special operator in Scala that means 'negative'
  // (to differentiate from the 'sub')
  def unary_- =
    new Rational(-numer, denom)

  def - (r: Rational) =
    this + -r

  def < (r: Rational) = numer * r.denom < r.numer * denom

  def max(r: Rational) = if(this < r) r else this

  def min(r: Rational) = if (this < r) this else r

  //We can override methods
  override def toString = numer + "/" + denom

}

val a = new Rational(1,2)
val b = new Rational(2,3)

// Now I can use the operator on my Relational Class
a + b
a - b

/*

The precedence of an operator is determined by its first character.

The following table lists the characters in increasing order of priority
precedence:

(all letters)
|
^
&
< >
= !
:
+ -
* / %
(all other special characters)

a + b ^? c ?^ d less a ==> b | c
is the same as:
((a + b) ^? (c ?^ d)) less ((a ==> b) | c)

 */