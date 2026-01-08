/**
 * Thrown when no elf is logged into the workshop system.
 */
export class ElfNotLoggedInException extends Error {
    constructor() {
        super('No elf is logged into the workshop system');
        this.name = 'ElfNotLoggedInException';
    }
}

/**
 * Thrown when a static collaborator (like UserSession or TripDAO) is called directly.
 */
export class CollaboratorCallException extends Error {
    constructor(message: string) {
        super(message);
        this.name = 'CollaboratorCallException';
    }
}
