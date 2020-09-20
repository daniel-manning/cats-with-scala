package sandbox.monoid

import cats.kernel.Monoid
import cats.instances.int._
import cats.instances.option._
import cats.syntax.semigroup._
import sandbox.model.Order

object SuperAdder extends App {

  def add[A: Monoid](items: List[A]): A = items.foldRight(Monoid[A].empty)(_ |+| _)


  println(add(List(Option(1),Option(2),Option(3),Option(4),Option(5))))


  println(add(List(Order(1.10, 2.8), Order(2.10, 3.2), Order(3.10, 4.4))))
}
