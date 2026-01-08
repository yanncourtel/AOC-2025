// Decompiled / patched-up version used for the exercise.
// This class name (D) is from an old tool, never renamed to anything sensible.

// acts like a coordinator that uses some map and a bunch of entries.
export class D {
  // map of keys to numeric counts (probably stock of some kind?).
  private s: Map<any, number>;
  // array of objects that will be processed later.
  private r: any[] = [];

  // seems to be called once with some initial map (configuration / stock table?).
  constructor(m: Map<any, number> | null) {
    this.s = new Map<any, number>();
    if (m) {
      for (const [k, v] of m.entries()) {
        this.s.set(k, v); // just mirror what's in m
      }
    }
  }

  // adds one entry with a string id x and an array y (probably an ordered list).
  public a(x: string | null, y: any[] | null): void {
    if (x == null || y == null) return;
    const cp: any[] = [];
    for (const item of y) {
      cp.push(item); // keep our own copy so callers can reuse their array
    }
    this.r.push(new E(x, cp));
  }

  // main routine: loops over r and pulls up to z items per entry from the map.
  public b(z: number): G[] {
    const out: G[] = [];
    if (z <= 0) return out;

    for (const obj of this.r) {
      if (!(obj instanceof E)) continue; // should mostly be E instances
      const e = obj as E;
      let c = z;
      while (c > 0) {
        const picked = this.f(e);
        if (picked == null) break; // nothing reasonable left for this one
        out.push(new G(e.n, String(picked)));
        c--;
      }
    }
    return out;
  }

  // helper that tries to get exactly one value from s based on this E.
  private f(e: E | null): any | null {
    if (!e || !e.w) return null;

    // first attempt: walk through e.w, in order, and see if s has any of those left
    for (const wish of e.w) {
      if (wish == null) continue;
      const cnt = this.s.get(wish);
      if (typeof cnt === "number" && cnt > 0) {
        this.s.set(wish, cnt - 1); // reduce count by 1
        return wish;
      }
    }

    // fallback: if nothing matched, just pick the first key in s with a positive count
    for (const [k, v] of this.s.entries()) {
      if (typeof v === "number" && v > 0) {
        this.s.set(k, v - 1);
        return k;
      }
    }

    return null;
  }
}

// little container: a label n plus an array w (looks like some sort of wishlist).
class E {
  constructor(
    public n: string,
    public w: any[]
  ) {}
}

// output pair: name + chosen item; not sure why the fields are called a and b.
export class G {
  constructor(
    public a: string,
    public b: string
  ) {}
}
