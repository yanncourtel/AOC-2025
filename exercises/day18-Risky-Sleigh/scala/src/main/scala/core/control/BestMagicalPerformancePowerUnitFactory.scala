package core.control

import external._

class BestMagicalPerformancePowerUnitFactory(
                                              private val allReindeers: List[Reindeer],
                                              private val availableAmplifierByMagicalPower: Map[Int, AmplifierType]
                                            ) extends PowerUnitFactory {

  override def bringAllReindeers(): List[ReindeerPowerUnit] = {
    val allReindeerByMagicalPower = allReindeers
      .sortBy(_.getMagicPower)(Ordering[Int].reverse)

    allReindeerByMagicalPower
      .zipWithIndex
      .map { case (reindeer, index) =>
        attachPowerUnit(reindeer, index + 1)
      }
  }

  private def attachPowerUnit(reindeer: Reindeer, indexOfMagicalPower: Int): ReindeerPowerUnit = {
    generatePowerUnit(
      reindeer,
      availableAmplifierByMagicalPower.getOrElse(indexOfMagicalPower, AmplifierType.Basic)
    )
  }

  private def generatePowerUnit(
                                 reindeer: Reindeer,
                                 amplifierToAttach: AmplifierType
                               ): ReindeerPowerUnit = {
    new ReindeerPowerUnit(reindeer, new MagicPowerAmplifier(amplifierToAttach))
  }
}