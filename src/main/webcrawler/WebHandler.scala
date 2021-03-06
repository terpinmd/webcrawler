package webcrawler
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.htmlcleaner._
import java.net.URL
import net.liftweb.json._
import net.liftweb.json.JsonDSL._
import scala.util.Try
object WebHandler {

  //Per docs this is thread safe
  val cleaner = new HtmlCleaner

  def body(url: String): TagNode = {
    var rootNode : TagNode = null
    try{
      rootNode = cleaner.clean(new URL(url))
      return rootNode.getElementsByName("body", true)(0)
    } catch{
      case ex: java.io.IOException =>{
          println(ex) //Need to figure out what to do here
      }
    }
    return new TagNode("error")
  }
  
  
  def asJSON(tag: String, content : String): String = {
    if(content == null) return ""
    val trimmedContent = content.trim   
    if(trimmedContent.length == 0) return ""
    val json = ("tag" -> tag) ~ ("content" -> trimmedContent)
    return compact(render(json))
  }
  
}
