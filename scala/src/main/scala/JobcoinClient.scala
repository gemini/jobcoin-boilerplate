package com.gemini.jobcoin

import play.api.libs.ws._
import play.api.libs.ws.ahc._
import play.api.libs.ws.JsonBodyReadables._
import play.api.libs.ws.JsonBodyWritables._
import play.api.libs.json._
import play.api.libs.json.Reads._ 
import play.api.libs.functional.syntax._ 
import com.typesafe.config.Config
import akka.stream.Materializer

import scala.async.Async._
import scala.concurrent.Future

import DefaultBodyReadables._
import scala.concurrent.ExecutionContext.Implicits._

import JobcoinClient.PlaceholderResponse

class JobcoinClient(config: Config)(implicit materializer: Materializer) {
  private val wsClient = StandaloneAhcWSClient()
  private val apiAddressesUrl = config.getString("jobcoin.apiAddressesUrl")
  
  // Docs:
  // https://github.com/playframework/play-ws
  // https://www.playframework.com/documentation/2.6.x/ScalaJsonCombinators
  def testGet(): Future[PlaceholderResponse] = async {
    val response = await {
      wsClient
        .url("https://jsonplaceholder.typicode.com/posts/1")
        .get()
    }

    response
      .body[JsValue]
      .validate[PlaceholderResponse]
      .get
  }
}

object JobcoinClient {
  case class PlaceholderResponse(userId: Int, id: Int, title: String, body: String)
  object PlaceholderResponse {
    implicit val jsonReads: Reads[PlaceholderResponse] = Json.reads[PlaceholderResponse]
  }
}
