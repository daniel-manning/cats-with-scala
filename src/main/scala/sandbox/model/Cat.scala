package sandbox.model

import cats.kernel.Eq
import cats.syntax.eq._
import cats.instances.int._
import cats.instances.string._

final case class Cat(name: String, age: Int, color: String)

object Cat {

  implicit val dateEq: Eq[Cat] =
    Eq.instance[Cat] { (catOne, catTwo) =>
      catOne.age === catTwo.age &&
        catOne.color === catTwo.color &&
        catOne.name === catTwo.name
    }

}
