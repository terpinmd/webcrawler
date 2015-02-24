package webcrawler
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import net.liftweb.json._
import net.liftweb.json.JsonDSL._

class DataAccess {

  private val data = List()
  
  def parse(tag: String, content : String): Boolean = {
    val json = ("tag" -> tag) ~ ("content" -> content.trim)
    println(compact(render(json)))
    //return data.add(json)
    true
  }
  
  def getStore() : AnyRef = {
    return List(1,2,3)
  }
}
