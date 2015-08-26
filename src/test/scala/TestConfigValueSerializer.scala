
import com.typesafe.config.ConfigFactory
import org.scalatest.{FlatSpec, Matchers}


class TestConfigValueSerializer extends FlatSpec with Matchers {
  import eu.inn.binders.tconfig._

  "ConfigValue " should " deserialize class with String" in {
    val config = ConfigFactory.parseString("obj.string-val:abc")
    val value = config.getValue("obj")
    val o = value.read[TestString]
    val t = TestString("abc")
    assert (t === o)
  }
}