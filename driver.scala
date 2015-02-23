
import scalaj.http._
import net.liftweb.json._
import scala.xml._
import org.htmlcleaner._
import java.net.URL
import scala.collection.mutable._
import org.apache.commons.lang._
import webcrawler.WebHandler


object Driver { 
  def main(args: Array[String]) = {println("dddddd") }

  val webHandler = new WebHandler
  val body = webHandler.getBody("http://www.testudotimes.com/2015/2/23/8085389/maryland-wisconsin-basketball-game-2015-preview")
  webHandler.traverse(body)
} 