package sleightrip.elf

import sleightrip.exception.CollaboratorCallException

object ElfSession {
  private val instance = new ElfSession

  def getInstance(): ElfSession = instance
}

class ElfSession private {
  def getLoggedUser: Elf = {
    throw new CollaboratorCallException(
      "ElfSession is a static collaborator and should not be used directly in unit tests"
    )
  }
}
