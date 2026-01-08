// models.ts
export class Child {
  constructor(public readonly name: string) {}
}

export class Gift {
  constructor(
    public readonly name: string,
    public readonly barCode: string
  ) {}
}

export class Sleigh {
  private messages = new Map<Child, string>();

  put(child: Child, message: string): void {
    this.messages.set(child, message);
  }

  getMessages(): ReadonlyMap<Child, string> {
    return this.messages;
  }
}
