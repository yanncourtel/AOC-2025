using SleighTripService.Trip;

namespace SleighTripService.Elf;

/// <summary>
/// Represents an elf in Santa's workshop.
/// 
/// Each elf:
///  - has a list of workshop friends (other elves)
///  - has a list of sleigh trips they went on to deliver presents
/// </summary>
public class Elf
{
    private readonly List<SleightTrip> _sleighTrips = new();
    private readonly List<Elf> _friends = new();

    public List<Elf> GetFriends() => _friends;

    public void AddFriend(Elf elf)
    {
        _friends.Add(elf);
    }

    public void AddTrip(SleightTrip sleighTrip)
    {
        _sleighTrips.Add(sleighTrip);
    }

    public List<SleightTrip> Trips() => _sleighTrips;
}
