import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Producer {

  Storage storage;

  public Producer() {
    storage = Storage.getInstance();
  }
   public void send (String topicName, Message message) {

        storage.publish(message, topicName);

   }

}
