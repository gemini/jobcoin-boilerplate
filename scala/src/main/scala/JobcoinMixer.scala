package com.gemini.jobcoin

import java.util.UUID

import scala.io.StdIn
import com.typesafe.config.ConfigFactory
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import scala.concurrent.ExecutionContext.Implicits._

object JobcoinMixer {
  object CompletedException extends Exception { }

  def main(args: Array[String]): Unit = {
    // Create an actor system
    implicit val actorSystem = ActorSystem()
    implicit val materializer = ActorMaterializer()

    // Load Config
    val config = ConfigFactory.load()

    // Test HTTP client
    // val client = new JobcoinClient(config)
    // client.testGet().map(response => println(s"Response:\n$response"))

    try {
      while (true) {
        println(prompt)
        val line = StdIn.readLine()

        if (line == "quit") throw CompletedException
        
        val addresses = line.split(",")
        if (line == "") {
          println(s"You must specify empty addresses to mix into!\n$helpText")
        } else {
          val depositAddress = UUID.randomUUID()
          println(s"You may now send Jobcoins to address $depositAddress. They will be mixed and sent to your destination addresses.")
        }
      }
    } catch {
      case CompletedException => println("Quitting...")
    } finally {
      actorSystem.terminate()
    }
  }

  val prompt: String = "Please enter a comma-separated list of new, unused Jobcoin addresses where your mixed Jobcoins will be sent."
  val helpText: String =
    """
      |Jobcoin Mixer
      |
      |Takes in at least one return address as parameters (where to send coins after mixing). Returns a deposit address to send coins to.
      |
      |Usage:
      |    run return_addresses...
    """.stripMargin
}
