package sandbox
import cats.Monad
import cats.instances.option._
import cats.instances.list._
import cats.instances.option._ // for Monad
import cats.instances.vector._ // for Monad
import cats.instances.future._

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt

object MonadExamples extends App {

  val opt1 = Monad[Option].pure(3)
  println(opt1)
  // opt1: Option[Int] = Some(3)

  val opt2 = Monad[Option].flatMap(opt1)(a => Some(a + 2))
  println(opt2)
  // opt2: Option[Int] = Some(5)

  val opt3 = Monad[Option].map(opt2)(a => 100 * a)
  println(opt3)
  // opt3: Option[Int] = Some(500)

  val list1 = Monad[List].pure(3)
  println(list1)
  // list1: List[Int] = List(3)

  val list2 = Monad[List].
    flatMap(List(1, 2, 3))(a => List(a, a*10))
  println(list2)
  // list2: List[Int] = List(1, 10, 2, 20, 3, 30)

  val list3 = Monad[List].map(list2)(a => a + 123)
  println(list3)
  // list3: List[Int] = List(124, 133, 125, 143, 126, 153)

  println(Monad[Option].flatMap(Option(1))(a => Option(a*2)))
  // res0: Option[Int] = Some(2)

  import cats.instances.list._ // for Monad

  println(Monad[List].flatMap(List(1, 2, 3))(a => List(a, a*10)))
  // res1: List[Int] = List(1, 10, 2, 20, 3, 30)

  println(Monad[Vector].flatMap(Vector(1, 2, 3))(a => Vector(a, a*10)))
  // res2: Vector[Int] = Vector(1, 10, 2, 20, 3, 30)


  val fm = Monad[Future]
  val future = fm.flatMap(fm.pure(1))(x => fm.pure(x + 2))

  println(Await.result(future, 1.second))
}
