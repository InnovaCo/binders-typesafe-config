
import com.typesafe.config.ConfigFactory
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.duration._

case class ObjectWithDuration(duration: Duration,
                              optionalDuration: Option[Duration],
                              optionalDuration2: Option[Duration],
                              finiteDuration: FiniteDuration)

class TestDurationDeserializer extends FlatSpec with Matchers {

  import eu.inn.binders.tconfig._

  "Config " should " deserialize class with TimeUnit and FiniteDuration" in {
    val config = ConfigFactory.parseString("""
      obj.duration = 10s
      obj.optional-duration2 = 6min
      obj.finite-duration = 3sec
    """)

    val o = config.read[ObjectWithDuration]("obj")
    val t = ObjectWithDuration(10.seconds, None, Some(6.minutes), 3.seconds)
    assert(t === o)
  }
}
