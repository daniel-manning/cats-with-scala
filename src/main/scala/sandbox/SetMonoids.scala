package sandbox

import cats.kernel.{Monoid, Semigroup}

object SetUnionMonoids extends App {
  implicit def setUnionMonoid[A]: Monoid[Set[A]] =
    new Monoid[Set[A]] {
      def combine(a: Set[A], b: Set[A]) = a union b
      def empty = Set.empty[A]
    }

  val intSet = Monoid[Set[Int]]
  println(intSet.combine(Set(1,2), Set(2,3)))
}

object SetIntersection extends App {
  implicit def setIntersectSemiGroup[A]: Semigroup[Set[A]] = new Semigroup[Set[A]] {
    override def combine(x: Set[A], y: Set[A]): Set[A] = x intersect y
  }

  val intSemigroup = Semigroup[Set[Int]]
  println(intSemigroup.combine(Set(1,2), Set(2,3)))
}

object SetSymmetricDifference extends App {
  implicit def setSymmetricDifferenceSemiGroup[A]: Semigroup[Set[A]] = new Semigroup[Set[A]] {
    override def combine(x: Set[A], y: Set[A]): Set[A] = (x diff y) union (y diff x)
  }

  val intSemigroup = Semigroup[Set[Int]]
  println(intSemigroup.combine(Set(1,2), Set(2,3)))
}