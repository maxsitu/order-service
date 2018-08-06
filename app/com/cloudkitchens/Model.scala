package com.cloudkitchens

import play.api.libs.json._

case class Order(name: String, temp: String, shelfLife: Int, decayRate: Double)

object Order {
  implicit val orderReads: Reads[Order] = Json.reads[Order]
  implicit object orderWrites extends OWrites[Order] {
    override def writes(o: Order): JsObject = Json.obj(
      "name" → o.name,
      "temp" → o.temp,
      "shelfLife" → o.shelfLife,
      "decayRate" → o.decayRate
    )
  }
}