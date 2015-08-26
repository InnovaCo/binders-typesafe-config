package eu.inn.binders

import java.io.{OutputStream, InputStream}

import com.typesafe.config.{ConfigValue, Config}
import eu.inn.binders.tconfig.internal.ConfigMacro
import eu.inn.binders.naming.{PlainConverter, Converter}
import scala.language.experimental.macros
import scala.reflect.runtime.universe._

package object tconfig {
  implicit class ConfigReader(val config: Config) extends AnyVal{
    def read[O](path: String): O = macro ConfigMacro.read[O]
  }
  implicit class ConfigValueReader(val configValue: ConfigValue) extends AnyVal{
    def read[O]: O = macro ConfigMacro.readValue[O]
  }
}
