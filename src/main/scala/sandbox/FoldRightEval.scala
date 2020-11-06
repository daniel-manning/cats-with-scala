package sandbox

import cats.Eval

object FoldRightEval extends App {

  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B =
    as match {
      case head :: tail =>
        fn(head, foldRight(tail, acc)(fn))
      case Nil =>
        acc
    }

  def foldRightEvaluated[A, B](as: List[A], acc: B)(fn: (A, Eval[B]) => Eval[B]): Eval[B] =
    as match {
      case head :: tail =>
        Eval.defer(fn(head, foldRightEvaluated(tail, acc)(fn)))
      case Nil =>
        Eval.now(acc)
    }

  val longList = List.fill(10000)(9)

  lazy val result = foldRight[Int, Int](longList, 0)(_ * _)

  //println(result)

  val delayedEvaluation = foldRightEvaluated[Int, Int](longList, 0) {
    (a, b) => b.map( l => l*a)
  }

  println(delayedEvaluation.value)

  println(Seq(Some(1), None, Some(2)).filter(_.isDefined).map(_.get))

}
