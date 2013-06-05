package pl.mproch.wjug.tdd.validation

import scalaz._
import scalaz.syntax.all._
import scalaz.std.AllInstances._

object validation  extends App {

  type StringNelValidation[E] = ValidationNel[String,E]

  def validateName(name:String)
    = if (name.length < 2) "Too short".failNel[String] else name.successNel[String]

  def validateAge(age:Int) = if (age < 10) "Too young".failNel[Int] else age.successNel[String]

  def parsePerson(name:String, age:Int, phone:String) = {
    (validateName(name) |@| validateAge(age) |@| phone.successNel[String])(Person.apply _)
  }

  println (parsePerson("b",2,"tt"))
}

case class Person(name:String, age:Int, phone:String)


