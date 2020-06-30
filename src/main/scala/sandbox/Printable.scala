package sandbox

final case class Cat(name: String, age: Int, color: String)

trait Printable[A] {
  def format(value: A): String
}


object PrintableInstances {

  implicit val printableString = new Printable[String] {
    override def format(value: String): String = value
  }

  implicit val printableInt = new Printable[Int] {
    override def format(value: Int): String = value.toString
  }

  implicit val printableCat = new Printable[Cat] {
    override def format(cat: Cat): String = s"${cat.name} is a ${cat.age} year-old ${cat.color} cat."
  }

}

object Printable {
  def format[A](value: A)(implicit printable: Printable[A]): String = printable.format(value)
  def print[A](value: A)(implicit printable: Printable[A]): Unit = println(format(value))
}

object PrintableSyntax {
  implicit class PrintableOps[A](a: A) {
    def format(implicit printable: Printable[A]): String = printable.format(a)
    def print(implicit printable: Printable[A]): Unit = println(a.format)
  }
}


object PrintableRunner extends App {
  import PrintableInstances._

  Printable.print("Hello")
  Printable.print(12345)

  val myCat = Cat("Oscar", 4, "Ginger")

  Printable.print(myCat)
}

object PrintableRunnerSyntax extends App {
  import PrintableSyntax._
  import PrintableInstances._

  Cat("Oscar", 4, "Ginger").print
}