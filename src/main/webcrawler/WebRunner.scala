package webcrawler

import org.apache.commons.lang._
import webcrawler.WebHandler._
import webcrawler.TagUtil._
import scala.collection.mutable.Stack
import scala.collection.mutable.Set
class WebRunner {
 
  //These are all temporary we can fill in allLinks from some repository later
  var visited = Set[String]()
  var allLinks = Set[String]()
   
  //This is a good test site to use for now
  val rootURL = "http://www.nextcentury.com"
  allLinks += rootURL
  
  def run() : Boolean = {
    iterate(rootURL, allLinks, visited)
  }
  
  def iterate(url: String, unvistedLinks: Set[String], visitedLinks: Set[String]) : Boolean = {

    println(url)
    
    //Grab the root node for the given url then collect the json
    var inspector = new NodeInspector(body(url))
    var siteNodes = inspector.collect(asJSON)

    //Turn the nodes into tags. This can be what is handed off to someoutside process later
    var nodesAsTags = siteNodes.map({json => toTag(json)})
    
    //Grab the unique links
    var linksNodes = nodesAsTags.filter(item => item.tag == "link")
    
    //These are the unique links for the current URL
    var uniqueLinks =  linksNodes.map({link => link.content}).filter(item => item startsWith rootURL).toSet[String] 

    //We have now visited this URL so add it
    visitedLinks += url

    //Add the uniqueLinks to the unvisited set
    unvistedLinks ++= uniqueLinks
    
    //Make sure the visited links are removed from the unvisited
    unvistedLinks --= visitedLinks

    //Also remove the current URL from the unvisited
    unvistedLinks -= url
    
    //If there are no unvisited links then we are done
    if(unvistedLinks.size == 0){
      return false
    }
    
    //If there are more links to visit then recursivly call this method
    return iterate(unvistedLinks.head, unvistedLinks, visitedLinks)
  }
  
} 

