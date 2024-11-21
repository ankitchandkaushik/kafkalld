import java.util.*;

public class Topic {
  String id;

  String name;

  List<Message> messages;

  Map<String, Integer> consumerOffset;

  public Topic(String name) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    messages = Collections.synchronizedList(new ArrayList<>());;
    consumerOffset = new HashMap<>();
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<Message> getMessages() {
    return messages;
  }

  public Map<String, Integer> getConsumerOffset() {
    return consumerOffset;
  }
}
