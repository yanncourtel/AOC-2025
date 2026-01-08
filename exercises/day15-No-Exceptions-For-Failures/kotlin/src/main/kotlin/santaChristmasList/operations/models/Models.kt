package santaChristmasList.operations.models

data class Child(val name: String)
data class Gift(val name: String, val barCode: String)

class Sleigh {
    private val messages: MutableMap<Child, String> = LinkedHashMap()
    fun put(child: Child, message: String) { messages[child] = message }
    fun messages(): Map<Child, String> = messages.toMap()
}
