package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
	trait TestTrees {
		val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
		val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
    val table: List[(Char, List[Bit])] = List(('a', List(0)),('b', List(1,0)),('c', List(1,1)))
	}


  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }


  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }


  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("insert one more b") {
    assert(insertPair('b',List(('a', 2), ('b', 1))) === List(('a', 2), ('b', 2)))
  }

  test("insert a new char c") {
    assert(insertPair('c',List(('a', 2), ('b', 1))) === List(('a', 2), ('b', 1), ('c', 1)))
  }

  test("times") {
    assert(times(List('a', 'b', 'a')) === List(('a', 2), ('b', 1)))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("singleton with empty list") {
    assert(singleton(List()) === false)
  }

  test("singleton with singleton") {
    assert(singleton(List(Leaf('a', 1))) === true)
  }

  test("singleton with non empty and non singleton list") {
    assert(singleton(List(Leaf('a', 1),Leaf('b', 1))) === false)
  }

  test("combine of two leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2))
    assert(combine(leaflist) === List(Fork(Leaf('t',2),Leaf('e',1),List('t', 'e'),3)))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('t',2),Leaf('e',1),List('t', 'e'),3), Leaf('x',4)))
  }

  test("until") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(until(singleton,combine)(leaflist) === Fork(Leaf('x',4),Fork(Leaf('t',2),Leaf('e',1),List('t', 'e'),3), List('x', 't', 'e'), 7))
  }

  test("createCodeTree") {
    val text = "abbaea"
    assert(createCodeTree(string2Chars(text)) === Fork(Leaf('a',3),Fork(Leaf('b',2),Leaf('e',1),List('b', 'e'),3), List('a', 'b', 'e'), 6))
  }

  test("decode french secret") {
    assert(decodedSecret.mkString === "huffmanestcool")
  }

  test("encode") {
    new TestTrees {
      assert(encode(t1)(string2Chars("aab")) === List(0,0,1))
    }
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("convert") {
    new TestTrees {
      assert(convert(createCodeTree(string2Chars("bab"))) === List(('b', List(0)),('a', List(1))))
    }
  }

  test("codeBits") {
    new TestTrees {
      assert(codeBits(table)('c') === List(1,1))
    }
  }

  test("quick encode") {
    new TestTrees {
      assert(quickEncode(t1)(string2Chars("aab")) === List(0,0,1))
    }
  }

}
