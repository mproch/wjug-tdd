package pl.mproch.wjug.tdd.nats

object peano {

  import Peano._
  import Suma._

  compiles[_2,_2,_4]

}

object Peano {
  type _0 = PZero
  type _1 = Suc[PZero]
  type _2 = Suc[_1]
  type _3 = Suc[_2]
  type _4 = Suc[_3]
}

sealed trait Peano

case class PZero() extends Peano

case class Suc[A<:Peano]() extends Peano

trait Suma[A<:Peano,B<:Peano,Result<:Peano]

object Suma {

  implicit def sumZero[A<:Peano] : Suma[A,PZero,A] = new Suma[A,PZero,A]{}

  implicit def sumSucc[A<:Peano,B<:Peano,C<:Peano]
    (implicit psum:Suma[A,B,C]) : Suma[A,Suc[B],Suc[C]] = new Suma[A,Suc[B],Suc[C]]{}

  def compiles[A<:Peano,B<:Peano,C<:Peano](implicit a : Suma[A,B,C])  {}

}

