package webcrawler
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.htmlcleaner._
import java.net.URL
import webcrawler.TagMatcher._
import net.liftweb.json._
import net.liftweb.json.JsonDSL._

object WebHandler {

  //Per docs this is thread safe
  val cleaner = new HtmlCleaner

  def body(url: String): TagNode = {
    val rootNode = cleaner.clean(new URL(url))
    return rootNode.getElementsByName("body", true)(0)
  }
  
  def href(s:String, c: ContentNode) : String = {
    if(s == "href")
      return s + ":" + c.getContent.trim
    ""
  } 
  
  def asJSON(tag: String, c : ContentNode): String = {
    if(c == null) return ""
    val trimmedContent = c.getContent.trim   
    if(trimmedContent.length == 0) return ""
    val json = ("tag" -> tag) ~ ("content" -> trimmedContent)
    return compact(render(json))
  }
}
