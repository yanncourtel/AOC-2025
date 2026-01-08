import core.control.ControlSystem
import core.control.ReindeersNeedRestException
import core.control.SleighAction
import core.control.SleighEngineStatus
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ControlSystemTest : StringSpec({
    val outputStreamCaptor = ByteArrayOutputStream()

    beforeTest {
        System.setOut(PrintStream(outputStreamCaptor))
    }

    afterTest {
        System.setOut(System.out)
    }

    "testStart" {
        // The system has been started
        val controlSystem = ControlSystem()
        controlSystem.action = SleighAction.FLYING
        controlSystem.status = SleighEngineStatus.OFF
        controlSystem.startSystem()
        controlSystem.status shouldBe SleighEngineStatus.ON
        outputStreamCaptor.toString().trim() shouldBe "Starting the sleigh...\r\nSystem ready."
    }

    "testAscend" {
        val controlSystem = ControlSystem()
        controlSystem.startSystem()
        controlSystem.ascend()
        controlSystem.action shouldBe SleighAction.FLYING
        outputStreamCaptor.toString().trim() shouldBe "Starting the sleigh...\r\nSystem ready.\r\nAscending..."
    }

    "testDescend" {
        val controlSystem = ControlSystem()
        controlSystem.startSystem()
        controlSystem.ascend()
        controlSystem.descend()
        controlSystem.action shouldBe SleighAction.HOVERING
        outputStreamCaptor.toString()
            .trim() shouldBe "Starting the sleigh...\r\nSystem ready.\r\nAscending...\r\nDescending..."
    }

    "testPark" {
        fun safeAscendManyTimes(controlSystem: ControlSystem, numberOfTimes: Int) {
            try {
                repeat(numberOfTimes) {
                    controlSystem.ascend()
                }
            } catch (e: ReindeersNeedRestException) {
                // we want to continue
            }
        }

        val controlSystem = ControlSystem()
        controlSystem.startSystem()

        // we want to drain all the magic power to test the parking
        safeAscendManyTimes(controlSystem, 10)

        controlSystem.park()
        controlSystem.ascend()

        controlSystem.action shouldBe SleighAction.FLYING
        outputStreamCaptor.toString()
            .trim() shouldBe """
                Starting the sleigh...
                System ready.
                Ascending...
                Ascending...
                Ascending...
                Ascending...
                Parking...
                Ascending...
            """.trimIndent()
    }

    "testStop" {
        // The system has been started
        val controlSystem = ControlSystem().apply {
            action = SleighAction.PARKED
            status = SleighEngineStatus.ON
        }
        controlSystem.stopSystem()
        controlSystem.status shouldBe SleighEngineStatus.OFF
        outputStreamCaptor.toString()
            .trim() shouldBe "Stopping the sleigh...\r\nSystem shutdown."
    }
})