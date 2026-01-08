package santaChristmasList.operations.models

final case class Child(name: String)
final case class Gift(name: String, barCode: String)

final class Sleigh {
  private val messages = scala.collection.mutable.Map.empty[Child, String]
  def put(child: Child, message: String): Unit = messages.update(child, message)
  def messagesView: Map[Child, String] = messages.toMap
}
