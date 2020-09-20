package sandbox.functor

import sandbox.model.Box

trait Printable[A] {
  self =>
  def format(value: A): String

  def contramap[B](func: B => A): Printable[B] =
    new Printable[B] {
      def format(value: B): String =
        self.format(func(value))
    }
}

object PrintableExample extends App {
  def format[A](value: A)(implicit p: Printable[A]): String =
    p.format(value)

  implicit val stringPrintable: Printable[String] =
    new Printable[String] {
      def format(value: String): String =
        "\"" + value + "\""
    }

  implicit val booleanPrintable: Printable[Boolean] =
    new Printable[Boolean] {
      def format(value: Boolean): String =
        if(value) "yes" else "no"
    }

  implicit def printableBox[A](implicit p:Printable[A]): Printable[Box[A]] =
    p.contramap(boxA => boxA.value)

  println(format("hello"))
  // res3: String = "hello"

  println(format(true))

  println(format(Box("hello world")))
  // res5: String = "hello world"

  println(format(Box(true)))
  // res6: String = yes
}


