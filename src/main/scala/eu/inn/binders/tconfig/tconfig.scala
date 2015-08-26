package eu.inn.binders

import java.io.{OutputStream, InputStream}

import com.typesafe.config.Config
import eu.inn.binders.tconfig.internal.ConfigMacro
import eu.inn.binders.naming.{PlainConverter, Converter}
import scala.language.experimental.macros
import scala.reflect.runtime.universe._

package object tconfig {
  implicit class ConfigReader(val config: Config) extends AnyVal{
    def readObject[O](path: String): O = macro ConfigMacro.readConfig[O]
  }
}
