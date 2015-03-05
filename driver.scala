
import org.apache.commons.lang._
import webcrawler.WebHandler._
import webcrawler.NodeInspector
import webcrawler.TagUtil._

import scala.collection.mutable.ListBuffer


object Driver {
  def main(args: Array[String]) = {

    
    def getSites() : ListBuffer[String] = {
      val list = new ListBuffer[String]()
      list += "http://www.testudotimes.com/"
      list += "http://www.testudotimes.com/2015/2/23/8085389/maryland-wisconsin-basketball-game-2015-preview" 
      list += "http://www.foxnews.com"
      list
    }    
    
    for (url <- getSites){
      var inspector = new NodeInspector(body(url))
      var list = inspector.collect(asJSON)

      var a = list.map( item => {
          println(asMap(item))
      })

    }   
  }
} 

