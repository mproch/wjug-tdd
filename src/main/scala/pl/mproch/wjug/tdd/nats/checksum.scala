package pl.mproch.wjug.tdd.nats

import shapeless._
import shapeless.Nat._

object checksum {


  def isSum[L<:HList,S<:Nat](l:L,s:S)(implicit sumProof: Summed[L,S]) {}

  trait Summed[H <:HList, S<:Nat]

  implicit object NilSummed extends Summed[HNil, _0]

  implicit def pSum[H<:Nat,T<:HList,PS<:Nat,S<:Nat](implicit partial: Summed[T, PS], sum: SumAux[H, PS, S])
    = new Summed[H :: T, S] {}

  //isSum(_8::_4 :: HNil, _10)

  isSum(_8::_4 :: HNil, _12)

}
