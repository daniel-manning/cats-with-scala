package sandbox

import cats.Functor
import cats.instances.function._
import cats.instances.list._
import cats.instances.option._
import cats.syntax.functor._
import sandbox.functor.{Branch, Leaf, Tree}     // for map

object FunctorExample extends App {

  val func1: Int => Double =
    (x: Int) => x.toDouble

  val func2: Double => Double =
    (y: Double) => y * 2

  println((func1 map func2)(1))     // composition using map
  // res7: Double = 2.0

  println((func1 andThen func2)(1)) // composition using andThen
  // res8: Double = 2.0

  println(func2(func1(1)))         // composition written out by hand
  // res9: Double = 2.0


  val func =
    ((x: Int) => x.toDouble).
      map(x => x + 1).
      map(x => x * 2).
      map(x => s"$x!")

  println(func(123))

  val list1 = List(1, 2, 3)
  // list1: List[Int] = List(1, 2, 3)

  val list2 = Functor[List].map(list1)(_ * 2)
  println(list2)
  // list2: List[Int] = List(2, 4, 6)

  val option1 = Option(123)
  // option1: Option[Int] = Some(123)

  val option2 = Functor[Option].map(option1)(_.toString)
  println(option2)
  // option2: Option[String] = Some(123)

  val funcN = (x: Int) => x + 1
  // func: Int => Int = <function1>

  val liftedFunc = Functor[Option].lift(funcN)
  // liftedFunc: Option[Int] => Option[Int] = cats.Functor$$Lambda$11698/1847181061@41c6929b

  println(liftedFunc(Option(1)))

  val funcT1 = (a: Int) => a + 1
  val funcT2 = (a: Int) => a * 2
  val funcT3 = (a: Int) => s"$a!"
  val funcT4 = funcT1.map(funcT2).map(funcT3)

  println(funcT4(123))
  // res1: String = 248!

  def doMath[F[_]](start: F[Int])
                  (implicit functor: Functor[F]): F[Int] =
    start.map(n => n + 1 * 2)

  println(doMath(Option(20)))
  // res3: Option[Int] = Some(22)

  println(doMath(List(1, 2, 3)))
  // res4: List[Int] = List(3, 4, 5)

  /*final case class Box[A](value: A)

  val box = Box[Int](123)

  box.map(value => value + 1)*/


  val tree: Tree[Int] = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))

  println(tree.map(n => n*2))
}
