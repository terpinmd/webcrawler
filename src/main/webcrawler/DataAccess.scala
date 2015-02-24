package webcrawler

import net.liftweb.json._
import net.liftweb.json.JsonDSL._
import scala.collection.mutable.ListBuffer

class DataAccess {

  private val data = List()
  
  def parse(tag: String, content : String): Boolean = {
    var trimmedContent = content.trim   
    if(trimmedContent.length == 0) return false
    val json = ("tag" -> tag) ~ ("content" -> content.trim)
    println(compact(render(json)))
    true
  }
  
  def getSites() : ListBuffer[String] = {
    val list = new ListBuffer[String]()
    list += "http://www.testudotimes.com/"
    list += "http://www.testudotimes.com/2015/2/23/8085389/maryland-wisconsin-basketball-game-2015-preview" 
    list += "http://www.foxnews.com"
    list
  }
}
