package pl.mproch.wjug.tdd.options


object option {

  //konstrukcja Option
  import scalaz.syntax.std.boolean._


  val res : MozeCos[String] = for {
    a <- MozeCos("aa")
    b <- Nic : MozeCos[String]
  } yield (a+b)

  def findEmployee(id:String) = (id == "ala").option(Employee("ala","dd","blah"))
  def findManager(emp:Employee) = findEmployee(emp.managerId)


}

case class Employee(id:String, name:String, managerId:String)


object MozeCos {

  def apply[A](a:A) : MozeCos[A] = if (a == null ) Nic else Cos(a)

}

sealed trait MozeCos[+A] {

  def get : A

  def flatMap[B](f : A => MozeCos[B]) : MozeCos[B]

  def map[B](f: A => B) : MozeCos[B] = flatMap( f andThen MozeCos.apply )

}

case object Nic extends MozeCos[Nothing] {
  def get = throw new RuntimeException("nie ma mnie!!")

  def flatMap[B](f: (Nothing) => MozeCos[B]) = Nic
}

case class Cos[+A](a:A) extends MozeCos[A] {
  def get = a

  def flatMap[B](f: (A) => MozeCos[B]) = f(a)
}

