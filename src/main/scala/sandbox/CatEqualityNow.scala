package sandbox

import sandbox.model.Cat
import cats.syntax.eq._
import cats.instances.option._

object CatEqualityNow extends App {
  val cat1 = Cat("Garfield",   38, "orange and black")
  val cat2 = Cat("Heathcliff", 33, "orange and black")

  val optionCat1 = Option(cat1)
  val optionCat2 = Option.empty[Cat]

  println(s"Cat 1 is equal to itself: ${cat1 === cat1}")
  println(s"Cat 1 is equal to cat2: ${cat1 === cat2}")
  println(s"Cat 1 is not equal to cat2: ${cat1 =!= cat2}")

  println(s"Option Cat 1 is equal to itself: ${optionCat1 === optionCat1}")
  println(s"Option Cat 1 is equal to option cat 2: ${optionCat1 === optionCat2}")
  println(s"Option Cat 1 is not equal to option cat 2: ${optionCat1 =!= optionCat2}")
}
