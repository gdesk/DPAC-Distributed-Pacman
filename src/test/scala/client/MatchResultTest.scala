package client

import java.util.Calendar

import client.gameElement.VirtualDot
import client.utils.Point
import org.scalatest.FunSuite

/**
  * Created by ManuBottax on 29/06/2017.
  */
class MatchResultTest extends FunSuite {

    test("A New Result has the current date & Time") {
      val r: MatchResult = new MatchResultImpl(true, 10)
      val date: Calendar = Calendar.getInstance()


      assert(r.date.get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH))
      assert(r.date.get(Calendar.HOUR) == date.get(Calendar.HOUR))
      assert(r.date.get(Calendar.MINUTE) == date.get(Calendar.MINUTE))
    }

}
