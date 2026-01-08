package core.control;

import external.deer.Reindeer;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class BestMagicalPerformancePowerUnitFactory implements IPowerUnitFactory {
    private final List<Reindeer> allReindeers;
    private final Map<Integer, AmplifierType> availableAmplifierByMagicalPower;

    public BestMagicalPerformancePowerUnitFactory(
            List<Reindeer> allReindeers,
            Map<Integer, AmplifierType> availableAmplifierByMagicalPower) {
        this.allReindeers = allReindeers;
        this.availableAmplifierByMagicalPower = availableAmplifierByMagicalPower;
    }

    @Override
    public List<ReindeerPowerUnit> bringAllReindeers() {
        var allReindeerByMagicalPower = allReindeers.stream()
                .sorted(Comparator.comparingDouble(Reindeer::getMagicPower).reversed())
                .toList();

        return IntStream.range(0, allReindeerByMagicalPower.size())
                .mapToObj(index -> attachPowerUnit(
                        allReindeerByMagicalPower.get(index),
                        index + 1))
                .toList();
    }

    private ReindeerPowerUnit attachPowerUnit(Reindeer reindeer, int indexOfMagicalPower) {
        return generatePowerUnit(
                reindeer,
                availableAmplifierByMagicalPower.getOrDefault(
                        indexOfMagicalPower,
                        AmplifierType.BASIC
                )
        );
    }

    private ReindeerPowerUnit generatePowerUnit(Reindeer reindeer, AmplifierType amplifierToAttach) {
        return new ReindeerPowerUnit(reindeer, new MagicPowerAmplifier(amplifierToAttach));
    }
}