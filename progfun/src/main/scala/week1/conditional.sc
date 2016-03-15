//loop function (just for testing purposes)
def loop: Boolean = loop

/*

Write a function and such that for all argument expression x and y:
and(x,y) == x && y

 */
def and(x: Boolean, y: => Boolean): Boolean =
  if(x) y else false

// test and() function:
and(false, false)
and(false, true)
and(true, false)
and(true, true)
and(false, loop)

/*

Write a function and such that for all argument expression x and y:
or(x,y) == x || y

 */
def or(x: Boolean, y: => Boolean): Boolean =
  if(x) true else y

// test or() function:
or(false, false)
or(false, true)
or(true, false)
or(true, true)
or(true, loop)