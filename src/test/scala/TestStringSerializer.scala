
import com.typesafe.config.ConfigFactory
import org.scalatest.{FlatSpec, Matchers}

case class TestString(stringVal: String)

class TestStringSerializer extends FlatSpec with Matchers {
  import eu.inn.binders.tconfig._

  "Config " should " deserialize class with String" in {
    val config = ConfigFactory.parseString("obj.stringVal:abc")
    val o = config.readObject[TestString]("obj")
    val t = TestString("abc")
    assert (t === o)
  }
}