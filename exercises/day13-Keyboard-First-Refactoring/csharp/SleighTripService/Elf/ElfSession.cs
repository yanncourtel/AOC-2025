using SleighTripService.Exception;

namespace SleighTripService.Elf;

public class ElfSession
{
    private static readonly ElfSession Instance = new();

    private ElfSession() { }

    public static ElfSession GetInstance() => Instance;

    public Elf GetLoggedUser()
    {
        throw new CollaboratorCallException(
            "ElfSession is a static collaborator and should not be used directly in unit tests"
        );
    }
}
