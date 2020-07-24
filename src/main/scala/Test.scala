import cats.instances.int._
import cats.kernel.Eq // for Eq
import cats.syntax.eq._
import cats.instances.int._    // for Eq
import cats.instances.option._
import cats.syntax.option._

object Test extends App {

  val eqInt = Eq[Int]

  println(eqInt.eqv(123, 123))
  println(eqInt.eqv(123, 234))
  println(123 === 123)
  println(123 =!= 234)
  println(1.some =!= none[Int])


  import java.util.Date
  import cats.instances.long._ // for Eq

  implicit val dateEq: Eq[Date] =
    Eq.instance[Date] { (date1, date2) =>
      date1.getTime === date2.getTime
    }

  val x = new Date() // now
  Thread.sleep(1)
  val y = new Date() // a bit later than now

  println(x === x)
  // res13: Boolean = true

  println(x.getTime)
  println(y.getTime)
  println(s"should be false: ${x === y}")
}
