// Decompiled / patched-up version used for the exercise.
// Class name D isn't great; left over from some tooling, I think.
import scala.collection.mutable

class D(m: Map[Any, Any]) {

  // map of keys to remaining Int counts (stock? points?). kept very generic.
  private val s: mutable.Map[Any, Int] = mutable.Map.empty

  // buffer of entries to process one by one.
  private val r: mutable.Buffer[E] = mutable.Buffer.empty

  if (m != null) {
    m.foreach { case (k, v) =>
      v match {
        case i: Int => s(k) = i // just preserve integer-ish values
        case _      => // ignore other stuff
      }
    }
  }

  // adds one record using x as some identifier and y as an ordered list of values.
  def a(x: String, y: List[Any]): Unit = {
    if (x == null || y == null) return
    val cp = y.toBuffer
    r.append(E(x, cp))
  }

  // walks through r and, for each E, tries to pull up to z items from s.
  def b(z: Int): List[G] = {
    val out = mutable.Buffer[G]()
    if (z <= 0) return out.toList

    for (e <- r) {
      var c = z
      while (c > 0) {
        val picked = f(e)
        if (picked.isEmpty) {
          c = 0 // nothing else useful for this one
        } else {
          out.append(G(e.n, picked.get.toString))
          c -= 1
        }
      }
    }
    out.toList
  }

  // tries to obtain exactly one element for the given E from the map s.
  private def f(e: E): Option[Any] = {
    if (e == null || e.w == null) return None

    // pass 1: go through e.w and try to match entries in s that still have positive count
    e.w.foreach { wish =>
      if (wish != null) {
        s.get(wish) match {
          case Some(cnt) if cnt > 0 =>
            s.update(wish, cnt - 1)
            return Some(wish)
          case _ =>
        }
      }
    }

    // pass 2: if nothing matched, just find the first key in s with count > 0
    s.find { case (_, cnt) => cnt > 0 } match {
      case Some((k, cnt)) =>
        s.update(k, cnt - 1)
        Some(k)
      case None =>
        None
    }
  }

  // simple case class: identifier n plus a buffer w (looks like a preference list).
  private case class E(n: String, w: mutable.Buffer[Any])

  // output row: two strings; original letters a and b aren't exactly descriptive.
  case class G(a: String, b: String)
}
