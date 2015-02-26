package webcrawler
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.htmlcleaner._
import java.net.URL
import webcrawler.TagMatcher._

object WebHandler {

  //Per docs this is thread safe
  val cleaner = new HtmlCleaner

  def body(url: String): TagNode = {
    val rootNode = cleaner.clean(new URL(url))
    return rootNode.getElementsByName("body", true)(0)
  }
}
