package core.control;

import external.deer.Reindeer;

public class ReindeerPowerUnit {
    public Reindeer reindeer;
    public MagicPowerAmplifier amplifier;

    public ReindeerPowerUnit(Reindeer reindeer, MagicPowerAmplifier amplifier) {
        this.reindeer = reindeer;
        this.amplifier = amplifier;
    }

    public float harnessMagicPower() {
        if (!reindeer.needsRest()) {
            reindeer.timesHarnessing++;
            return amplifier.amplify(reindeer.getMagicPower());
        }

        return 0;
    }

    public float checkMagicPower() {
        return reindeer.getMagicPower();
    }
}
