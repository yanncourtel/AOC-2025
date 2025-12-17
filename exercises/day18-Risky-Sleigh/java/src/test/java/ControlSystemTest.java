import core.control.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class TestControlSystem {
    private ByteArrayOutputStream output;
    private PrintStream originalOutput;

    @BeforeEach
    void setUp() {
        output = new ByteArrayOutputStream();
        originalOutput = System.out;
        System.setOut(new PrintStream(output));
    }

    @Test
    void testStart() {
        // The system has been started
        var controlSystem = new ControlSystem();
        controlSystem.action = SleighAction.FLYING;
        controlSystem.status = SleighEngineStatus.OFF;
        controlSystem.startSystem();
        assertThat(controlSystem.status).isEqualTo(SleighEngineStatus.ON);
        assertThat(output.toString().trim()).isEqualTo("Starting the sleigh...\r\nSystem ready.");
    }

    @Test
    void testAscend() throws ReindeersNeedRestException, SleighNotStartedException {
        var controlSystem = new ControlSystem();
        controlSystem.startSystem();
        controlSystem.ascend();
        assertThat(controlSystem.action).isEqualTo(SleighAction.FLYING);
        assertThat(output.toString().trim()).isEqualTo("Starting the sleigh...\r\nSystem ready.\r\nAscending...");
    }

    @Test
    void testDescend() throws ReindeersNeedRestException, SleighNotStartedException {
        var controlSystem = new ControlSystem();
        controlSystem.startSystem();
        controlSystem.ascend();
        assertThatCode(controlSystem::descend).doesNotThrowAnyException();
        assertThat(controlSystem.action).isEqualTo(SleighAction.HOVERING);
        assertThat(output.toString().trim())
                .isEqualTo("Starting the sleigh...\r\nSystem ready.\r\nAscending...\r\nDescending...");
    }

    @Test
    void testPark() throws SleighNotStartedException, ReindeersNeedRestException {
        var controlSystem = new ControlSystem();
        controlSystem.startSystem();

        // we want to drain all the magic power to test the parking
        safeAscendManyTimes(controlSystem, 10);

        controlSystem.park();
        controlSystem.ascend();

        assertThat(controlSystem.action).isEqualTo(SleighAction.FLYING);
        assertThat(output.toString().trim())
                .isEqualTo("Starting the sleigh...\r\n" +
                        "System ready.\r\n" +
                        "Ascending...\r\n" +
                        "Ascending...\r\n" +
                        "Ascending...\r\n" +
                        "Parking...\r\n" +
                        "Ascending...");
    }

    @Test
    void testStop() {
        // The system has been started
        var controlSystem = new ControlSystem();
        controlSystem.action = SleighAction.PARKED;
        controlSystem.status = SleighEngineStatus.ON;
        controlSystem.stopSystem();
        assertThat(controlSystem.status).isEqualTo(SleighEngineStatus.OFF);
        assertThat(output.toString().trim())
                .isEqualTo("Stopping the sleigh...\r\nSystem shutdown.");
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOutput);
    }

    private static void safeAscendManyTimes(ControlSystem controlSystem, int numberOfTimes) {
        try {
            for (int i = 0; i < numberOfTimes; i++) {
                controlSystem.ascend();
            }
        } catch (ReindeersNeedRestException e) {
            // we want to continue
        } catch (SleighNotStartedException e) {
            // we want to continue
        }
    }
}