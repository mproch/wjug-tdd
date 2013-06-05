package pl.mproch.wjug.tdd.dbmonad

import java.sql.Connection
import language.implicitConversions

object dbmonad {

  import DBOption._

  for {
    maciek <- toDBOption(DB.getEmployee("maciek"))
    maciekManager <- toDBOption(DB.getEmployee(maciek.id))
  } yield { maciekManager.id }

}

object DB {

  def point[A](a:A) = DB(_ => a)

  def query(str:String, args : Map[String,Object]) =
    DB(conn => conn.prepareStatement(str).executeQuery())


  def getEmployee(id:String) : DB[Option[Employee]] = point(Some(Employee(id,"nameOf"+id,"m"+id)))
}

case class DB[A](action : Connection => A) {

  def unsafeRun(conn:Connection) : A =  action(conn)

  def map[B](f:A=>B) = flatMap(f andThen DB.point)

  def flatMap[B](f:A=>DB[B]) = DB[B](conn => f(action(conn)).action(conn))

}


case class Employee(id:String, name:String, managerId:String)


object DBOption {

  def point[A](value:A) = DB.point(Option(value))

  implicit def toDBOption[A](value:DB[Option[A]]) = DBOption(value)
}

case class DBOption[A](value : DB[Option[A]]) {
  def map[B](f:A=>B) : DBOption[B] = value.map(_.map(f))

  def unsafeRun(conn:Connection) = value.unsafeRun(conn)

  def flatMap[B](f:A=>DBOption[B]) : DBOption[B] =
    DB[Option[B]](conn => {
      val firstRunResult = unsafeRun(conn)
      firstRunResult.map(f).flatMap(_.unsafeRun(conn))
    })
}
