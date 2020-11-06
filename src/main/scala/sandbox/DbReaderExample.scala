package sandbox

import cats.data._
import cats.implicits._
import cats._

object DbReaderExample extends App {

  case class Db(
                 usernames: Map[Int, String],
                 passwords: Map[String, String]
               )

  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] =
        Reader(_.usernames.get(userId))

  def checkPassword(
                     username: String,
                     password: String): DbReader[Boolean] =
    Reader(_.passwords(username) == password)

  def checkLogin(
                  userId: Int,
                  password: String): DbReader[Boolean] =
    for {
      username <- findUsername(userId)
      valid <- username.map(name => checkPassword(name, password))
        .getOrElse(false.pure[DbReader])
    } yield valid

  val users = Map(
    1 -> "dade",
    2 -> "kate",
    3 -> "margo"
  )

  val passwords = Map(
    "dade"  -> "zerocool",
    "kate"  -> "acidburn",
    "margo" -> "secret"
  )

  val db = Db(users, passwords)

  println(checkLogin(1, "zerocool").run(db))
  // res10: cats.Id[Boolean] = true

  println(checkLogin(4, "davinci").run(db))
  // res11: cats.Id[Boolean] = false


}
