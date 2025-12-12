package sleightrip.elf

import sleightrip.exception.CollaboratorCallException

class ElfSession private constructor() {
    
    companion object {
        private val instance = ElfSession()
        
        fun getInstance(): ElfSession = instance
    }

    fun getLoggedUser(): Elf {
        throw CollaboratorCallException(
            "ElfSession is a static collaborator and should not be used directly in unit tests"
        )
    }
}
