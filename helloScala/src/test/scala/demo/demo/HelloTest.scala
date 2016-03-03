package demo.demo

import org.scalatest.FunSuite

/**
  * Created by thibaultclement on 02/03/16.
  */
class HelloTest extends FunSuite {

  test("sayHello method works correctly") {
    val hello = new Hello
    assert(hello.sayHello("Scala") == "Hello, Scala!")
  }

}
