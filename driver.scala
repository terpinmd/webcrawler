
import webcrawler.WebRunner

object Driver {
  def main(args: Array[String]) = {

    
    var w = new WebRunner()
    w.run()
    
//    def getSites() : ListBuffer[String] = {
//      val list = new ListBuffer[String]()
//      list += "http://www.testudotimes.com/"
//      list += "http://www.testudotimes.com/2015/2/23/8085389/maryland-wisconsin-basketball-game-2015-preview" 
//      list += "http://www.foxnews.com"
//      list
//    }    
//    
//    for (url <- getSites){
//      
//      var inspector = new NodeInspector(body(url))
//      var list = inspector.collect(asJSON)
//
//      val links = list.map({json => toTag(json)}).filter(item => item.tag == "link")
//      
//      println(Set(links.map({link => link.content})))
//      
//      //for (item <- list) { println(asMap(item)) }
//
//    }   
  }
} 

