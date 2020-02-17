package org.dfasdl

import enumeratum._
import eu.timepit.refined.auto._
import eu.timepit.refined.types.string.NonEmptyString

/**
  * An enumeration of all element names of the DFASDL.
  */
sealed trait Elements extends EnumEntry with Product with Serializable {

  /**
    * The tag name of the element in the DFASDL XML.
    *
    * @return A string containing the XML tag name of the element.
    */
  def tagName: NonEmptyString

}

object Elements extends Enum[Elements] {
  val values = findValues

  val mappings: Map[NonEmptyString, Elements] = values.map(e => (e.tagName -> e)).toMap

  /**
    * Return an element type corresponding to the given tag name.
    *
    * @param s A string containing an XML tag name.
    * @return An option to the corresponding DFASDL element type.
    */
  def fromTagName(s: String): Option[Elements] =
    NonEmptyString.from(s).toOption.flatMap(mappings.get)

  case object BINARY extends Elements { override val tagName: NonEmptyString = "bin" }

  case object BINARY_64 extends Elements { override val tagName: NonEmptyString = "bin64" }

  case object BINARY_HEX extends Elements { override val tagName: NonEmptyString = "binHex" }

  case object CHOICE extends Elements { override val tagName: NonEmptyString = "choice" }

  case object CONSTANT extends Elements { override val tagName: NonEmptyString = "const" }

  case object CUSTOM_ID extends Elements { override val tagName: NonEmptyString = "cid" }

  case object DATE extends Elements { override val tagName: NonEmptyString = "date" }

  case object DATETIME extends Elements { override val tagName: NonEmptyString = "datetime" }

  case object CHOICE_ELEMENT extends Elements { override val tagName: NonEmptyString = "celem" }

  case object ELEMENT extends Elements { override val tagName: NonEmptyString = "elem" }

  case object FIXED_SEQUENCE extends Elements { override val tagName: NonEmptyString = "fixseq" }

  case object FORMATTED_NUMBER extends Elements {
    override val tagName: NonEmptyString = "formatnum"
  }

  case object FORMATTED_STRING extends Elements {
    override val tagName: NonEmptyString = "formatstr"
  }

  case object FORMATTED_TIME extends Elements {
    override val tagName: NonEmptyString = "formattime"
  }

  case object NUMBER extends Elements { override val tagName: NonEmptyString = "num" }

  case object REFERENCE extends Elements { override val tagName: NonEmptyString = "ref" }

  case object ROOT extends Elements { override val tagName: NonEmptyString = "dfasdl" }

  case object SCALA_EXPRESSION extends Elements { override val tagName: NonEmptyString = "sxp" }

  case object SEQUENCE extends Elements { override val tagName: NonEmptyString = "seq" }

  case object STRING extends Elements { override val tagName: NonEmptyString = "str" }

  case object TIME extends Elements { override val tagName: NonEmptyString = "time" }

}
