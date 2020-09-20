package sandbox

import cats.Monoid
import cats.instances.string._ // for Monoid
import cats.syntax.invariant._ // for imap
import cats.syntax.semigroup._ // for |+|

object SymbolMonoid extends App {
  implicit val symbolMonoid: Monoid[Symbol] =
    Monoid[String].imap(Symbol.apply)(_.name)

  println(Monoid[Symbol].empty)
  // res5: Symbol = '

  println(Symbol("a") |+| Symbol("few") |+| Symbol("words"))
}
