import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary
import forcomp._
val test = List(('a', 2), ('b', 2), ('c', 1))
val result = List()
val liste = for {
  c <- test
  nb <- 1 to c._2
} yield (c._1, nb)

val m = Map((1 -> "un"), (2 -> "deux"))
m + (3 -> "trois")
m updated (3, "trois")

List(('a', 1), ('d', 1), ('l', 1), ('r', 1)).toMap
/** A word is simply a `String`. */
type Word = String
/** A sentence is a `List` of words. */
type Sentence = List[Word]

/** `Occurrences` is a `List` of pairs of characters and positive integers saying
  *  how often the character appears.
  *  This list is sorted alphabetically w.r.t. to the character in each pair.
  *  All characters in the occurrence list are lowercase.
  *
  *  Any list of pairs of lowercase characters and their frequency which is not sorted
  *  is **not** an occurrence list.
  *
  *  Note: If the frequency of some character is zero, then that character should not be
  *  in the list.
  */
type Occurrences = List[(Char, Int)]
/** The dictionary is simply a sequence of words.
  *  It is predefined and obtained as a sequence using the utility method `loadDictionary`.
  */
val dictionary: List[Word] = loadDictionary

/** Converts the word into its character occurrence list.
  *
  *  Note: the uppercase and lowercase version of the character are treated as the
  *  same character, and are represented as a lowercase character in the occurrence list.
  *
  *  Note: you must use `groupBy` to implement this method!
  */
def wordOccurrences(w: Word): Occurrences =
  (w.toLowerCase.groupBy((c: Char) => c) map {
    case(x, y) => (x, y.length)
  } toList) sortWith(_._1 < _._1)

/** Converts a sentence into its character occurrence list. */
def sentenceOccurrences(s: Sentence): Occurrences = wordOccurrences(s mkString(""))

/** The `dictionaryByOccurrences` is a `Map` from different occurrences to a sequence of all
  *  the words that have that occurrence count.
  *  This map serves as an easy way to obtain all the anagrams of a word given its occurrence list.
  *
  *  For example, the word "eat" has the following character occurrence list:
  *
  *     `List(('a', 1), ('e', 1), ('t', 1))`
  *
  *  Incidentally, so do the words "ate" and "tea".
  *
  *  This means that the `dictionaryByOccurrences` map will contain an entry:
  *
  *    List(('a', 1), ('e', 1), ('t', 1)) -> Seq("ate", "eat", "tea")
  *
  */
lazy val dictionaryByOccurrences: Map[Occurrences, List[Word]] = dictionary groupBy((w: Word) => wordOccurrences(w))

/** Returns all the anagrams of a given word. */
def wordAnagrams(word: Word): List[Word] = dictionaryByOccurrences.get(wordOccurrences(word)) match {
  case Some(word) => word
  case None => List()
}

/** Returns the list of all subsets of the occurrence list.
  *  This includes the occurrence itself, i.e. `List(('k', 1), ('o', 1))`
  *  is a subset of `List(('k', 1), ('o', 1))`.
  *  It also include the empty subset `List()`.
  *
  *  Example: the subsets of the occurrence list `List(('a', 2), ('b', 2))` are:
  *
  *    List(
  *      List(),
  *      List(('a', 1)),
  *      List(('a', 2)),
  *      List(('b', 1)),
  *      List(('a', 1), ('b', 1)),
  *      List(('a', 2), ('b', 1)),
  *      List(('b', 2)),
  *      List(('a', 1), ('b', 2)),
  *      List(('a', 2), ('b', 2))
  *    )
  *
  *  Note that the order of the occurrence list subsets does not matter -- the subsets
  *  in the example above could have been displayed in some other order.
  */
def combinations(occurrences: Occurrences): List[Occurrences] = occurrences match {
  case List() => List(List())
  case occ :: occs => {
    for {
      othOccs <- combinations(occs)
      n <- 1 to occ._2
    } yield (occ._1, n) :: othOccs
  } ::: combinations(occs)
}

def subtract(x: Occurrences, y: Occurrences): Occurrences = y match {
  case List() => x
  case occ :: occs => {
    val xMap = x.toMap
    if(occ._2 == (xMap apply occ._1))
      subtract((xMap - occ._1).toList, occs)
    else
      subtract((xMap updated(occ._1, occ._2)).toList, occs)
  }
}

def sentenceAnagrams(sentence: Sentence): List[Sentence] = { //Robert
  if (sentence.isEmpty) List(List())
  else
    for {
      option <- combinations(sentenceOccurrences(sentence))
      endSentences <- sentenceAnagrams(List())
      word <- wordAnagrams(option map (t => t._1) mkString(""))
    } yield word :: endSentences
}
sentenceAnagrams(List("eat"))
List(('a',1), ('e',1), ('t',1)) map (t => t._1) mkString("")
wordAnagrams("eat")
List(subtract(sentenceOccurrences(List("eat")),List(('a', 1))) map (t => t._1) mkString(""))
//(sentenceAnagrams(List(subtract(sentOccurence,option) map (t => t._1) mkString(""))) map (w => word :: w))