package pl.mproch.wjug.tdd.sized

import shapeless._
import shapeless.Nat._
import shapeless.Sized._

object sized {


  val list = Sized("ala", "ma", "kota")

  list.take[_2]

  wrap(List("ala","bela", "cela"))

  //list.take[_5]


}
