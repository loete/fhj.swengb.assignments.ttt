package fhj.swengb.assignments.ttt.sleitner

import scala.collection.Set

/**
  * models the different moves the game allows
  *
  * each move is made by either player a or player b.
  */
sealed trait TMove {
  def idx: Int
}

case object TopLeft extends TMove {
  override def idx: Int = 0
}

case object TopCenter extends TMove {
  override def idx: Int = 1
}

case object TopRight extends TMove {
  override def idx: Int = 2
}

case object MiddleLeft extends TMove {
  override def idx: Int = 3
}

case object MiddleCenter extends TMove {
  override def idx: Int = 4
}

case object MiddleRight extends TMove {
  override def idx: Int = 5
}

case object BottomLeft extends TMove {
  override def idx: Int = 6
}

case object BottomCenter extends TMove {
  override def idx: Int = 7
}

case object BottomRight extends TMove {
  override def idx: Int = 8
}


/**
  * for a tic tac toe game, there are two players, player A and player B
  */
sealed trait Player

case object PlayerA extends Player

case object PlayerB extends Player

case object Draw  extends Player

object TicTacToe {

  /**
    * creates an empty tic tac toe game
    *
    * @return
    */
  def apply(): TicTacToe = TicTacToe(Map())

  /**
    * For a given tic tac toe game, this function applies all moves to the game.
    * The first element of the sequence is also the first move.
    *
    * @param t
    * @param moves
    * @return
    */
  def play(t: TicTacToe, moves: Seq[TMove]): TicTacToe = ???

  /**
    * creates all possible games.
    *
    * @return
    */
  def mkGames(): Map[Seq[TMove], TicTacToe] = ???

}

/**
  * Models the well known tic tac toe game.
  *
  * The map holds the information which player controls which field.
  *
  * The nextplayer parameter defines which player makes the next move.
  */
case class TicTacToe(moveHistory: Map[TMove, Player],
                     nextPlayer: Player = PlayerA) {

  /**
    * outputs a representation of the tic tac toe like this:
    *
    * |---|---|---|
    * | x | o | x |
    * |---|---|---|
    * | o | x | x |
    * |---|---|---|
    * | x | o | o |
    * |---|---|---|
    *
    * @return
    */
  def asString(): String = {

    def setOrNot(m: TMove): String = moveHistory match {
      case not if !moveHistory.contains(m) => "   "
      case x if x.apply(m)==PlayerA => " X "
      case o if o.apply(m)==PlayerB => " O "
    }

    "|---|---|---|\n" +
    "|"+setOrNot(TopLeft)+"|"+setOrNot(TopCenter)+"|"+setOrNot(TopRight)+"|\n" +
    "|---|---|---|\n" +
    "|"+setOrNot(MiddleLeft)+"|"+setOrNot(MiddleCenter)+"|"+setOrNot(MiddleRight)+"|\n" +
    "|---|---|---|\n" +
    "|"+setOrNot(BottomLeft)+"|"+setOrNot(BottomCenter)+"|"+setOrNot(BottomRight)+"|\n" +
    "|---|---|---|"
  }

  /**
    * is true if the game is over.
    *
    * The game is over if either of a player wins or there is a draw.
    */
  val gameOver : Boolean = if(winner != None) true else false

  /**
    * the moves which are still to be played on this tic tac toe.
    */
  val remainingMoves: Set[TMove] = {
    val allMoves:Set[TMove] = Set(TopLeft,TopCenter,TopRight,MiddleLeft,MiddleCenter,MiddleRight,BottomLeft,BottomCenter,BottomRight)
    allMoves diff moveHistory.keySet
  }

  /**
    * given a tic tac toe game, this function returns all
    * games which can be derived by making the next turn. that means one of the
    * possible turns is taken and added to the set.
    */
  lazy val nextGames: Set[TicTacToe] = ???

  /**
    * Either there is no winner, or PlayerA or PlayerB won the game.
    *
    * The set of moves contains all moves which contributed to the result.
    */
  def winner: Option[(Player, Set[TMove])] = {

    def checkTicTacToe(pMoves: Set[TMove]): Boolean = pMoves match {
      case r1 if r1.contains(TopLeft) && r1.contains(TopCenter) && r1.contains(TopRight) => true
      case r2 if r2.contains(MiddleLeft) && r2.contains(MiddleCenter) && r2.contains(MiddleRight) => true
      case r3 if r3.contains(BottomLeft) && r3.contains(BottomCenter) && r3.contains(BottomRight) => true
      case c1 if c1.contains(TopLeft) && c1.contains(MiddleLeft) && c1.contains(BottomLeft) => true
      case c2 if c2.contains(TopCenter) && c2.contains(MiddleCenter) && c2.contains(BottomCenter) => true
      case c3 if c3.contains(TopRight) && c3.contains(MiddleRight) && c3.contains(BottomRight) => true
      case d1 if d1.contains(TopLeft) && d1.contains(MiddleCenter) && d1.contains(BottomRight) => true
      case d2 if d2.contains(TopRight) && d2.contains(MiddleCenter) && d2.contains(BottomLeft) => true
      case _ => false
    }
    val Amoves = moveHistory.filter(_._2==PlayerA).keySet
    val Bmoves = moveHistory.filter(_._2==PlayerB).keySet

    if(checkTicTacToe(Amoves)) Some(PlayerA,Amoves)
    else if(checkTicTacToe(Bmoves)) Some(PlayerB,Bmoves)
    else if(moveHistory.size == 9) Some(Draw, moveHistory.keySet)
    else None

  }

  /**
    * returns a copy of the current game, but with the move applied to the tic tac toe game.
    *
    * @param p to be played
    * @param player the player
    * @return
    */
  def turn(p: TMove, player: Player): TicTacToe = {
    if(player==PlayerA) {
      TicTacToe(moveHistory.updated(p,player),PlayerB)
    } else {
      TicTacToe(moveHistory.updated(p,player),PlayerA)
    }
  }


}