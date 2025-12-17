package core.control

import external.deer.Reindeer
import external.stable.MagicStable

class ControlSystem {
    //The Xmas spirit is 40 magic power unit
    private val xmasSpirit = 40
    private val dashboard = Dashboard()
    private val magicStable = MagicStable()
    private var reindeerPowerUnits = bringAllReindeers()
    var status: SleighEngineStatus = SleighEngineStatus.OFF
    var action: SleighAction = SleighAction.PARKED
    private var controlMagicPower = 0f
    private var availableSpecialAmplifiers = mapOf(
        1 to AmplifierType.DIVINE,
        2 to AmplifierType.BLESSED,
        3 to AmplifierType.BLESSED
    )

    init {
        reindeerPowerUnits = bringAllReindeers()
    }

    private fun bringAllReindeers(): List<ReindeerPowerUnit> {
        return BestMagicalPerformancePowerUnitFactory(
            magicStable.allReindeers,
            availableSpecialAmplifiers
        ).bringAllReindeers()
    }

    fun startSystem() {
        dashboard.displayStatus("Starting the sleigh...")

        status = SleighEngineStatus.ON
        dashboard.displayStatus("System ready.")
    }

    @Throws(ReindeersNeedRestException::class, SleighNotStartedException::class)
    fun ascend() {
        if (status == SleighEngineStatus.ON) {
            for (reindeerPowerUnit in reindeerPowerUnits) {
                controlMagicPower += reindeerPowerUnit.harnessMagicPower()
            }

            if (checkReindeerStatus()) {
                dashboard.displayStatus("Ascending...")
                action = SleighAction.FLYING
                controlMagicPower = 0f
            } else throw ReindeersNeedRestException()
        } else {
            throw SleighNotStartedException()
        }
    }

    @Throws(SleighNotStartedException::class)
    fun descend() {
        if (status == SleighEngineStatus.ON) {
            dashboard.displayStatus("Descending...")
            action = SleighAction.HOVERING
        } else throw SleighNotStartedException()
    }

    @Throws(SleighNotStartedException::class)
    fun park() {
        if (status == SleighEngineStatus.ON) {
            dashboard.displayStatus("Parking...")

            for (reindeerPowerUnit in reindeerPowerUnits) {
                // The reindeer rests so the times to harness his magic power resets
                reindeerPowerUnit.reindeer.timesHarnessing = 0
            }

            action = SleighAction.PARKED
        } else throw SleighNotStartedException()
    }

    fun stopSystem() {
        dashboard.displayStatus("Stopping the sleigh...")

        status = SleighEngineStatus.OFF
        dashboard.displayStatus("System shutdown.")
    }

    private fun checkReindeerStatus() = controlMagicPower >= xmasSpirit
}