package core.control

import external.deer.Reindeer

class BestMagicalPerformancePowerUnitFactory(
    private val allReindeers: List<Reindeer>,
    private val availableAmplifierByMagicalPower: Map<Int, AmplifierType>
) : PowerUnitFactory {

    override fun bringAllReindeers(): List<ReindeerPowerUnit> {
        val allReindeerByMagicalPower = allReindeers
            .sortedByDescending { it.magicPower }

        return allReindeerByMagicalPower
            .mapIndexed { index, reindeer ->
                attachPowerUnit(reindeer, index + 1)
            }
    }

    private fun attachPowerUnit(reindeer: Reindeer, indexOfMagicalPower: Int): ReindeerPowerUnit {
        return generatePowerUnit(
            reindeer,
            availableAmplifierByMagicalPower.getOrDefault(indexOfMagicalPower, AmplifierType.BASIC)
        )
    }

    private fun generatePowerUnit(
        reindeer: Reindeer,
        amplifierToAttach: AmplifierType
    ): ReindeerPowerUnit {
        return ReindeerPowerUnit(reindeer, MagicPowerAmplifier(amplifierToAttach))
    }
}