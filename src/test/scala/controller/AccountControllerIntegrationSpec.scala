package controller

import mvc.BaseControllerIntegrationSpec
import org.scalacheck.Gen
import org.springframework.test.web.servlet.result.StatusResultMatchers


class AccountControllerIntegrationSpec extends BaseControllerIntegrationSpec { override def data =
  ("when testing ", Seq(("/public/search", true), ("/public/search3", false), ("/public/search7", false)))

}
