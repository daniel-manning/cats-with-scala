package sandbox

import cats.data.State

object PostOrderCalculator extends App {

  type CalcState[A] = State[List[Int], A]

  def evalOne(sym: String): CalcState[Int] = ???

}
