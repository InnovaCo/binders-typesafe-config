
import com.typesafe.config.ConfigFactory
import org.scalatest.{FlatSpec, Matchers}


case class TestObject(x: Int, y: Double)
case class TestStringArray(arrayOfStrings: List[String])
case class TestObjectArray(arrayOfObjects: List[TestObject])

class TestArraySerializer extends FlatSpec with Matchers {
  import eu.inn.binders.tconfig._

  "Config " should " deserialize class with List[String]" in {
    val config = ConfigFactory.parseString("obj.array-of-strings:[abc,def]")
    val o = config.read[TestStringArray]("obj")
    val t = TestStringArray(List("abc", "def"))
    assert (t === o)
  }

  "Config " should " deserialize class with TestObjectArray(List[TestObject])" in {
    val config = ConfigFactory.parseString("""
      obj.array-of-objects: [
        {
          x: 10
          y: 20.0
        },
        {
          x: 5
          y: 13
        }
      ]
    """)
    val o = config.read[TestObjectArray]("obj")
    val t = TestObjectArray(List(TestObject(10,20.0), TestObject(5,13)))
    assert (t === o)
  }

  "Config " should " deserialize class with List[TestObject]" in {
    val config = ConfigFactory.parseString("""
      array-of-objects: [
        {
          x: 10
          y: 20.0
        },
        {
          x: 5
          y: 13
        }
      ]
    """)
    val o = config.read[List[TestObject]]("array-of-objects")
    val t = List(TestObject(10,20.0), TestObject(5,13))
    assert (t === o)
  }
}