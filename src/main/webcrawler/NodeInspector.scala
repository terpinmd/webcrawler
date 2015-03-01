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

  def traverse(callback: (String, String) => Unit): Unit = {
    node.traverse(new TagNodeVisitor {
      def visit(parentNode: TagNode, htmlNode: HtmlNode): Boolean = {
        htmlNode match {
          case tagNode: TagNode => {
              if(tagNode.getName() == "a"){
                if(callback != null)
                callback("href",tagNode.getAttributeByName("href"))                
              }
          }
          case commentNode: CommentNode => {}          
          case contentNode: ContentNode => {
            if (tagMatch(parentNode.getName() )) {
              callback(parentNode.getName(), contentNode.getContent())
            }
          }
          case _ => throw new ClassCastException
        }
        true
      }
    })
  }

  def collect(f : (String, String) => String) : ListBuffer[String] = {
    val list =  new ListBuffer[String]
    
    def append(t:String, c:String) = {
      val result = f(t,c)
      if(result != ""){
        list+= result
      }        
    }    
    traverse(append)
    list
  }
  

  
}
