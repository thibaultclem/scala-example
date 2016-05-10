val test = List(('a', 2), ('b', 2), ('c', 1))

val result = List()
val liste = for {
  c <- test
  nb <- 1 to c._2
} yield (c._1, nb)

type Occurrences = List[(Char, Int)]

def combinations(occurrences: Occurrences): List[Occurrences] = occurrences match {
  case List() => List(List())
  case occ :: occs => {
    for {
      othOcc <- combinations(occs)
      n <- 1 to occ._2
    } yield (occ._1, n) :: othOcc
  } ::: combinations(occs)
}

List(('a', 2), ('b', 2)).tail

combinations(List(('a', 2), ('b', 2)))

combinations(List(('b', 2)))

combinations(test)