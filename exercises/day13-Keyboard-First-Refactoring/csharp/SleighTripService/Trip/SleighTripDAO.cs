using SleighTripService.Exception;

namespace SleighTripService.Trip;

/// <summary>
/// Legacy static data access object for sleigh trips.
/// </summary>
public static class SleighTripDAO
{
    public static List<SleightTrip> FindTripsByUser(Elf.Elf user)
    {
        throw new CollaboratorCallException(
            "SleighTripDAO is a static collaborator and should not be used directly in unit tests"
        );
    }
}
