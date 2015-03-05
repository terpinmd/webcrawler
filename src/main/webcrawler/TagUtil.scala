package webcrawler
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import net.liftweb.json._
import net.liftweb.json.JsonDSL._

object TagUtil {

  case class Tag(tag: String, content: String)
  
  implicit val formats = net.liftweb.json.DefaultFormats
  
  def tagMatch(tag: String): Boolean = tag match {
    case "p" => true
    case "a" => true      
    case _ => false
  }
  
  def toTag(tag : String) : Tag = {
    parse(tag).extract[Tag]
  }
  
  def asMap(tagString : String) : Map[String, String] = {
    val tag = toTag(tagString)
    Map("tag" -> tag.tag, "content" -> tag.content)
  }
 
}
