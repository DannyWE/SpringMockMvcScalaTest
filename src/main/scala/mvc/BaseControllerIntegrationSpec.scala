package mvc

import org.scalacheck._
import org.specs2.mutable._
import org.springframework.http.{MediaType, HttpMethod}
import org.springframework.test.web.servlet.{ResultMatcher, ResultActions, MockMvc}
import org.springframework.test.web.servlet.setup.MockMvcBuilders._
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders._
import org.specs2.matcher.{MatchSuccess, MatchResult, Expectable, Matcher}
import org.springframework.test.web.servlet.result.MockMvcResultMatchers._
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request
import scala.util.{Failure, Success, Try}
import org.mockito.{Mockito, Mock, ArgumentCaptor}
import controller.{ApplicationController, Dispatcher, Query}
import com.sun.tools.javac.code.TypeTag
import org.mockito.Mockito._

abstract class BaseControllerIntegrationSpec extends Specification {

  private lazy val mockMvc: MockMvc = standaloneSetup(controllerRef).build

  def controllerRef: AnyRef

//  Gen.oneOf(("123", ""), ("123123", ""), ("123123", ""))

//  def whenPost(url: String, content: String, contentType: Seq[MediaType], consume: MediaType) = {
//    request(HttpMethod.POST, url, content, contentType, consume)
//  }
//
//  def request(method: HttpMethod,
//              url: String,
//              content: String = "{}",
//              contentType: Seq[MediaType] = MediaType.ALL,
//              consume: MediaType = MediaType.ALL): ResultActions = {
//
//    mockMvc.perform(
//      post(url)
//        .contentType(MediaType.APPLICATION_JSON)
//        .accept(consume)
//        .content(content))
//  }


  object whenPost extends request {

    def apply(url: String,
              content: String = "{}",
              contentType: MediaType = MediaType.ALL,
              consume: MediaType = MediaType.ALL) = {

//      val spy: ApplicationController = Mockito.spy(new ApplicationController)
//
//      val captor: ArgumentCaptor[_] = ArgumentCaptor.forClass(ev.runtimeClass)
////
////      val mockDispatcher = mock(classOf[Dispatcher])
////
//      verify(service.asInstanceOf[Dispatcher]).dispatch(captor.capture().asInstanceOf[Query])
//
////      println(captor)
//
//      val argument: Any = captor.getValue

//      println(argument)

//      val result = controllerRef
//
//      val mockController = Mock[result]
//      verify(controller)

      execute(HttpMethod.POST, url, content, contentType, consume)

    }
  }

  object whenGet extends request {

    def apply(url: String,
              content: String = "{}",
              contentType: MediaType = MediaType.ALL,
              consume: MediaType = MediaType.ALL) = {

      execute(HttpMethod.GET, url, content, contentType, consume)

    }
  }

  trait request {

    def execute(method: HttpMethod,
              url: String,
              content: String = "{}",
              contentType: MediaType = MediaType.ALL,
              consume: MediaType = MediaType.ALL,
              param: Seq[(String, String)] = Nil ): ResultActions = {

      mockMvc.perform(
        post(url)
          .contentType(MediaType.APPLICATION_JSON)
          .accept(consume)
          .content(content)
      )
    }

  }

  class StatusMatcher[T](status: ResultMatcher) extends Matcher[ResultActions] {
    override def apply[S <: ResultActions](t: Expectable[S]): MatchResult[S] = {
      val expectedResult: Try[ResultActions] = Try(t.value.andExpect(status))

//      expectedResult match {
//        case Success => {
//          result(true, "ok", "ko", t)
//        }
//        case Failure => {
//
//        }
//      }

      result(expectedResult.isSuccess, "ok", "expected result is not success", t)

    }
  }


  def haveOkStatus = new StatusMatcher[ResultActions](status.isOk)
  def haveNotFoundStatus = new StatusMatcher[ResultActions](status.isNotFound)
  def haveBadRequestStatus = new StatusMatcher[ResultActions](status.isBadRequest)


  //  class StatusMatcher[T](status: Try[ResultMatcher]) extends Matcher[Try[ResultActions]] {
//    override def apply[S <: Try[ResultActions]](t: Expectable[S]): MatchResult[S] = {
//
//      result(true, "ok", "ko", t)
//
//    }
//  }
//
//  def statusOk = new StatusMatcher[ResultActions](Success(status.isOk))
//



//  def is = {
//    val validUrls: Gen[(String, Boolean)] = Gen.oneOf(("123", true), ("123", true))
//
//    "" ! check(Prop.forAll(validUrls) {urls: (String, Boolean) => {
//
//      true
//
//
//    }})
//
//
//  }
//    end
}

private case class Action(description: String, data: Gen[(String, String)], evaluation: Boolean) {

}