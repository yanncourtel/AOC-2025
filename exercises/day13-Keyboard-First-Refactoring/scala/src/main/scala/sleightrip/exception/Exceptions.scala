package sleightrip.exception

/**
 * Thrown when no elf is logged into the workshop system.
 */
class ElfNotLoggedInException 
  extends RuntimeException("No elf is logged into the workshop system")

/**
 * Thrown when a static collaborator (like UserSession or TripDAO) is called directly.
 */
class CollaboratorCallException(message: String) extends RuntimeException(message)
