package pl.mproch.wjug.tdd.valueobjects


import scalaz._
case class Client(id:String, name:String)

object equalities {

  import scalaz.syntax.all._

  val clientId = "13"

  val client = Client("13","Maciek")

  val client2 = Client("24","Pola")

  if (clientId == client) {
    println("haha, to ja!!")
  }


  implicit object ClientEqual extends Equal[Client] {
    def equal(a1: Client, a2: Client) = a1.id == a2.id
  }

  //if (clientId === client.id) {
  //  println("Ha ha, to ja")
  //}

  trait ByName
  trait ById

  implicit object ClientNameEqual extends Equal[Client @@ ByName] {
    def equal(a1: scalaz.@@[Client, ByName], a2: scalaz.@@[Client, ByName]) = a1.name == a2.name
  }

  if (client === client2) {
    println("ja to pola??")
  }

  if (Tag[Client,ByName](client) === Tag[Client,ByName](client2)) {
    println("hahahaha!!!!")
  }

}

object taggedtypes {

  type ClientId = String @@ Client

  val clientId : ClientId = Tag[String,Client]("13")

  val cal  = clientId +" bal"

  val repo : Option[ClientRepo] = None
  repo.map(_.load(clientId))

  //repo.map(_.load("13"))

  val a = clientId.substring(1)

  trait ClientRepo {
    def load(clientId : ClientId)
  }

}
