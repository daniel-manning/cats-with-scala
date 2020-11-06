package sandbox

import cats._
import cats.data.Writer
import cats.implicits._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._

object FactorialWriter extends App {

  type Logged[A] = Writer[Vector[String], A]
  def slowly[A](body: => A) =
    try body finally Thread.sleep(100)

  def factorial(n: Int): Logged[Int] =
    for {
      ans  <- if(n == 0) 1.pure[Logged]
      else slowly(factorial(n - 1).map(_ * n))
      _ <- Vector(s"fact $n $ans").tell
    } yield ans

  lazy val thing = Await.result(Future.sequence(Vector(
    Future(factorial(3)),
    Future(factorial(3))
  )), 5.seconds)

println(thing.map(_.run))

}
