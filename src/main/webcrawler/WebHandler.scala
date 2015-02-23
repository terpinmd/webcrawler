package webcrawler
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.htmlcleaner._
import java.net.URL

class WebHandler {

  //Per docs this is thread safe
  val cleaner = new HtmlCleaner

  def getBody(url: String): TagNode = {
    val rootNode = cleaner.clean(new URL(url))
    return rootNode.getElementsByName("body", true)(0)
  }




    def traverse(node: TagNode): Unit = {
      node.traverse(new TagNodeVisitor {
          def visit(parentNode: TagNode, htmlNode: HtmlNode): Boolean = {
            htmlNode match {
              case node: TagNode => {
                  println(node.getName())            
                }
              case commentNode: CommentNode => {
                  println(commentNode)            
                } 
              case contentNode: ContentNode => {
                  println(parentNode.getName() + " <-parent " + contentNode.getContent())
                }
              case _ => throw new ClassCastException
            }
            true
          }
        })
    }

}
