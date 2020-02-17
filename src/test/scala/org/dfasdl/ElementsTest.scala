package org.dfasdl

import eu.timepit.refined.auto._
import org.scalacheck.{ Arbitrary, Gen }
import org.scalatest.{ MustMatchers, WordSpec }
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class ElementsTest extends WordSpec with MustMatchers with ScalaCheckPropertyChecks {

  private val element: Gen[Elements]                         = Gen.oneOf(Elements.values.toList)
  private implicit val arbitraryElement: Arbitrary[Elements] = Arbitrary(element)

  "Elements#fromTagName" when {
    "tag name is invalid" must {
      "return an empty Option" in {
        forAll("tag name") { s: String =>
          Elements.fromTagName(s) must be(empty)
        }
      }
    }

    "tag name is valid" must {
      "return the element type" in {
        forAll("element") { e: Elements =>
          val s: String = e.tagName
          Elements.fromTagName(s) must contain(e)
        }
      }
    }
  }

}
