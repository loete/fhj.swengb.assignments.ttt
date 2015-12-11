package fhj.swengb.assignments.ttt.sleitner

/**
  * Implement here your TicTacToe JavaFX App.
  */
object TicTacToeApp{

  def main(args: Array[String]) {

    val mh:Map[TMove,Player] = Map()
    println(TicTacToe.apply(mh).asString())

    val mh2:Map[TMove,Player] = Map(TopLeft->PlayerA,TopRight->PlayerB)
    println(TicTacToe.apply(mh2).asString())


    for((k,v) <- mh2) if (v==PlayerA) println("X") else println("O")
  }
}
