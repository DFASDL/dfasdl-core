/*
 * Copyright (c) 2014 - 2020 Contributors as noted in the AUTHORS.md file
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.dfasdl

import enumeratum._
import eu.timepit.refined.auto._
import eu.timepit.refined.types.string.NonEmptyString

/**
  * An enumeration of all element names of the DFASDL.
  */
sealed trait Element extends EnumEntry with Product with Serializable {

  /**
    * The tag name of the element in the DFASDL XML.
    *
    * @return A string containing the XML tag name of the element.
    */
  def tagName: NonEmptyString

}

object Element extends Enum[Element] {
  val values = findValues

  private val mappings: Map[NonEmptyString, Element] = values.map(e => (e.tagName -> e)).toMap

  /**
    * Return an element type corresponding to the given tag name.
    *
    * @param tagName A string containing an XML tag name.
    * @return An option to the corresponding DFASDL element type.
    */
  def from(tagName: String): Option[Element] =
    // Would be nicer to write this line but it caused problems on 2.11:
    // NonEmptyString.from(tagName).toOption.flatMap(n => mappings.get(n))
    NonEmptyString.from(tagName) match {
      case Left(_)  => None
      case Right(n) => mappings.get(n)
    }

  case object BINARY extends Element { override val tagName: NonEmptyString = "bin" }

  case object BINARY_64 extends Element { override val tagName: NonEmptyString = "bin64" }

  case object BINARY_HEX extends Element { override val tagName: NonEmptyString = "binHex" }

  case object CHOICE extends Element { override val tagName: NonEmptyString = "choice" }

  case object CONSTANT extends Element { override val tagName: NonEmptyString = "const" }

  case object CUSTOM_ID extends Element { override val tagName: NonEmptyString = "cid" }

  case object DATE extends Element { override val tagName: NonEmptyString = "date" }

  case object DATETIME extends Element { override val tagName: NonEmptyString = "datetime" }

  case object CHOICE_ELEMENT extends Element { override val tagName: NonEmptyString = "celem" }

  case object ELEMENT extends Element { override val tagName: NonEmptyString = "elem" }

  case object FIXED_SEQUENCE extends Element { override val tagName: NonEmptyString = "fixseq" }

  case object FORMATTED_NUMBER extends Element {
    override val tagName: NonEmptyString = "formatnum"
  }

  case object FORMATTED_STRING extends Element {
    override val tagName: NonEmptyString = "formatstr"
  }

  case object FORMATTED_TIME extends Element {
    override val tagName: NonEmptyString = "formattime"
  }

  case object NUMBER extends Element { override val tagName: NonEmptyString = "num" }

  case object REFERENCE extends Element { override val tagName: NonEmptyString = "ref" }

  case object ROOT extends Element { override val tagName: NonEmptyString = "dfasdl" }

  case object SCALA_EXPRESSION extends Element { override val tagName: NonEmptyString = "sxp" }

  case object SEQUENCE extends Element { override val tagName: NonEmptyString = "seq" }

  case object STRING extends Element { override val tagName: NonEmptyString = "str" }

  case object TIME extends Element { override val tagName: NonEmptyString = "time" }

}
