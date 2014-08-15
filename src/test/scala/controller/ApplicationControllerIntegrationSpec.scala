package controller

import mvc.BaseControllerIntegrationSpec
import org.scalacheck.Gen


class ApplicationControllerIntegrationSpec extends BaseControllerIntegrationSpec { override def data =

  ("when testing ", Seq(("/public/search", true), ("/public/search3", false), ("/public/search7", true)))

}
