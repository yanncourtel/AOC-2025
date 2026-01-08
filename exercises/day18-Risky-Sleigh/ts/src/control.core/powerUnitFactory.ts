import {ReindeerPowerUnit} from "./reindeerPowerUnit";

export interface PowerUnitFactory {
    bringAllReindeers(): ReindeerPowerUnit[]
}