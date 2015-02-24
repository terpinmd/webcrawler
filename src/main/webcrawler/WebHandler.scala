package webcrawler
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.htmlcleaner._
import java.net.URL
import webcrawler.TagMatcher._

class WebHandler {

  //Per docs this is thread safe
  val cleaner = new HtmlCleaner

  def getBody(url: String): TagNode = {
    val rootNode = cleaner.clean(new URL(url))
    return rootNode.getElementsByName("body", true)(0)
  }

  def traverse(node: TagNode, callback: (String, String) => Unit): Unit = {
    node.traverse(new TagNodeVisitor {
      def visit(parentNode: TagNode, htmlNode: HtmlNode): Boolean = {
        htmlNode match {
          case node: TagNode => {}
          case commentNode: CommentNode => {}
          case contentNode: ContentNode => {
            if (tagMatch(parentNode.getName())) {
              callback(parentNode.getName(), contentNode.getContent())
            }
          }
          case _ => throw new ClassCastException
        }
        true
      }
    })
  }

}
