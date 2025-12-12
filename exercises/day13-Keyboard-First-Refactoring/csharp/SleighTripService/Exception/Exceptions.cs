namespace SleighTripService.Exception;

/// <summary>
/// Thrown when no elf is logged into the workshop system.
/// </summary>
public class ElfNotLoggedInException : System.Exception
{
    public ElfNotLoggedInException() 
        : base("No elf is logged into the workshop system")
    {
    }
}

/// <summary>
/// Thrown when a static collaborator (like UserSession or TripDAO) is called directly.
/// </summary>
public class CollaboratorCallException : System.Exception
{
    public CollaboratorCallException(string message) : base(message)
    {
    }
}
