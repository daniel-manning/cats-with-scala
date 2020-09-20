package sandbox

import cats.Monad
import cats.syntax.functor._ // for map
import cats.syntax.flatMap._ // for flatMap
import cats.instances.option._ // for Monad
import cats.instances.list._   // for Monad


object SumSquareMonad extends App {

  def sumSquare[F[_]: Monad](a: F[Int], b: F[Int]): F[Int] =
    for {
      x <- a
      y <- b
    } yield x*x + y*y


  println(sumSquare(Option(3), Option(4)))
  // res8: Option[Int] = Some(25)

  println(sumSquare(List(1, 2, 3), List(4, 5)))
  // res9: List[Int] = List(17, 26, 20, 29, 25, 34)
}
