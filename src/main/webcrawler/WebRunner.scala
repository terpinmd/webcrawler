package webcrawler

import org.apache.commons.lang._
import webcrawler.WebHandler._
//import webcrawler.NodeInspector
import webcrawler.TagUtil._
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Stack

class WebRunner {
 
  var visited = Set[String]()
  var allLinks = Stack[String]()
 
  
  val rootURL = "http://www.testudotimes.com"
  allLinks.push(rootURL)
  
  def run() = {
     while(iterate(rootURL, allLinks)){
       println("got one")
     }
    println(allLinks)
    println("done") //wtf if this is gone then program blows up
  }
  
  def iterate(url: String, unvistedLinks: Stack[String]) : Boolean = {

    if(unvistedLinks.isEmpty){
      return true
    }
    var inspector = new NodeInspector(body(url))
    var list = inspector.collect(asJSON)

    //Later figure out how to hand off the processing of the actual content here
    var linksNodes = list.map({json => toTag(json)}).filter(item => item.tag == "link")
    var uniqueLinks =  linksNodes.map({link => link.content}).filter(item => item startsWith rootURL).toSet[String] 

    //The links we still need to visit is the current list minus whats been visited   
    uniqueLinks --= visited
    uniqueLinks -= url
    //We have visited this url so grab it
    visited += url

    
    unvistedLinks.pushAll(uniqueLinks)

    var t = uniqueLinks.toArray
    if(t.length == 0){
      return false
    } 
      else
    return iterate(t(0), unvistedLinks)
  }
  
} 

