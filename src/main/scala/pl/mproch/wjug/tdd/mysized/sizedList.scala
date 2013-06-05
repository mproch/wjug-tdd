package pl.mproch.wjug.tdd.mysized

object sizedList {

}

import shapeless._
import shapeless.Nat._0

sealed trait SizedList[T,Length<:Nat] {
  def +(a:T) : SizedList[T,Succ[Length]] = NotEList[T,Length](a,this)
  def head:Option[T]
}

case class NotEList[T,PLength<:Nat](hV:T,tail:SizedList[T,PLength]) extends SizedList[T,Succ[PLength]] {
  def head = Some(hV)

}

case class SizedNil[T]() extends SizedList[T,_0] {
  def head = None
}

trait NatEq[A<:Nat,B<:Nat]

object NatEq {

  implicit def zero[A<:Nat](implicit sum : Sum[A,_0]) : NatEq[sum.Out,A] = new NatEq[sum.Out, A] {}

  implicit def succ[A<:Nat,B<:Nat](implicit sum : Sum[A,Succ[B]]) : NatEq[sum.Out,A] = new NatEq[sum.Out, A] {}

  implicit def sym[A<:Nat,B<:Nat](implicit eq : NatEq[A,B]) : NatEq[B,A] = new NatEq[B, A] {}
}

object SizedList {

  def zero[T,L<:Nat](a:SizedList[T,L])(implicit w:Sum[L,_0]) : SizedList[T,w.Out] = a.asInstanceOf[SizedList[T,w.Out]]

}
