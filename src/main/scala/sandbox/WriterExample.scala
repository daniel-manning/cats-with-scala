package sandbox

import cats.data.Writer
import cats.instances.vector._ // for Monoid
import cats.instances.vector._   // for Monoid
import cats.syntax.applicative._ // for pure
import cats.syntax.writer._ // for tell

object WriterExample extends App {
  Writer(Vector(
    "It was the best of times",
    "it was the worst of times"
  ), 1859)

  type Logged[A] = Writer[Vector[String], A]

  println(123.pure[Logged])

  println(Vector("msg1", "msg2", "msg3").tell)

  val a = Writer(Vector("msg1", "msg2", "msg3"), 123)
  // a: cats.data.WriterT[cats.Id,scala.collection.immutable.Vector[String],Int] = WriterT((Vector(msg1, msg2, msg3),123))
println(a)

  val b = 123.writer(Vector("msg1", "msg2", "msg3"))
  // b: cats.data.Writer[scala.collection.immutable.Vector[String],Int] = WriterT((Vector(msg1, msg2, msg3),123))
  println(b)

  val aResult: Int =
    a.value
  // aResult: Int = 123
println(aResult)
  val aLog: Vector[String] =
    a.written
  // aLog: Vector[String] = Vector(msg1, msg2, msg3)
  println(aLog)

  val (log, result) = b.run


  val writer1 = for {
    a <- 10.pure[Logged]
    _ <- Vector("a", "b", "c").tell
    b <- 32.writer(Vector("x", "y", "z"))
  } yield a + b

  println(writer1)
  println(writer1.run)

  val writer2 = writer1.mapWritten(_.map(_.toUpperCase))
  // writer2: cats.data.WriterT[cats.Id,scala.collection.immutable.Vector[String],Int] = WriterT((Vector(A, B, C, X, Y, Z),42))
println(writer2)

  println(writer2.run)
  // res5: cats.Id[(scala.collection.immutable.Vector[String], Int)] = (Vector(A, B, C, X, Y, Z),42)

  val writer3 = writer1.bimap(
    log => log.map(_.toUpperCase),
    res => res * 100
  )
  // writer3: cats.data.WriterT[cats.Id,scala.collection.immutable.Vector[String],Int] = WriterT((Vector(A, B, C, X, Y, Z),4200))

  println(writer3.run)
  // res6: cats.Id[(scala.collection.immutable.Vector[String], Int)] = (Vector(A, B, C, X, Y, Z),4200)

  val writer4 = writer1.mapBoth { (log, res) =>
    val log2 = log.map(_ + "!")
    val res2 = res * 1000
    (log2, res2)
  }
  // writer4: cats.data.WriterT[cats.Id,scala.collection.immutable.Vector[String],Int] = WriterT((Vector(a!, b!, c!, x!, y!, z!),42000))

  println(writer4.run)
  // res7: cats.Id[(scala.collection.immutable.Vector[String], Int)] = (Vector(a!, b!, c!, x!, y!, z!),42000)

  val writer5 = writer1.reset
  // writer5: cats.data.WriterT[cats.Id,Vector[String],Int] = WriterT((Vector(),42))

  println(writer5.run)
  // res8: cats.Id[(Vector[String], Int)] = (Vector(),42)

  val writer6 = writer1.swap
  // writer6: cats.data.WriterT[cats.Id,Int,Vector[String]] = WriterT((42,Vector(a, b, c, x, y, z)))

  println(writer6.run)
  // res9: cats.Id[(Int, Vector[String])] = (42,Vector(a, b, c, x, y, z))
}
