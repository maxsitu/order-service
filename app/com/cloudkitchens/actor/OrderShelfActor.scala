package com.cloudkitchens.actor

import akka.actor.{Actor, Props}
import com.cloudkitchens.Order

import scala.collection.mutable

object OrderShelfActor {
  def props = Props[OrderShelfActor]

  case class AddOrder(time: Long, order: Order)
  case class RemoveTime(time: Long)
  case class RemoveOrder(time: Long)
}

class OrderShelfActor extends Actor {

  implicit val ord : Ordering[Order] = Ordering.by[Order, Double](_.decayRate)
  val map = mutable.Map[Long, mutable.PriorityQueue[Order]]()

  import OrderShelfActor._

  override def receive: Receive = {
    case AddOrder ⇒ sender() ! "added"
    case RemoveTime(time) ⇒
      map.remove(time)
    case RemoveOrder(time: Long) ⇒
      sender() ! "Done"
  }
}
