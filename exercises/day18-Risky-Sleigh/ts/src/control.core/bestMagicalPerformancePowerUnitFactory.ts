import { AmplifierType } from './amplifierType';
import { Reindeer } from '../external/deer/reindeer';
import { PowerUnitFactory } from './powerUnitFactory';
import { ReindeerPowerUnit } from './reindeerPowerUnit';
import { MagicPowerAmplifier } from './magicPowerAmplifier';

export class BestMagicalPerformancePowerUnitFactory implements PowerUnitFactory {
    private readonly allReindeers: Reindeer[];
    private readonly availableAmplifierByMagicalPower: Map<number, AmplifierType>;

    constructor(
        allReindeers: Reindeer[],
        availableAmplifierByMagicalPower: Map<number, AmplifierType>
    ) {
        this.allReindeers = allReindeers;
        this.availableAmplifierByMagicalPower = availableAmplifierByMagicalPower;
    }

    bringAllReindeers(): ReindeerPowerUnit[] {
        const allReindeerByMagicalPower = [...this.allReindeers]
            .sort((a, b) => b.getMagicPower() - a.getMagicPower());

        return allReindeerByMagicalPower.map((reindeer, index) =>
            this.attachPowerUnit(reindeer, index + 1)
        );
    }

    private attachPowerUnit(reindeer: Reindeer, indexOfMagicalPower: number): ReindeerPowerUnit {
        return this.generatePowerUnit(
            reindeer,
            this.availableAmplifierByMagicalPower.get(indexOfMagicalPower) ?? AmplifierType.BASIC
        );
    }

    private generatePowerUnit(
        reindeer: Reindeer,
        amplifierToAttach: AmplifierType
    ): ReindeerPowerUnit {
        return new ReindeerPowerUnit(reindeer, new MagicPowerAmplifier(amplifierToAttach));
    }
}