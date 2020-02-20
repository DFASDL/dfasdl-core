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

class AttributeTest extends WordSpec with MustMatchers with ScalaCheckPropertyChecks {

  private val attribute: Gen[Attribute]                         = Gen.oneOf(Attribute.values.toList)
  private implicit val arbitraryAttribute: Arbitrary[Attribute] = Arbitrary(attribute)

  "Attribute#from" when {
    "name is invalid" must {
      "return an empty Option" in {
        forAll("name") { s: String =>
          val n = NonEmptyString.from(s)
          val t = n.fold(_ => false, t => Attribute.values.exists(_.name === t))
          whenever(n.isLeft || !t) {
            Attribute.from(s) must be(empty)
          }
        }
      }
    }

    "name is valid" must {
      "return the attribute type" in {
        forAll("attribute") { e: Attribute =>
          val s: String = e.name
          Attribute.from(s) must contain(e)
        }
      }
    }
  }

}
