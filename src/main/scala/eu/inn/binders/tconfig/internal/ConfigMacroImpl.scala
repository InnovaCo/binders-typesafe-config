package eu.inn.binders.tconfig.internal

import java.io.OutputStream

import scala.language.experimental.macros
import scala.language.reflectiveCalls
import scala.reflect.macros.Context

private [tconfig] trait ConfigMacroImpl {
  val c: Context
  import c.universe._
  def read[O: c.WeakTypeTag](path: c.Expr[String]): c.Tree = {
    val block = q"""{
      val t = ${c.prefix.tree}
      val c = t.config.getValue($path)
      val f = eu.inn.binders.tconfig.SerializerFactory.findFactory()
      val d = f.createDeserializer(Option(c), Option($path))
      d.unbind[${weakTypeOf[O]}]
    }"""
    //println(block)
    block
  }
}
