package webcrawler
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

object TagMatcher {

  def tagMatch(tag: String): Boolean = tag match {
    case "p" => true
    case _ => false
  }
}
