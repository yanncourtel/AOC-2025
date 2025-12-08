package santamarket.model

sealed trait ProductUnit

object ProductUnit {
  case object Kilo extends ProductUnit
  case object Each extends ProductUnit
}
