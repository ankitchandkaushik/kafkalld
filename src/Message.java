import java.util.UUID;

public class Message {
  String id;
  String description;

  public Message(String description) {
    id = UUID.randomUUID().toString();
    this.description = description;
  }

  @Override
  public String toString() {
    return "Message{" + "id='" + id + '\'' + ", description='" + description + '\'' + '}';
  }
}
