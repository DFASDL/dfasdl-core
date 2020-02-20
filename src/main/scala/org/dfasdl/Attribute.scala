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
  * An enumeration of all attribute names of the DFASDL.
  */
sealed trait Attribute extends EnumEntry with Product with Serializable {

  /**
    * The name of the attribute in the DFASDL XML.
    *
    * @return A string containing the XML name of the attribute.
    */
  def name: NonEmptyString

}

object Attribute extends Enum[Attribute] {
  val values = findValues

  private val mappings: Map[NonEmptyString, Attribute] = values.map(e => (e.name -> e)).toMap

  /**
    * Return an attribute type corresponding to the given name.
    *
    * @param name A string containing an XML attribute name.
    * @return An option to the corresponding DFASDL attribute type.
    */
  def from(name: String): Option[Attribute] =
    // Would be nicer to write this line but it caused problems on 2.11:
    // NonEmptyString.from(name).toOption.flatMap(n => mappings.get(n))
    NonEmptyString.from(name) match {
      case Left(_)  => None
      case Right(n) => mappings.get(n)
    }

  case object DEFAULT_NUMBER extends Attribute { override val name: NonEmptyString = "defaultnum" }

  case object DEFAULT_STRING extends Attribute { override val name: NonEmptyString = "defaultstr" }

  case object CORRECT_OFFSET extends Attribute {
    override val name: NonEmptyString = "correct-offset"
  }

  case object DB_AUTO_INCREMENT extends Attribute {
    override val name: NonEmptyString = "db-auto-inc"
  }

  case object DB_COLUMN_NAME extends Attribute {
    override val name: NonEmptyString = "db-column-name"
  }

  case object DB_FOREIGN_KEY extends Attribute {
    override val name: NonEmptyString = "db-foreign-key"
  }

  case object DB_INSERT extends Attribute { override val name: NonEmptyString = "db-insert" }

  case object DB_PRIMARY_KEY extends Attribute {
    override val name: NonEmptyString = "db-primary-key"
  }

  case object DB_SELECT extends Attribute { override val name: NonEmptyString = "db-select" }

  case object DB_UPDATE extends Attribute { override val name: NonEmptyString = "db-update" }

  case object DECIMAL_SEPARATOR extends Attribute {
    override val name: NonEmptyString = "decimal-separator"
  }

  case object DEFAULT_ENCODING extends Attribute {
    override val name: NonEmptyString = "default-encoding"
  }

  case object ENCODING extends Attribute { override val name: NonEmptyString = "encoding" }

  case object FIXED_SEQUENCE_COUNT extends Attribute { override val name: NonEmptyString = "count" }

  case object FILTER extends Attribute { override val name: NonEmptyString = "filter" }

  case object FORMAT extends Attribute { override val name: NonEmptyString = "format" }

  case object JSON_ATTRIBUTE_NAME extends Attribute {
    override val name: NonEmptyString = "json-attribute-name"
  }

  case object KEEP_ID extends Attribute { override val name: NonEmptyString = "keepID" }

  case object LENGTH extends Attribute { override val name: NonEmptyString = "length" }

  case object MAX_DIGITS extends Attribute { override val name: NonEmptyString = "max-digits" }

  case object MAX_LENGTH extends Attribute { override val name: NonEmptyString = "max-length" }

  case object MAX_PRECISION extends Attribute {
    override val name: NonEmptyString = "max-precision"
  }

  case object PRECISION extends Attribute { override val name: NonEmptyString = "precision" }

  case object SEMANTIC extends Attribute { override val name: NonEmptyString = "s" }

  case object SEMANTIC_SCHEMA extends Attribute { override val name: NonEmptyString = "semantic" }

  case object SEQUENCE_MAX extends Attribute { override val name: NonEmptyString = "max" }

  case object SEQUENCE_MIN extends Attribute { override val name: NonEmptyString = "min" }

  case object SOURCE_ID extends Attribute { override val name: NonEmptyString = "sid" }

  case object START_SIGN extends Attribute { override val name: NonEmptyString = "start-sign" }

  case object STOP_SIGN extends Attribute { override val name: NonEmptyString = "stop-sign" }

  case object STORAGE_PATH extends Attribute { override val name: NonEmptyString = "storage-path" }

  case object TRIM extends Attribute { override val name: NonEmptyString = "trim" }

  case object UNIQUE extends Attribute { override val name: NonEmptyString = "unique" }

  case object XML_ATTRIBUTE_NAME extends Attribute {
    override val name: NonEmptyString = "xml-attribute-name"
  }

  case object XML_ATTRIBUTE_PARENT extends Attribute {
    override val name: NonEmptyString = "xml-attribute-parent"
  }

  case object XML_ELEMENT_NAME extends Attribute {
    override val name: NonEmptyString = "xml-element-name"
  }
}
