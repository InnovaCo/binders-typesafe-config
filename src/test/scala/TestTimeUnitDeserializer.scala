
import com.typesafe.config.ConfigFactory
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.duration._

case class ObjectWithTimeUnit(duration: Duration, optionalDuration: Option[Duration], optionalDuration2: Option[Duration])

class TestTimeUnitDeserializer extends FlatSpec with Matchers {

  import eu.inn.binders.tconfig._

  "Config " should " deserialize class with TimeUnit and FiniteDuration" in {
    val config = ConfigFactory.parseString("""
      obj.duration = 10s
      obj.optional-duration2 = 6min
    """)

    val o = config.read[ObjectWithTimeUnit]("obj")
    val t = ObjectWithTimeUnit(10.seconds, None, Some(6.minutes))
    assert(t === o)
  }
}