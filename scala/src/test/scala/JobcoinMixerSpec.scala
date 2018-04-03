package com.gemini.jobcoin

import org.scalatest._
import java.io.{ByteArrayOutputStream, ByteArrayInputStream}
import java.nio.charset.StandardCharsets

class MixerTests extends FlatSpec with Matchers {
  "Main method" should "print the help text when no args are given" in {
    val inputText = "\nquit\n"
    val inputStream = new ByteArrayInputStream(inputText.getBytes(StandardCharsets.UTF_8))
    val outCapture = new ByteArrayOutputStream
    Console.withIn(inputStream) {
      Console.withOut(outCapture) {
        JobcoinMixer.main(Array[String]())
      }
    }

    val expectedOutput = s"""${JobcoinMixer.prompt}
    |You must specify empty addresses to mix into!
    |${JobcoinMixer.helpText}
    |${JobcoinMixer.prompt}
    |Quitting...
    |""".stripMargin

    outCapture.toString should be (expectedOutput)
  }

  "Main method" should "print the deposit address when args are given" in {
    val inputText = "test1,test2,test3\nquit\n"
    val inputStream = new ByteArrayInputStream(inputText.getBytes(StandardCharsets.UTF_8))
    val outCapture = new ByteArrayOutputStream
    Console.withIn(inputStream) {
      Console.withOut(outCapture) {
        JobcoinMixer.main(Array[String]("test1", "test2"))
      }
    }

    val pattern = "You may now send Jobcoins to address [0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}. They will be mixed and sent to your destination addresses.".r
    val regexMatches = pattern findFirstIn outCapture.toString

    regexMatches.isEmpty should be(false)
  }
}
