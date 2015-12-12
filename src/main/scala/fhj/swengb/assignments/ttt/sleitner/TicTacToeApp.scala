package fhj.swengb.assignments.ttt.sleitner

/**
  * Implement here your TicTacToe JavaFX App.
  */
object TicTacToeApp{

  def main(args: Array[String]) {

    val mh: Map[TMove, Player] = Map(
      TopLeft -> PlayerA,
      TopCenter -> PlayerA,
      TopRight -> PlayerB,
      MiddleLeft -> PlayerA,
      MiddleCenter -> PlayerB,
      MiddleRight -> PlayerA,
      BottomLeft -> PlayerB,
      BottomCenter -> PlayerA,
      BottomRight -> PlayerB
    )
    println(TicTacToe.apply(mh).asString())

    val mh2: Map[TMove, Player] = Map()
    println(TicTacToe.apply(mh2).turn(TopCenter, PlayerA).asString())
    val x = TicTacToe.apply(mh2).turn(TopCenter, PlayerA).turn(BottomCenter,PlayerB).turn(MiddleRight,PlayerB)
    println(x.asString())

    /*
    val all = Seq(1, 2, 3, 4, 5, 6, 7, 8, 9).combinations(5).toList.toSeq
    val z = Seq(1, 2, 3, 4, 5, 6, 7, 8, 9)

    val zA = Seq(0, 2, 4)
    val zB = Seq(1, 3, 5, 7)

    println(zA.map(index => z(index)))
    println(zB.map(index => z(index)))
    val pA = Seq()
    println(pA)
    println(all.length)
    for (i <- all) i match {
      case one if (one.contains(1) && one.contains(2) && one.contains(3)) => println("FirstROW")
      case two if (two.contains(4) && two.contains(5) && two.contains(6)) => println("SecondROW")
      case three if (three.contains(7) && three.contains(8) && three.contains(9)) => println("ThirdROW")
      case _ => println("NOWIN")
    }
    */


  }
}
