package controller

import mvc.BaseControllerIntegrationSpec
import org.scalacheck.Gen
import org.springframework.test.web.servlet.result.StatusResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers._
import controller.ApplicationController
import org.springframework.http.MediaType
import org.mockito.Mockito._
import org.mockito.ArgumentCaptor


class AccountControllerIntegrationSpec extends BaseControllerIntegrationSpec {

  val mockService: Dispatcher = mock(classOf[Dispatcher])

  def controllerRef = new ApplicationController(mockService)

  "The 'Hello world' string" should {
    "contain 11 characters" in {
      val captor: ArgumentCaptor[Query] = ArgumentCaptor.forClass(classOf[Query])

      whenPost("/public/search") must haveOkStatus
      whenPost("/public/search123") must haveNotFoundStatus

      verify(mockService).dispatch(captor.capture)

      val value: Query = captor.getValue

      println(value)

      value mustNotEqual(null)
    }
  }

}
