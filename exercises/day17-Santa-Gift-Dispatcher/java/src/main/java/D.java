// Decompiled / patched-up version used for the exercise.
// I *think* this was some kind of main component, but the name never got fixed.
import java.util.*;

public class D {

    // looks like some map of counts? keys are probably strings but I'm not 100% sure.
    private Map s;
    // list of "something" that gets processed later, maybe requests?
    private List r = new ArrayList();

    // pretty sure this was called early on with some initial data map (stock? config?).
    public D(Map m) {
        this.s = new HashMap();
        if (m != null) {
            for (Object k : m.keySet()) {
                this.s.put(k, m.get(k)); // just copy everything over for now
            }
        }
    }

    // adds one entry: a name plus a list of values (preferences? items?). naming is terrible.
    public void a(String x, List y) {
        if (x == null || y == null) return;
        List cp = new ArrayList();
        cp.addAll(y); // I remember wanting a defensive copy here...
        r.add(new E(x, cp));
    }

    // main method that runs through r and uses s somehow. z = max per entry? not documented.
    public List b(int z) {
        List out = new ArrayList();
        if (z <= 0) return out;

        for (int i = 0; i < r.size(); i++) {
            Object o = r.get(i);
            if (!(o instanceof E)) continue; // should not really happen but who knows.
            E e = (E) o;
            int c = z;
            while (c > 0) {
                Object picked = f(e);
                if (picked == null) break; // nothing left to grab for this one
                out.add(new G(e.n, picked.toString()));
                c--;
            }
        }
        return out;
    }

    // tries to fetch ONE thing for the given E from the map s.
    private Object f(E e) {
        if (e == null || e.w == null) return null;

        // first pass: walk through whatever is in e.w in order and see if s has it
        for (int i = 0; i < e.w.size(); i++) {
            Object wish = e.w.get(i);
            if (wish == null) continue;
            Integer cnt = (Integer) s.get(wish);
            if (cnt != null && cnt.intValue() > 0) {
                s.put(wish, Integer.valueOf(cnt.intValue() - 1)); // consume one
                return wish;
            }
        }

        // fallback: couldn't match anything in e.w, so just grab the first entry with count > 0
        Object altKey = null;
        Integer altCnt = null;
        for (Object k : s.keySet()) {
            Object v = s.get(k);
            if (v instanceof Integer) {
                Integer ci = (Integer) v;
                if (ci.intValue() > 0) {
                    altKey = k;
                    altCnt = ci;
                    break;
                }
            }
        }
        if (altKey == null || altCnt == null) return null;
        s.put(altKey, Integer.valueOf(altCnt.intValue() - 1));
        return altKey;
    }

    // tiny holder class: name + some list (looks like an ordered list of "wanted" things).
    private static class E {
        String n;
        List w;

        E(String n, List w) {
            this.n = n;
            this.w = w;
        }
    }

    // seems to represent one "output row": two strings, no idea why it's called G.
    public static class G {
        public final String a;
        public final String b;

        public G(String a, String b) {
            this.a = a;
            this.b = b;
        }
    }
}
