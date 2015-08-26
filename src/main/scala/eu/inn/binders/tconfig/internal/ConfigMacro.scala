package eu.inn.binders.tconfig.internal

import java.io.OutputStream

import scala.language.experimental.macros
import scala.language.reflectiveCalls
import scala.reflect.macros.Context

private [tconfig] object ConfigMacro {
  def read[O: c.WeakTypeTag]
    (c: Context)
    (path: c.Expr[String]): c.Expr[O] = {
    val c0: c.type = c
    val bundle = new {
      val c: c0.type = c0
    } with ConfigMacroImpl
    c.Expr[O](bundle.read[O](path))
  }

  def readValue[O: c.WeakTypeTag]
  (c: Context): c.Expr[O] = {
    val c0: c.type = c
    val bundle = new {
      val c: c0.type = c0
    } with ConfigMacroImpl
    c.Expr[O](bundle.readValue[O])
  }
}
