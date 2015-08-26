package eu.inn.binders.tconfig

import com.typesafe.config.ConfigValue
import eu.inn.binders.core.Deserializer
import eu.inn.binders.naming.{CamelCaseToDashCaseConverter, Converter, DashCaseToCamelCaseConverter}

trait SerializerFactory[C <: Converter, D <: Deserializer[C]] {
  def createDeserializer(fieldValue: Option[ConfigValue], fieldName: Option[String]): D
}

class DefaultSerializerFactory[C <: Converter] extends SerializerFactory[C, ConfigDeserializer[C]] {
  def createDeserializer(fieldValue: Option[ConfigValue], fieldName: Option[String]) = new ConfigDeserializer[C](fieldValue, fieldName)
}

object SerializerFactory {
  implicit val defaultSerializerFactory = new DefaultSerializerFactory[CamelCaseToDashCaseConverter]

  def findFactory[C <: Converter, D <: Deserializer[C]]
    ()(implicit factory: SerializerFactory[C, D]): SerializerFactory[C, D] = factory
}