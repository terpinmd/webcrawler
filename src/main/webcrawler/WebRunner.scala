package webcrawler

import org.apache.commons.lang._
import webcrawler.WebHandler._
//import webcrawler.NodeInspector
import webcrawler.TagUtil._
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Stack
import scala.collection.mutable.Set
class WebRunner {
 
  var visited = Set[String]()
  var allLinks = Set[String]()
 
  
  val rootURL = "http://www.nextcentury.com"
  allLinks += rootURL
  
  def run() = {
     iterate(rootURL, allLinks, visited)
   // println(allLinks)
    println("done") //wtf if this is gone then program blows up
  }
  
  def iterate(url: String, unvistedLinks: Set[String], visitedLinks: Set[String]) : Boolean = {

    println("----------------" + url)
    

    var inspector = new NodeInspector(body(url))
    var list = inspector.collect(asJSON)

    //Later figure out how to hand off the processing of the actual content here
    var linksNodes = list.map({json => toTag(json)}).filter(item => item.tag == "link")
    var uniqueLinks =  linksNodes.map({link => link.content}).filter(item => item startsWith rootURL).toSet[String] 

    //We have now visited this URL so add it
    visitedLinks += url

    //println("--2> " + uniqueLinks.size)
    unvistedLinks ++= uniqueLinks
    unvistedLinks --= visitedLinks

    unvistedLinks -= url
    
    if(unvistedLinks.size == 0){
      return false
    }
    
    return iterate(unvistedLinks.head, unvistedLinks, visitedLinks)
  }
  
} 

