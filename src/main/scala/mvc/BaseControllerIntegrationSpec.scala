package mvc

import org.scalacheck._
import org.specs2._

class BaseControllerIntegrationSpec extends Specification with ScalaCheck {
  def data: (String, Seq[(String, Boolean)]) = ???

//  Gen.oneOf(("123", ""), ("123123", ""), ("123123", ""))

  def is = {
    val validUrls: Gen[(String, Boolean)] = Gen.oneOf(data._2)

    data._1 ! check(Prop.forAll(validUrls) {urls: (String, Boolean) => {

      false


    }})


  }
    end
}

private case class Action(description: String, data: Gen[(String, String)], evaluation: Boolean) {

}