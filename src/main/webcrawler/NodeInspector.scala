package webcrawler
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.htmlcleaner._
import scala.collection.mutable.ListBuffer
import java.net.URLDecoder;

class NodeInspector(n: TagNode) {
  var node: TagNode = n

  val noop = {}
  
  def traverse(callback: (String, String) => Unit): Unit = {
    node.traverse(new TagNodeVisitor {
      def visit(parentNode: TagNode, htmlNode: HtmlNode): Boolean = {
        htmlNode match {
          case tagNode: TagNode => {
            //move this to tag util
            var encoded = tagNode.getAttributeByName("href")
            if(encoded != null){
              val decoded = URLDecoder.decode(encoded, "utf-8")
              callback("link " + parentNode.getName,  URLDecoder.decode(encoded, "utf-8"))              
            }
          }
          case commentNode: CommentNode => {}          
          case contentNode: ContentNode => {
            callback(parentNode.getName(), contentNode.getContent())
          }
          case _ => throw new ClassCastException
        }
        true
      }
    })
  }

  def collect(f : (String, String) => String) : ListBuffer[String] = {
    val list =  new ListBuffer[String]        
    
    traverse((t:String, c:String) => {
      val result = f(t,c)
      if(result != ""){
        list+= result
      }        
    })
  
    list
  }
  

  
}
