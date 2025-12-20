using ControlSystem.External;

namespace ControlSystem.Core;

public class BestMagicalPerformancePowerUnitFactory : IPowerUnitFactory {
    private List<Reindeer> allReindeers;
    private Dictionary<int, AmplifierType> availableAmplifierByMagicalPower;

    public BestMagicalPerformancePowerUnitFactory(List<Reindeer> allReindeers, Dictionary<int, AmplifierType> availableAmplifierByMagicalPower) {
        this.allReindeers = allReindeers;
        this.availableAmplifierByMagicalPower = availableAmplifierByMagicalPower;
    }

    public List<ReindeerPowerUnit> BringAllReindeers() 
    {      
        var allReindeerByMagicalPower = allReindeers
            .OrderByDescending(r => r.GetMagicPower())
            .ToList();
        
        return allReindeerByMagicalPower
            .Select((reindeer, index) => AttachPowerUnit(reindeer, index + 1))
            .ToList();
    }
    
    private ReindeerPowerUnit AttachPowerUnit(Reindeer reindeer, int indexOfMagicalPower)
    {
        return GeneratePowerUnit(
            reindeer, 
            availableAmplifierByMagicalPower.GetValueOrDefault(indexOfMagicalPower, AmplifierType.Basic)
        );
    }
    
    private ReindeerPowerUnit GeneratePowerUnit(Reindeer reindeer, AmplifierType amplifierToAttach)
    {
        return new ReindeerPowerUnit(reindeer, new MagicPowerAmplifier(amplifierToAttach));
    }
}