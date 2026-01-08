// Decompiled / patched-up version used for the exercise.
// This used to have nicer names, but they were stripped; didn't get around to fixing them.
import java.util.*

// main-ish class, some sort of dispatcher/allocator from what I remember.
class D(m: Map<*, *>?) {

    // map of something to an Int count (stock? quota?). left generic on purpose.
    private val s: MutableMap<Any?, Int> = HashMap()
    // bunch of entries that get iterated over later.
    private val r: MutableList<E> = ArrayList()

    init {
        if (m != null) {
            for (k in m.keys) {
                val v = m[k]
                if (v is Int) {
                    s[k] = v // only keep integer-like values
                }
            }
        }
    }

    // registers one "row": some identifier x with a list y (probably ordered preferences).
    fun a(x: String?, y: List<Any?>?) {
        if (x == null || y == null) return
        val cp: MutableList<Any?> = ArrayList()
        cp.addAll(y) // shallow copy, better than aliasing the original
        r.add(E(x, cp))
    }

    // does the main processing loop; z is some upper bound per E (max items per entry?).
    fun b(z: Int): List<G> {
        val out: MutableList<G> = ArrayList()
        if (z <= 0) return out

        for (e in r) {
            var c = z
            while (c > 0) {
                val picked = f(e) ?: break
                out.add(G(e.n, picked.toString()))
                c--
            }
        }
        return out
    }

    // internal helper that tries to take ONE item from s that matches this E.
    private fun f(e: E?): Any? {
        if (e == null || e.w == null) return null

        // stage one: go through e.w, in order, and see if s still has any of those
        for (wish in e.w) {
            if (wish == null) continue
            val cnt = s[wish]
            if (cnt != null && cnt > 0) {
                s[wish] = cnt - 1 // burn one
                return wish
            }
        }

        // stage two: nothing from the list worked, so take the first leftover thing in s
        var altKey: Any? = null
        var altCnt: Int? = null
        for ((k, v) in s) {
            if (v > 0) {
                altKey = k
                altCnt = v
                break
            }
        }
        if (altKey == null || altCnt == null) return null
        s[altKey] = altCnt - 1
        return altKey
    }

    // tiny data holder: some name plus a list w (seems like an ordered wishlist).
    private class E(val n: String, val w: List<Any?>?)

    // result pair: label + chosen value; original name "G" is not very helpful.
    class G(val a: String, val b: String)
}
