/*

The eight queens problem is to place eight queens on a chessboard
so that no queen is threatened by another.

▶ In other words, there can’t be two queens in the same row,
column, or diagonal.

We now develop a solution for a chessboard of any size, not just 8.

One way to solve the problem is to place a queen on each row.

Once we have placed k - 1 queens, one must place the kth queen in
a column where it’s not “in check” with any other queen on the
board.

 */

def isSafe(col: Int, queens: List[Int]): Boolean = {
  val row = queens.length
  //get a pair of position (row, column) for eqch queen
  val queensPos = (row - 1 to 0 by - 1) zip queens
  queensPos forall {
    case (r, c) => col != c && Math.abs(col - c) != row - r
  }
}

def queens(n: Int): Set[List[Int]] = {
  def placeQueens(k: Int): Set[List[Int]] = {
    if (k == 0) Set(List())
    else
      for {
        queens <- placeQueens(k - 1)
        col <- 0 until n
        if isSafe(col, queens)
      } yield col :: queens
  }
  placeQueens(n)
}

queens(4)