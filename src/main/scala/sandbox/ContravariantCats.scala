package sandbox

import cats.Contravariant
import cats.Show
import cats.instances.string._
import cats.syntax.contravariant._ // for contramap

object ContravariantCats extends App {
  val showString = Show[String]

  val showSymbol: Show[Symbol] = Contravariant[Show].
    contramap(showString)((sym: Symbol) => s"'${sym.name}")

  println(showSymbol.show(Symbol("dave")))

  println(showString.contramap[Symbol](_.name).show(Symbol("dave")))
}
