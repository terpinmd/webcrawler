
import scalaj.http._
import net.liftweb.json._
import scala.xml._
import org.htmlcleaner._
import java.net.URL
import scala.collection.mutable._
import org.apache.commons.lang._
import webcrawler.WebHandler._
import webcrawler.DataAccess

object Driver {
  def main(args: Array[String]) = {

    var dataAccess = new DataAccess
    
    for (site <- dataAccess.getSites){
      traverse(body(site), dataAccess.parse)
    } 
  }
} 

