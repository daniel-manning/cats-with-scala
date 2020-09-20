package sandbox.monoid

import cats.Monoid
import cats.instances.string._ // for Monoid
import cats.Semigroup
import cats.Monoid
import cats.instances.int._ // for Monoid
import cats.Monoid
import cats.instances.int._    // for Monoid
import cats.instances.option._ // for Monoid
import cats.instances.string._ // for Monoid
import cats.syntax.semigroup._ // for |+|
import cats.instances.int._ // for Monoid

object MonoidInstances extends App {

  println(Semigroup[String].combine("Hi ", "there"))

  println(Monoid[String].combine("Hi ", "there"))
  // res0: String = Hi there

  println(Monoid[String].empty)
  // res1: String = ""

  println(Monoid[Int].combine(32, 10))

  val a = Option(22)
  // a: Option[Int] = Some(22)

  val b = Option(20)
  // b: Option[Int] = Some(20)

  println(Monoid[Option[Int]].combine(a, b))

  val stringResult = "Hi " |+| "there" |+| Monoid[String].empty
  // stringResult: String = Hi there
  println(stringResult)

  val intResult = 1 |+| 2 |+| Monoid[Int].empty
  println(intResult)
}
