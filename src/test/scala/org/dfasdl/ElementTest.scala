/*
 * Copyright (c) 2014 - 2020 Contributors as noted in the AUTHORS.md file
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.dfasdl

import eu.timepit.refined.auto._
import eu.timepit.refined.types.string.NonEmptyString
import org.scalacheck.{ Arbitrary, Gen }
import org.scalatest.{ MustMatchers, WordSpec }
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class ElementTest extends WordSpec with MustMatchers with ScalaCheckPropertyChecks {

  private val element: Gen[Element]                         = Gen.oneOf(Element.values.toList)
  private implicit val arbitraryElement: Arbitrary[Element] = Arbitrary(element)

  "Element#from" when {
    "tag name is invalid" must {
      "return an empty Option" in {
        forAll("tag name") { s: String =>
          val n = NonEmptyString.from(s)
          val t = n.fold(_ => false, t => Element.values.exists(_.tagName === t))
          whenever(n.isLeft || !t) {
            Element.from(s) must be(empty)
          }
        }
      }
    }

    "tag name is valid" must {
      "return the element type" in {
        forAll("element") { e: Element =>
          val s: String = e.tagName
          Element.from(s) must contain(e)
        }
      }
    }
  }

}
