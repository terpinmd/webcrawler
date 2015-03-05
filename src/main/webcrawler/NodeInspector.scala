package webcrawler
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.htmlcleaner._
import webcrawler.TagMatcher._
import scala.collection.mutable.ListBuffer

class NodeInspector(n: TagNode) {
  var node: TagNode = n

  val noop = {}
  
  def traverse(callback: (String, ContentNode) => Unit): Unit = {
    node.traverse(new TagNodeVisitor {
      def visit(parentNode: TagNode, htmlNode: HtmlNode): Boolean = {
        htmlNode match {
          case tagNode: TagNode => noop
          case commentNode: CommentNode => {}          
          case contentNode: ContentNode => {
            callback(parentNode.getName(), contentNode)
          }
          case _ => throw new ClassCastException
        }
        true
      }
    })
  }

  def collect(f : (String, ContentNode) => String) : ListBuffer[String] = {
    val list =  new ListBuffer[String]        
    
    traverse((t:String, c:ContentNode) => {
      val result = f(t,c)
      if(result != ""){
        list+= result
      }        
    })
  
    list
  }
  

  
}
