package com.cloudkitchens.controllers

import akka.actor.ActorSystem
import com.cloudkitchens.Order
import com.cloudkitchens.actor.OrderShelfActor
import com.cloudkitchens.actor.OrderShelfActor._
import javax.inject._
import play.api.libs.json.Json
import play.api.mvc._
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class OrderController @Inject()(system: ActorSystem,
                                 cc: ControllerComponents)
                               (implicit ec: ExecutionContext)
  extends AbstractController(cc) {

  implicit val orderReads   = Order.orderReads
  implicit val orderWrites  = Order.orderWrites

  implicit val timeout: Timeout = 5.seconds

  val orderShelfActor = system.actorOf(OrderShelfActor.props, "order-shelf-actor")

  def postOrder = Action.async {
    (orderShelfActor ? AddOrder).mapTo[String].map { message ⇒
      Ok(message)
    }
  }
}
