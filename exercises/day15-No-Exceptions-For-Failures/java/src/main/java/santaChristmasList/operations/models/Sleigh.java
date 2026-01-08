package santaChristmasList.operations.models;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Sleigh {

    private final Map<Child, String> messages = new HashMap<>();

    public void put(Child child, String message) {
        messages.put(child, message);
    }

    public Map<Child, String> messages() {
        return Collections.unmodifiableMap(messages);
    }
}
