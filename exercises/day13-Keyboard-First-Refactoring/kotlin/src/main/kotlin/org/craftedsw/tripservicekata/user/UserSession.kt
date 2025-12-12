package org.craftedsw.tripservicekata.user

import org.craftedsw.tripservicekata.exception.CollaboratorCallException

/**
 * Static-like singleton representing the workshop login system.
 */
open class UserSession private constructor() {

    open fun getLoggedUser(): User? {
        throw CollaboratorCallException(
            "UserSession is a static collaborator and should not be used directly in unit tests"
        )
    }

    companion object {
        private var instance: UserSession = UserSession()

        fun getInstance(): UserSession = instance
    }
}
