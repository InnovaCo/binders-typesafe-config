
import com.typesafe.config.ConfigFactory
import eu.inn.binders.naming.DashCaseToCamelCaseConverter
import org.scalatest.{FlatSpec, Matchers}

case class TestString(stringVal: String)
case class TestOptionString(stringVal: Option[String])

class TestStringSerializer extends FlatSpec with Matchers {
  import eu.inn.binders.tconfig._

  "Config " should " deserialize class with String" in {
    val config = ConfigFactory.parseString("obj.string-val:abc")
    val o = config.read[TestString]("obj")
    val t = TestString("abc")
    assert (t === o)
  }

  "Config " should " deserialize class with Option[String]/Some" in {
    val config = ConfigFactory.parseString("obj.string-val:abc")
    val o = config.read[TestOptionString]("obj")
    val t = TestOptionString(Some("abc"))
    assert (t === o)
  }

  "Config " should " deserialize class with Option[String]/null" in {
    val config = ConfigFactory.parseString("obj.string-val:null")
    val o = config.read[TestOptionString]("obj")
    val t = TestOptionString(None)
    assert (t === o)
  }

  "Config " should " deserialize class with Option[String]/None" in {
    val config = ConfigFactory.parseString("obj: {}")
    val o = config.read[TestOptionString]("obj")
    val t = TestOptionString(None)
    assert (t === o)
  }
}