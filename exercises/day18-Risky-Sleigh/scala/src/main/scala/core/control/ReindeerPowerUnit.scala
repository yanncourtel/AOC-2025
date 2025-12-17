package core.control

import external.deer.Reindeer

class ReindeerPowerUnit(val reindeer: Reindeer, amplifier: MagicPowerAmplifier) {
  def harnessMagicPower(): Float = {
    if (!reindeer.needsRest()) {
      reindeer.timesHarnessing += 1
      amplifier.amplify(reindeer.getMagicPower)
    } else 0f
  }

  def checkMagicPower(): Float = reindeer.getMagicPower
}