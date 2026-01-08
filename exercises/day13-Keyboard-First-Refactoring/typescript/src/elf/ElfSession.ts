import { Elf } from './Elf';
import { CollaboratorCallException } from '../exception/Exceptions';

export class ElfSession {
    private static instance = new ElfSession();

    private constructor() {}

    static getInstance(): ElfSession {
        return ElfSession.instance;
    }

    getLoggedUser(): Elf {
        throw new CollaboratorCallException(
            'ElfSession is a static collaborator and should not be used directly in unit tests'
        );
    }
}
