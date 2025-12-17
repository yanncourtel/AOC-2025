import core.control.{ControlSystem, SleighAction, SleighEngineStatus}
import org.scalatest.BeforeAndAfter
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import java.io.{ByteArrayOutputStream, PrintStream}

class ControlSystemSpec extends AnyFunSpec with Matchers with BeforeAndAfter {
  private val outputStreamCaptor = new ByteArrayOutputStream()

  before {
    Console.withOut(new PrintStream(outputStreamCaptor))
  }

  after {
    Console.withOut(System.out)
    outputStreamCaptor.reset()
  }

  describe("ControlSystem") {
    it("should start the system") {
      val controlSystem = new ControlSystem()
      controlSystem.action = SleighAction.FLYING
      controlSystem.status = SleighEngineStatus.OFF
      controlSystem.startSystem()
      controlSystem.status shouldBe SleighEngineStatus.ON
      outputStreamCaptor.toString.trim shouldBe "Starting the sleigh...\nSystem ready."
    }

    it("should ascend the system") {
      val controlSystem = new ControlSystem()
      controlSystem.startSystem()
      controlSystem.ascend()
      controlSystem.action shouldBe SleighAction.FLYING
      outputStreamCaptor.toString.trim shouldBe "Starting the sleigh...\nSystem ready.\nAscending..."
    }

    it("should descend the system") {
      val controlSystem = new ControlSystem()
      controlSystem.startSystem()
      controlSystem.ascend()
      controlSystem.descend()
      controlSystem.action shouldBe SleighAction.HOVERING
      outputStreamCaptor.toString.trim shouldBe "Starting the sleigh...\nSystem ready.\nAscending...\nDescending..."
    }

    it("testPark") {
      val controlSystem = new ControlSystem()
      controlSystem.startSystem()

      // we want to drain all the magic power to test the parking
      safeAscendManyTimes(controlSystem, 10)

      controlSystem.park()
      controlSystem.ascend()

      controlSystem.action shouldBe SleighAction.Flying
      output.toString.trim shouldBe
        """Starting the sleigh...
          |System ready.
          |Ascending...
          |Ascending...
          |Ascending...
          |Ascending...
          |Parking...
          |Ascending...""".stripMargin
    }

    it("testStop") {
      // The system has been started
      val controlSystem = new ControlSystem()
      controlSystem.action = SleighAction.Parked
      controlSystem.status = SleighEngineStatus.On
      controlSystem.stopSystem()
      controlSystem.status shouldBe SleighEngineStatus.Off
      output.toString.trim shouldBe "Stopping the sleigh...\nSystem shutdown."
    }

    def afterEach(): Unit = {
      System.setOut(originalOutput)
    }

    def safeAscendManyTimes(controlSystem: ControlSystem, numberOfTimes: Int): Unit = {
      try {
        for (_ <- 0 until numberOfTimes) {
          controlSystem.ascend()
        }
      } catch {
        case _: ReindeersNeedRestException =>
        // we want to continue
      }
    }
  }
}
